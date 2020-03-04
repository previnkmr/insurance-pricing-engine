package dev.previn.insurance.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * DTO for an insurance estimate for an applicant
 */
public class ApplicantEstimate {
    private final String firstName;
    private final Dollar insuranceEstimate;

    @JsonCreator
    public ApplicantEstimate(
            @JsonProperty("firstName") final String firstName,
            @JsonProperty("insuranceEstimate") final String insuranceEstimate
    ) {
        this(firstName, new BigDecimal(insuranceEstimate.substring(1)));
    }

    public ApplicantEstimate(final String firstName, final BigDecimal insuranceEstimate) {
        this(firstName, new Dollar(insuranceEstimate));
    }

    public ApplicantEstimate(final String firstName, final Dollar insuranceEstimate) {
        this.firstName = requireNonNull(firstName);
        this.insuranceEstimate = requireNonNull(insuranceEstimate);
    }

    public String getFirstName() {
        return firstName;
    }

    @JsonIgnore
    public Dollar getInsuranceEstimate() {
        return insuranceEstimate;
    }

    @JsonProperty("insuranceEstimate")
    public String getInsuranceEstimateString() {
        return insuranceEstimate.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicantEstimate)) return false;
        ApplicantEstimate that = (ApplicantEstimate) o;
        return getFirstName().equals(that.getFirstName()) &&
                getInsuranceEstimate().equals(that.getInsuranceEstimate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getInsuranceEstimate());
    }
}
