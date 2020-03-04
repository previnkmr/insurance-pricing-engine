package dev.previn.insurance.pricingengine;

import dev.previn.insurance.dtos.ApplicantEstimate;
import dev.previn.insurance.dtos.ApplicantInfo;
import dev.previn.insurance.dtos.Dollar;
import dev.previn.insurance.dtos.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PricingEngineTest {
    private static ApplicantInfo TEST_APPLICANT_INFO_1;
    private static ApplicantInfo TEST_APPLICANT_INFO_2;
    private static ApplicantInfo TEST_APPLICANT_INFO_3;
    private static ApplicantEstimate EXPECTED_APPLICANT_ESTIMATE_1;
    private static ApplicantEstimate EXPECTED_APPLICANT_ESTIMATE_2;
    private static ApplicantEstimate EXPECTED_APPLICANT_ESTIMATE_3;
    private static PricingEngine testPricingEngine;

    @BeforeAll
    static void setUp() {
        TEST_APPLICANT_INFO_1 = new ApplicantInfo("Kelly",50, Gender.FEMALE, Set.of("Allergies"));
        TEST_APPLICANT_INFO_2 = new ApplicantInfo("Josh",40, Gender.MALE, Set.of("Sleep Apnea"));
        TEST_APPLICANT_INFO_3 = new ApplicantInfo("Brad",20, Gender.MALE, Set.of("Heart Disease"));
        EXPECTED_APPLICANT_ESTIMATE_1 = new ApplicantEstimate("Kelly", new Dollar(BigDecimal.valueOf(210.2)));
        EXPECTED_APPLICANT_ESTIMATE_2 = new ApplicantEstimate("Josh", new Dollar(BigDecimal.valueOf(190.80)));
        EXPECTED_APPLICANT_ESTIMATE_3 = new ApplicantEstimate("Brad", new Dollar(BigDecimal.valueOf(117)));
        testPricingEngine = new PricingEngine();
    }

    @Test
    void estimateAnnualInsurancePrice() {
        assertEquals(
                EXPECTED_APPLICANT_ESTIMATE_1,
                testPricingEngine.estimateAnnualInsurancePrice(TEST_APPLICANT_INFO_1)
        );
        assertEquals(
                EXPECTED_APPLICANT_ESTIMATE_2,
                testPricingEngine.estimateAnnualInsurancePrice(TEST_APPLICANT_INFO_2)
        );
        final ApplicantEstimate actual = testPricingEngine.estimateAnnualInsurancePrice(TEST_APPLICANT_INFO_3);
        assertEquals(
                EXPECTED_APPLICANT_ESTIMATE_3,
                actual
        );
    }
}