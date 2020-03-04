package dev.previn.insurance.exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.previn.insurance.dtos.ApplicantEstimate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class JsonApplicantEstimateExporterTest {
    private static String[] APPLICANT_ESTIMATE_PATH;
    private static ApplicantEstimate EXPECTED_APPLICANT_INFO_1;
    private static ApplicantEstimate EXPECTED_APPLICANT_INFO_2;
    private static ApplicantEstimate EXPECTED_APPLICANT_INFO_3;
    private static ObjectMapper OBJECT_MAPPER;
    private static JsonApplicantEstimateExporter TEST_APPLICANT_ESTIMATE_EXPORTER;

    @BeforeAll
    static void setUp() {
        APPLICANT_ESTIMATE_PATH
                = new String[] { "src/test/resources/applicant-estimates/ApplicantEstimate.json" };
        EXPECTED_APPLICANT_INFO_1 = new ApplicantEstimate("Kelly", BigDecimal.valueOf(210.2));
        EXPECTED_APPLICANT_INFO_2 = new ApplicantEstimate("Josh",BigDecimal.valueOf(190.80));
        EXPECTED_APPLICANT_INFO_3 = new ApplicantEstimate("Brad",BigDecimal.valueOf(117.00));
        OBJECT_MAPPER = new ObjectMapper();
        TEST_APPLICANT_ESTIMATE_EXPORTER = new JsonApplicantEstimateExporter();
    }

    @Test
    void testExportApplicantEstimate() throws IOException {
        TEST_APPLICANT_ESTIMATE_EXPORTER.exportApplicantEstimate(EXPECTED_APPLICANT_INFO_1, APPLICANT_ESTIMATE_PATH);
        ApplicantEstimate actualEstimateObject
                = OBJECT_MAPPER.readValue(new File(APPLICANT_ESTIMATE_PATH[0]), ApplicantEstimate.class);
        assertEquals(EXPECTED_APPLICANT_INFO_1, actualEstimateObject);

        TEST_APPLICANT_ESTIMATE_EXPORTER.exportApplicantEstimate(EXPECTED_APPLICANT_INFO_2, APPLICANT_ESTIMATE_PATH);
        actualEstimateObject
                = OBJECT_MAPPER.readValue(new File(APPLICANT_ESTIMATE_PATH[0]), ApplicantEstimate.class);
        assertEquals(EXPECTED_APPLICANT_INFO_2, actualEstimateObject);

        TEST_APPLICANT_ESTIMATE_EXPORTER.exportApplicantEstimate(EXPECTED_APPLICANT_INFO_3, APPLICANT_ESTIMATE_PATH);
        actualEstimateObject
                = OBJECT_MAPPER.readValue(new File(APPLICANT_ESTIMATE_PATH[0]), ApplicantEstimate.class);
        assertEquals(EXPECTED_APPLICANT_INFO_3, actualEstimateObject);
    }
}