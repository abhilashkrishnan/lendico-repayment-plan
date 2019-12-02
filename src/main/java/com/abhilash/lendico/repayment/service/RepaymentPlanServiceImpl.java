package com.abhilash.lendico.repayment.service;

import com.abhilash.lendico.repayment.model.Repayment;
import com.abhilash.lendico.repayment.model.RepaymentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @see {@link RepaymentPlanService}
 */

@Service
public class RepaymentPlanServiceImpl implements RepaymentPlanService {

    @Autowired
    private RepaymentPlanGenerator repaymentPlanGenerator;


    @Override
    public RepaymentPlan generateRepaymentPlan(BigDecimal loanAmount, double nominalRate, int duration, LocalDateTime startDate) {

        List<Repayment> repayments = new ArrayList<>();
        BigDecimal initialOutstandingPrincipal = loanAmount;

        BigDecimal annuity = repaymentPlanGenerator.calculateAnnuity(loanAmount, nominalRate, duration);

        for (int i = 0; i < duration; i++) {
            Repayment repayment = generateRepayment(loanAmount, nominalRate, duration, startDate, initialOutstandingPrincipal, annuity, i);

            BigDecimal remainingOutstandingPrincipal = new BigDecimal(repayment.getRemainingOutstandingPrincipal());
            initialOutstandingPrincipal = remainingOutstandingPrincipal;

            repayments.add(repayment);
        }

        RepaymentPlan repaymentPlan = new RepaymentPlan();
        repaymentPlan.setRepayments(repayments);

        return repaymentPlan;
    }

    @Override
    public Repayment generateRepayment(BigDecimal loanAmount, double nominalRate, int duration, LocalDateTime startDate, BigDecimal initialOutstandingPrincipal, BigDecimal annuity, int monthsAfterStart) {

        Repayment repayment = new Repayment();


        LocalDateTime date = startDate.plusMonths(monthsAfterStart).withHour(0).withMinute(0).withSecond(0);
        repayment.setDate(date);

        BigDecimal interest = repaymentPlanGenerator.calculateInterest(nominalRate, initialOutstandingPrincipal);
        repayment.setInterest(interest.toString());

        BigDecimal principal = repaymentPlanGenerator.calculatePrincipal(interest, annuity, initialOutstandingPrincipal);
        repayment.setPrincipal(principal.toString());

        BigDecimal borrowerPayment = repaymentPlanGenerator.calculateBorrowerPayment(principal, interest);
        repayment.setBorrowerPaymentAmount(borrowerPayment.toString());

        initialOutstandingPrincipal = initialOutstandingPrincipal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        repayment.setInitialOutstandingPrincipal(initialOutstandingPrincipal.toString());

        BigDecimal remainingOutstandingPrincipal = repaymentPlanGenerator.calculateRemainingOutstandingPrincipal(initialOutstandingPrincipal, principal);
        repayment.setRemainingOutstandingPrincipal(remainingOutstandingPrincipal.toString());

        return repayment;
    }
}
