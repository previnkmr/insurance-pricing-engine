package dev.previn.insurance.pricingengine.transformations;

import dev.previn.insurance.dtos.ApplicantInfo;
import dev.previn.insurance.dtos.Dollar;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BaseCostTransformation implements Transformation {
    private final Dollar baseInsuranceCost;

    public BaseCostTransformation(final BigDecimal baseInsuranceCost) {
        this(new Dollar(baseInsuranceCost));
    }

    public BaseCostTransformation(final Dollar baseInsuranceCost) {
        this.baseInsuranceCost = requireNonNull(baseInsuranceCost);
    }

    @Override
    public Dollar apply(final ApplicantInfo applicantInfo, final Dollar estimate) {
        return new Dollar(estimate.getValue().add(baseInsuranceCost.getValue()));
    }
}
