package dev.previn.insurance.importers;

import dev.previn.insurance.dtos.ApplicantInfo;

import java.io.IOException;

/**
 * Interface for {@link ApplicantInfo} importers
 */
public interface ApplicantInfoImporter {

    /**
     * Imports {@link ApplicantInfo}
     *
     * @param args the arguments necessary to import the applicant info based on the implementation of this interface
     * @return the applicant info that was imported
     * @throws IOException if there was an I/O error importing the applicant info
     */
    ApplicantInfo importApplicantInfo(final String[] args) throws IOException;
}
