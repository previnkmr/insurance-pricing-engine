package dev.previn.insurance;

import dev.previn.insurance.dtos.ApplicantEstimate;
import dev.previn.insurance.dtos.ApplicantInfo;
import dev.previn.insurance.exporters.ApplicantEstimateExporter;
import dev.previn.insurance.exporters.JsonApplicantEstimateExporter;
import dev.previn.insurance.importers.ApplicantInfoImporter;
import dev.previn.insurance.importers.JsonApplicantInfoImporter;
import dev.previn.insurance.pricingengine.PricingEngine;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static java.util.Objects.isNull;

public class Application {
    private static final int IMPORT_PATH_ARG = 1;
    private static final int EXPORT_PATH_ARG = 2;

    /**
     * Application entry point for the TICKLE Pricing Engine MVP
     *
     * @param args Two arguments are required for the TICKLE Pricing Engine. The first should be the path to the input
     *             Applicant Info JSON file and the second should be the path to write the output Applicant Estimate
     *             JSON to
     */
    public static void main(String[] args) {
        if (isNull(args) || args.length < 2 || isNull(args[0]) || isNull(args[1])) {
            throw new IllegalArgumentException("Two arguments are required for the TICKLE Pricing Engine. The first " +
                    "should be the path to the input Applicant Info JSON file and the second should be the path to " +
                    "write the output Applicant Estimate JSON File");
        }
        File file = new File("test.txt");
        System.out.println(file.getAbsolutePath());
        final ApplicantInfoImporter importer = new JsonApplicantInfoImporter();
        final PricingEngine pricingEngine = new PricingEngine();
        final ApplicantEstimateExporter exporter = new JsonApplicantEstimateExporter();
        try {
            final String[] importerArgs = Arrays.copyOfRange(args,0, IMPORT_PATH_ARG);
            final String[] exporterArgs = Arrays.copyOfRange(args, IMPORT_PATH_ARG, EXPORT_PATH_ARG);
            final ApplicantInfo applicantInfo= importer.importApplicantInfo(importerArgs);
            final ApplicantEstimate applicantEstimate = pricingEngine.estimateAnnualInsurancePrice(applicantInfo);
            exporter.exportApplicantEstimate(applicantEstimate, exporterArgs);
        } catch (final IOException e) {
            System.out.println("Error importing or exporting applicant info: " + e.getMessage());
        }
    }
}
