package com.abhilash.lendico.repayment.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @see {@link RepaymentPlanGenerator}
 */
@Service
public class RepaymentPlanGeneratorImpl implements RepaymentPlanGenerator {

    @Override
    public BigDecimal calculateAnnuity(BigDecimal loanAmount, double nominalRate, int duration) {
        nominalRate = nominalRate / 100.0;

        double nominalRateByMonth = nominalRate / 12.0;

        double annuity =  (loanAmount.doubleValue() * nominalRateByMonth) /
                (1 - Math.pow(1 + nominalRateByMonth, -duration));

        BigDecimal result = new BigDecimal(annuity);
        result = result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return result;
    }

    @Override
    public BigDecimal calculateRemainingOutstandingPrincipal(BigDecimal initialOutstandingPrincipal, BigDecimal principal) {

        BigDecimal remainingOutstandingPrincipal = initialOutstandingPrincipal.subtract(principal);

        if (remainingOutstandingPrincipal.compareTo(BigDecimal.ZERO) < 0) {
            remainingOutstandingPrincipal = BigDecimal.ZERO.setScale(2);
        }
        return remainingOutstandingPrincipal;
    }

    @Override
    public BigDecimal calculatePrincipal(BigDecimal interest, BigDecimal annuity, BigDecimal initialOutstandingPrincipal) {

        BigDecimal principal = annuity.subtract(interest);

        if (principal.compareTo(initialOutstandingPrincipal) > 0) {
            principal = initialOutstandingPrincipal;
        }
        principal = principal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return principal;
    }

    @Override
    public BigDecimal calculateInterest(double nominalRate, BigDecimal initialOutstandingPrincipal) {

        BigDecimal interest = new BigDecimal(nominalRate * 30 * initialOutstandingPrincipal.doubleValue() / 360);
        interest = interest.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return interest;
    }

    @Override
    public BigDecimal calculateBorrowerPayment(BigDecimal principal, BigDecimal interest) {

        BigDecimal borrowerPayment = principal.add(interest);
        borrowerPayment.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return borrowerPayment;
    }
}
