package com.abhilash.lendico.repayment.service;

import java.math.BigDecimal;

/**
 * Repayment plan calculator functions
 */
public interface RepaymentPlanGenerator {

    /**
     * Calculates annuity amount from the input parameters
     * @param loanAmount The loan amount
     * @param nominalRate The nominal interest rate
     * @param duration The duration in months of the loan
     * @return The annuity amount
     */
    BigDecimal calculateAnnuity(BigDecimal loanAmount, double nominalRate,
                                int duration);

    /**
     * Calculates the remaining outstanding principal amount
     * @param initialOutstandingPrincipal The initial outstanding principal amount
     * @param principal The principal amount
     * @return The remaining outstanding principal amount
     */
    BigDecimal calculateRemainingOutstandingPrincipal(
            BigDecimal initialOutstandingPrincipal,
            BigDecimal principal);

    /**
     * Calculates the principal amount
     * @param interest The interest amount
     * @param annuity The annuity amount
     * @param initialOutstandingPrincipal The initial outstanding principal amount
     * @return The principal amount
     */
    BigDecimal calculatePrincipal(BigDecimal interest, BigDecimal annuity, BigDecimal initialOutstandingPrincipal);

    /**
     * Calculates the interest amount
     * @param nominalRate The nominate interest rate
     * @param initialOutstandingPrincipal The initial outstanding principal amount
     * @return The interest amount
     */
    BigDecimal calculateInterest(double nominalRate,
                                 BigDecimal initialOutstandingPrincipal);

    /**
     * Calculates the borrower payment amount
     * @param principal The principal amount
     * @param interest The interest amount
     * @return The borrower payment amount
     */
    BigDecimal calculateBorrowerPayment(BigDecimal principal, BigDecimal interest);
}
