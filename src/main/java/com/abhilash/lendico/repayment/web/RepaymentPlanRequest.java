package com.abhilash.lendico.repayment.web;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * HTTP request parameters for {@link RepaymentPlanController#generateRepaymentPlan(RepaymentPlanRequest)}
 */
public class RepaymentPlanRequest {

    private BigDecimal loanAmount;
    private Double nominalRate;
    private Integer duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date startDate;


    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getNominalRate() {
        return nominalRate;
    }

    public void setNominalRate(Double nominalRate) {
        this.nominalRate = nominalRate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepaymentPlanRequest that = (RepaymentPlanRequest) o;
        return loanAmount.equals(that.loanAmount) &&
                nominalRate.equals(that.nominalRate) &&
                duration.equals(that.duration) &&
                startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanAmount, nominalRate, duration, startDate);
    }

    @Override
    public String toString() {
        return "RepaymentPlanRequest{" +
                "loanAmount=" + loanAmount +
                ", nominalRate=" + nominalRate +
                ", duration=" + duration +
                ", startDate=" + startDate +
                '}';
    }
}
