package dev.previn.insurance.pricingengine.transformations;

import dev.previn.insurance.dtos.ApplicantInfo;
import dev.previn.insurance.dtos.Dollar;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class HealthConditionTransformation implements Transformation {
    private final Map<String, BigDecimal> healthConditionMultipliers;

    public HealthConditionTransformation(final Map<String, BigDecimal> healthConditionMultipliers) {
        this.healthConditionMultipliers = requireNonNull(healthConditionMultipliers);
    }

    @Override
    public Dollar apply(ApplicantInfo applicantInfo, Dollar estimate) {
        // Currently only one health condition allowed per applicant
        final Iterator<String> healthConditionIterator = applicantInfo.getHealthConditions().iterator();
        final String healthCondition = healthConditionIterator.hasNext() ? healthConditionIterator.next() : null;
        if (isNull(healthCondition)) {
            return estimate;
        }

        final BigDecimal healthConditionMultiplier = healthConditionMultipliers.get(healthCondition.toLowerCase());
        if (isNull(healthConditionMultiplier)) {
            throw new IllegalArgumentException("Invalid health condition: " + healthCondition);
        }
        return new Dollar(estimate.getValue().multiply(healthConditionMultiplier));
    }
}
