package slotegrator.project.helper;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class AssetHelper {

    private static final Logger LOGGER = LogManager.getLogger(AssetHelper.class);

    /**
     * This method is checking status from response
     * @param response
     * @param expectedHttpStatus
     */
    public static void checkStatus(Response response, int expectedHttpStatus) {
        LOGGER.debug("asserting response code...");
        int statusCode = response.getStatusCode();
        assertThat(statusCode)
                .withFailMessage("actual status code incorrect. Should be " + expectedHttpStatus)
                .isEqualTo(expectedHttpStatus);
        LOGGER.debug("status code is correct. Status code = " + statusCode);
    }
}
