package dev.previn.insurance.pricingengine.transformations;

import dev.previn.insurance.dtos.ApplicantInfo;
import dev.previn.insurance.dtos.Dollar;

import java.math.BigDecimal;

public class AgeScaleTransformation implements Transformation {
    private final int minAge;
    private final int ageIncrement;
    private final int baseIncreasePerIncrement;

    public AgeScaleTransformation(
            final int minAge, final int ageIncrement, final int baseIncreasePerIncrement
    ) {
        this.minAge = minAge;
        this.ageIncrement = ageIncrement;
        this.baseIncreasePerIncrement = baseIncreasePerIncrement;
    }

    @Override
    public Dollar apply(ApplicantInfo applicantInfo, Dollar estimate) {
        return new Dollar(estimate.getValue().add(
                BigDecimal.valueOf(baseIncreasePerIncrement * ((applicantInfo.getAge() - minAge) / ageIncrement))
        ));
    }
}
