package dev.previn.insurance.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * DTO for information related to an insurance estimate applicant
 */
public class ApplicantInfo {
    private static final int MIN_APPLICANT_AGE = 18;

    private final String firstName;
    private final int age;
    private final Gender gender;
    private final Set<String> healthConditions;

    @JsonCreator
    public ApplicantInfo(
            @JsonProperty("firstName") final String firstName,
            @JsonProperty("age") final int age,
            @JsonProperty("gender") final Gender gender,
            @JsonProperty("healthConditions") final Set<String> healthConditions
    ) {
        this.firstName = requireNonNull(firstName);
        if (age < MIN_APPLICANT_AGE) {
            throw new IllegalArgumentException("Applicant must be " + MIN_APPLICANT_AGE + " years of age or older");
        }
        this.age = age;
        this.gender = requireNonNull(gender);
        this.healthConditions = requireNonNull(healthConditions);
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Set<String> getHealthConditions() {
        return healthConditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicantInfo)) return false;
        ApplicantInfo that = (ApplicantInfo) o;
        return getAge() == that.getAge() &&
                getFirstName().equals(that.getFirstName()) &&
                getGender() == that.getGender() &&
                getHealthConditions().equals(that.getHealthConditions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getAge(), getGender(), getHealthConditions());
    }
}
