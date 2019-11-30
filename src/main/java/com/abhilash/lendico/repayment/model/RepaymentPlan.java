package com.abhilash.lendico.repayment.model;

import java.util.List;
import java.util.Objects;

/**
 *  The repayment plan model
 */
public class RepaymentPlan {

    /**
     *  List of repayment plans for the borrower
     */
    private List<Repayment> repayments;

    public List<Repayment> getRepayments() {
        return repayments;
    }

    public void setRepayments(List<Repayment> repayments) {
        this.repayments = repayments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepaymentPlan that = (RepaymentPlan) o;
        return Objects.equals(repayments, that.repayments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repayments);
    }

    @Override
    public String toString() {
        return "RepaymentPlan{" +
                "repayments=" + repayments +
                '}';
    }
}
