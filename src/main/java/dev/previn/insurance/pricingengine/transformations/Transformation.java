package dev.previn.insurance.pricingengine.transformations;

import dev.previn.insurance.dtos.ApplicantInfo;
import dev.previn.insurance.dtos.Dollar;

import java.util.function.BiFunction;

/**
 * Transformation interface for a BiFunction that performs a transformation on the current insurance estimate based on
 * the {@link ApplicantInfo}
 */
public interface Transformation extends BiFunction<ApplicantInfo, Dollar, Dollar> {

    @Override
    Dollar apply(final ApplicantInfo applicantInfo, final Dollar estimate);
}
