package dev.previn.insurance.pricingengine;

import dev.previn.insurance.dtos.ApplicantEstimate;
import dev.previn.insurance.dtos.ApplicantInfo;
import dev.previn.insurance.dtos.Dollar;
import dev.previn.insurance.pricingengine.transformations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PricingEngine {
    private static final int BASE_COST = 100;
    private static final int MIN_AGE = 18;
    private static final int AGE_INCREMENT = 5;
    private static final int BASE_INCREASE_PER_INCREMENT = 20;
    private static final Dollar MALE_DEDUCTION = new Dollar(BigDecimal.ZERO);
    private static final Dollar FEMALE_DEDUCTION = new Dollar(BigDecimal.valueOf(12));
    private static final Map<String, BigDecimal> HEALTH_CONDITION_MULTIPLIERS = Map.of(
            "allergies", BigDecimal.valueOf(1.01),
            "sleep apnea", BigDecimal.valueOf(1.06),
            "heart disease", BigDecimal.valueOf(1.17)
    );

    private final List<Transformation> calculationPipeline;

    public PricingEngine() {
        calculationPipeline = List.of(
                new BaseCostTransformation(BigDecimal.valueOf(BASE_COST)),
                new AgeScaleTransformation(MIN_AGE, AGE_INCREMENT, BASE_INCREASE_PER_INCREMENT),
                new HealthConditionTransformation(HEALTH_CONDITION_MULTIPLIERS),
                new GenderDeductionTransformation(MALE_DEDUCTION, FEMALE_DEDUCTION)
        );
    }

    /**
     * estimates the insurance cost for the given applicant
     *
     * @param applicantInfo the info of the applicant
     * @return the estimated insurance cost for the applicant
     */
    public ApplicantEstimate estimateAnnualInsurancePrice(final ApplicantInfo applicantInfo) {
        Dollar insuranceEstimate = new Dollar(BigDecimal.ZERO);
        for (final Transformation transformation : calculationPipeline) {
            final Dollar tempEstimate = transformation.apply(applicantInfo, insuranceEstimate);
            insuranceEstimate = tempEstimate;
        }
        return new ApplicantEstimate(applicantInfo.getFirstName(), insuranceEstimate);
    }
}
