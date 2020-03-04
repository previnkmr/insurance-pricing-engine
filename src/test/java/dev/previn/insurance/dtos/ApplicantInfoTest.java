package dev.previn.insurance.dtos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantInfoTest {

    private static String testFirstName;
    private static int testAge;
    private static Gender testGender;
    private static Set<String> testHealthConditions;
    private static ApplicantInfo testApplicantInfo;

    @BeforeAll
    static void setUp() {
        testFirstName = "testFirstName";
        testAge = 18;
        testGender = Gender.FEMALE;
        testHealthConditions = Set.of("testHealthCondition");
        testApplicantInfo = new ApplicantInfo(testFirstName, testAge, testGender, testHealthConditions);
    }

    @Test
    void getFirstName() {
        assertThrows(NullPointerException.class, () ->
                new ApplicantInfo(null, testAge, testGender, testHealthConditions)
        );

        assertEquals(testFirstName, testApplicantInfo.getFirstName());
    }

    @Test
    void getAge() {
        assertThrows(IllegalArgumentException.class, () ->
                new ApplicantInfo(testFirstName, 17, testGender, testHealthConditions)
        );

        assertEquals(testAge, testApplicantInfo.getAge());
    }

    @Test
    void getGender() {
        assertThrows(NullPointerException.class, () ->
                new ApplicantInfo(testFirstName, testAge, null, testHealthConditions)
        );

        assertEquals(testGender, testApplicantInfo.getGender());
    }

    @Test
    void getHealthConditions() {
        assertThrows(NullPointerException.class, () ->
                new ApplicantInfo(testFirstName, testAge, testGender, null)
        );

        assertEquals(testHealthConditions, testApplicantInfo.getHealthConditions());
    }
}