package com.abhilash.lendico.repayment.web;

import com.abhilash.lendico.repayment.model.Repayment;
import com.abhilash.lendico.repayment.model.RepaymentPlan;
import com.abhilash.lendico.repayment.service.RepaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * REST API for generating loan repayment plan
 */
@RestController
@RequestMapping(path = "/generate-plan")
public class RepaymentPlanController {

    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @PostMapping
    public @ResponseBody List<Repayment> generateRepaymentPlan(@RequestBody RepaymentPlanRequest request) {

        BigDecimal loanAmount = request.getLoanAmount();
        Double nominalRate = request.getNominalRate();
        Integer duration = request.getDuration();

        Date startDate = request.getStartDate();
        LocalDateTime localStartDate = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());

        RepaymentPlan plan = repaymentPlanService.generateRepaymentPlan(loanAmount, nominalRate, duration, localStartDate);
        return plan.getRepayments();
    }
}
