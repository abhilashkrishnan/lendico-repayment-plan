package com.abhilash.lendico.repayment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The repayment model
 */
public class Repayment {

    private String borrowerPaymentAmount;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime date;

    private String initialOutstandingPrincipal;
    private String interest;
    private String principal;
    private String remainingOutstandingPrincipal;

    public String getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public void setBorrowerPaymentAmount(String borrowerPaymentAmount) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public void setInitialOutstandingPrincipal(String initialOutstandingPrincipal) {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }

    public void setRemainingOutstandingPrincipal(String remainingOutstandingPrincipal) {
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repayment repayment = (Repayment) o;
        return borrowerPaymentAmount.equals(repayment.borrowerPaymentAmount) &&
                date.equals(repayment.date) &&
                initialOutstandingPrincipal.equals(repayment.initialOutstandingPrincipal) &&
                interest.equals(repayment.interest) &&
                principal.equals(repayment.principal) &&
                remainingOutstandingPrincipal.equals(repayment.remainingOutstandingPrincipal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowerPaymentAmount, date, initialOutstandingPrincipal, interest, principal, remainingOutstandingPrincipal);
    }

    @Override
    public String toString() {
        return "Repayment{" +
                "borrowerPaymentAmount='" + borrowerPaymentAmount + '\'' +
                ", date=" + date +
                ", initialOutstandingPrincipal='" + initialOutstandingPrincipal + '\'' +
                ", interest='" + interest + '\'' +
                ", principal='" + principal + '\'' +
                ", remainingOutstandingPrincipal='" + remainingOutstandingPrincipal + '\'' +
                '}';
    }
}
