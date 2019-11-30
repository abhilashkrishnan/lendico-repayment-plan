package com.abhilash.lendico.repayment.service;

import com.abhilash.lendico.repayment.model.Repayment;
import com.abhilash.lendico.repayment.model.RepaymentPlan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Repayment plan calculation service
 */
public interface RepaymentPlanService {

    /**
     * Generates a repayment plan
     * @param loanAmount The loan amount
     * @param nominalRate The nominal interest rate
     * @param duration The duration in months of the loan
     * @param startDate The start date of the loan
     * @return The repayment plan
     */
    RepaymentPlan generateRepaymentPlan(BigDecimal loanAmount,
                                        double nominalRate,
                                        int duration,
                                        LocalDateTime startDate);

    /**
     * Calculates the repayment for the month after the start date
     * @param loanAmount The loan amount
     * @param nominalRate The nominal interest rate
     * @param duration The duration in months of the loan
     * @param startDate The start date of the loan
     * @param initialOutstandingPrincipal The initial outstanding principal value
     * @param annuity The annuity amount
     * @param monthsAfterStart The number of months after the start date
     * @return The repayment
     */
    Repayment generateRepayment(BigDecimal loanAmount,
                                double nominalRate,
                                int duration,
                                LocalDateTime startDate,
                                BigDecimal initialOutstandingPrincipal,
                                BigDecimal annuity,
                                int monthsAfterStart);
}
