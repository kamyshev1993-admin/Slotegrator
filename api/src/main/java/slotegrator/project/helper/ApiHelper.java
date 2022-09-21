package slotegrator.project.helper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;
import slotegrator.project.JsonGenerator;
import slotegrator.project.PathEnum;
import slotegrator.project.config.ConfigQA;
import slotegrator.project.model.ClientCredentialsGrantRequest;
import slotegrator.project.model.player.PlayerRequest;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    private static final String URL = ConfigQA.getInstance().getBaseurl();

    /**
     * This method calls /v2/oauth2/token method to get guest token
     * @param basicToken
     * @param body
     * @return Response
     */
    public static Response postClientCredentialsGrant(String basicToken, ClientCredentialsGrantRequest body) {
        return given().log().all(true)
                .header("Authorization", "Basic " + basicToken)
                .contentType(ContentType.JSON)
                .body(JsonGenerator.toJsonString(body))
                .post(URL + PathEnum.TOKEN.getPath())
                .then().log().all(true)
                .extract()
                .response();
    }


    /**
     * This method calls /v2/players{id} method to get player info
     * @param accessToken
     * @param id
     * @return Response
     */
    public static Response getPlayerInfo(String accessToken, long id) {
        return given().log().all(true)
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .get(URL + PathEnum.PLAYERS.getPath() + "/" + id)
                .then().log().all(true)
                .extract()
                .response();
    }

    /**
     * This method calls /v2/players method to create player
     * @param accessToken
     * @param body
     * @return Response
     */
    public static Response postPlayer(String accessToken, PlayerRequest body) {
        return given().log().all(true)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(JsonGenerator.toJsonString(body))
                .post(URL + PathEnum.PLAYERS.getPath())
                .then().log().all(true)
                .extract()
                .response();
    }

    /**
     * This method creates basic token using login and secret
     * @param login
     * @param secret
     * @return
     */
    public static String generateBasicToken(String login, String secret) {
        String plainCredentials = login + ":" + secret;
        byte[] encodedAuth = Base64.encodeBase64(plainCredentials.getBytes(StandardCharsets.US_ASCII));
        return new String(encodedAuth);
    }
}
