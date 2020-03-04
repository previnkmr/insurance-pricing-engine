package dev.previn.insurance.importers;

import dev.previn.insurance.dtos.ApplicantInfo;
import dev.previn.insurance.dtos.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonApplicantInfoImporterTest {
    private static String APPLICANT_INFO_PATH_1;
    private static String APPLICANT_INFO_PATH_2;
    private static String APPLICANT_INFO_PATH_3;
    private static ApplicantInfo EXPECTED_APPLICANT_INFO_1;
    private static ApplicantInfo EXPECTED_APPLICANT_INFO_2;
    private static ApplicantInfo EXPECTED_APPLICANT_INFO_3;
    private static JsonApplicantInfoImporter TEST_APPLICANT_INFO_IMPORTER;

    @BeforeAll
    static void setUp() {
        APPLICANT_INFO_PATH_1 = "src/test/resources/test-applicant-info/ApplicantInfo1.json";
        APPLICANT_INFO_PATH_2 = "src/test/resources/test-applicant-info/ApplicantInfo2.json";
        APPLICANT_INFO_PATH_3 = "src/test/resources/test-applicant-info/ApplicantInfo3.json";
        EXPECTED_APPLICANT_INFO_1 = new ApplicantInfo("Kelly",50, Gender.FEMALE, Set.of("Allergies"));
        EXPECTED_APPLICANT_INFO_2 = new ApplicantInfo("Josh",40, Gender.MALE, Set.of("Sleep Apnea"));
        EXPECTED_APPLICANT_INFO_3 = new ApplicantInfo("Brad",20, Gender.MALE, Set.of("Heart Disease"));
        TEST_APPLICANT_INFO_IMPORTER = new JsonApplicantInfoImporter();
    }

    @Test
    void testImportApplicantInfo() throws IOException {
        final ApplicantInfo actualApplicantInfo1
                = TEST_APPLICANT_INFO_IMPORTER.importApplicantInfo(new String[] { APPLICANT_INFO_PATH_1 });
        assertEquals(EXPECTED_APPLICANT_INFO_1, actualApplicantInfo1);


        final ApplicantInfo actualApplicantInfo2
                = TEST_APPLICANT_INFO_IMPORTER.importApplicantInfo(new String[] { APPLICANT_INFO_PATH_2 });
        assertEquals(EXPECTED_APPLICANT_INFO_2, actualApplicantInfo2);


        final ApplicantInfo actualApplicantInfo3
                = TEST_APPLICANT_INFO_IMPORTER.importApplicantInfo(new String[] { APPLICANT_INFO_PATH_3 });
        assertEquals(EXPECTED_APPLICANT_INFO_3, actualApplicantInfo3);
    }
}