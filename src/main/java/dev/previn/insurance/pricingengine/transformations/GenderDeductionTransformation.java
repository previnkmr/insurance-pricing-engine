package dev.previn.insurance.pricingengine.transformations;

import dev.previn.insurance.dtos.ApplicantInfo;
import dev.previn.insurance.dtos.Dollar;
import dev.previn.insurance.dtos.Gender;

import static java.util.Objects.nonNull;

public class GenderDeductionTransformation implements Transformation {
    private final Dollar maleDeduction;
    private final Dollar femaleDeduction;

    public GenderDeductionTransformation(final Dollar maleDeduction, final Dollar femaleDeduction) {
        this.maleDeduction = maleDeduction;
        this.femaleDeduction = femaleDeduction;
    }

    @Override
    public Dollar apply(ApplicantInfo applicantInfo, Dollar estimate) {
        if (applicantInfo.getGender().equals(Gender.FEMALE) && nonNull(femaleDeduction)) {
            return new Dollar(estimate.getValue().add(femaleDeduction.getValue().negate()));
        } else if (applicantInfo.getGender().equals(Gender.MALE) && nonNull(maleDeduction)) {
            return new Dollar(estimate.getValue().add(maleDeduction.getValue().negate()));
        } else {
            return estimate;
        }
    }
}
