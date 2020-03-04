package dev.previn.insurance.importers;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.previn.insurance.dtos.ApplicantInfo;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * JSON file implementation of {@link ApplicantInfoImporter}
 */
public class JsonApplicantInfoImporter implements ApplicantInfoImporter {
    private final ObjectMapper objectMapper;

    public JsonApplicantInfoImporter() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    /**
     * Get the {@link ApplicantInfo} from a JSON file
     *
     * @param args the first element should be the fully qualified path to the ApplicantInfo JSON file to import
     * @return The imported {@link ApplicantInfo}
     */
    @Override
    public ApplicantInfo importApplicantInfo(final String[] args) throws IOException {
        if (isNull(args) || args.length < 1 || isNull(args[0])) {
            throw new IllegalArgumentException("The first argument should be the path to the input Applicant Info " +
                    "JSON file");
        }
        return objectMapper.readValue(new File(args[0]), ApplicantInfo.class);
    }
}
