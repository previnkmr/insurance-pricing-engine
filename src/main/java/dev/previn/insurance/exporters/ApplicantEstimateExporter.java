package dev.previn.insurance.exporters;

import dev.previn.insurance.dtos.ApplicantEstimate;

import java.io.IOException;

/**
 * Interface for exporters of {@link ApplicantEstimate} objects
 */
public interface ApplicantEstimateExporter {

    /**
     * Exports the given {@link ApplicantEstimate}
     *
     * @param applicantEstimate the applicant estimate to export
     * @param args any necessary arguments for the implemented exported
     * @throws IOException if there is an error while exporting the applicant estimate
     */
    void exportApplicantEstimate(final ApplicantEstimate applicantEstimate, final String[] args) throws IOException;
}
