package dev.previn.insurance.exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.previn.insurance.dtos.ApplicantEstimate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

/**
 * Exports {@link ApplicantEstimate} objects to JSON files
 */
public class JsonApplicantEstimateExporter implements ApplicantEstimateExporter {
    private static final ObjectWriter OBJECT_WRITER = new ObjectMapper().writerWithDefaultPrettyPrinter();

    /**
     * Exports the given {@link ApplicantEstimate} to a JSON file
     *
     * @param applicantEstimate the applicant estimate to export
     * @param args the first element should be the output path to the JSON file
     * @throws IOException if there is an error while exporting the applicant estimate
     */
    @Override
    public void exportApplicantEstimate(
            final ApplicantEstimate applicantEstimate, final String[] args
    ) throws IOException {
        requireNonNull(applicantEstimate);
        if (isNull(args) || args.length < 1 || isNull(args[0])) {
            throw new IllegalArgumentException("The first argument should be the path to the out Applicant Estimate " +
                    "JSON file");
        }
        final Path path = Path.of(args[0]);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.writeString(path, OBJECT_WRITER.writeValueAsString(applicantEstimate));
        System.out.println("Wrote applicant estimate to \"" + path + "\"");
    }
}
