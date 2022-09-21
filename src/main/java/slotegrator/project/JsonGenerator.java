package slotegrator.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slotegrator.project.exception.JsonGenerateException;

public class JsonGenerator {
    private static final Logger LOGGER = LogManager.getLogger(JsonGenerator.class);

    /**
     * Method for making String from dto model
     * @param t
     * @param <T>
     * @return <T>
     */
    public static <T> String toJsonString(T t) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        try {
            LOGGER.info("transforming dto class to json string...");
            return objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(t);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        throw new JsonGenerateException(t.getClass());
    }
}
