package dev.previn.insurance.dtos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantEstimateTest {
    private static String testFirstName;
    private static String testInsuranceEstimateString;
    private static Dollar testInsuranceEstimate;
    private static ApplicantEstimate testApplicantEstimate;

    @BeforeAll
    static void setUp() {
        testFirstName = "testFirstName";
        testInsuranceEstimateString = "$213.17";
        testInsuranceEstimate = new Dollar(BigDecimal.valueOf(213.17));
        testApplicantEstimate = new ApplicantEstimate(testFirstName, testInsuranceEstimate);
    }

    @Test
    void getFirstName() {
        assertThrows(NullPointerException.class, () ->
                new ApplicantEstimate(null, testInsuranceEstimate)
        );

        assertEquals(testFirstName, testApplicantEstimate.getFirstName());
    }

    @Test
    void getInsuranceEstimate() {
        assertThrows(NullPointerException.class, () ->
                new ApplicantEstimate(testFirstName, (BigDecimal) null)
        );

        assertEquals(testInsuranceEstimate, testApplicantEstimate.getInsuranceEstimate());
    }

    @Test
    void getInsuranceEstimateString() {
        assertEquals(testInsuranceEstimateString, testApplicantEstimate.getInsuranceEstimateString());
    }
}