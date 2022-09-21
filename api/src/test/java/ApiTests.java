import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slotegrator.project.config.ConfigQA;
import slotegrator.project.helper.ApiHelper;
import slotegrator.project.helper.AssetHelper;
import slotegrator.project.model.ClientCredentialsGrantRequest;
import slotegrator.project.model.ClientCredentialsGrantResponse;
import slotegrator.project.model.player.PlayerRequest;
import slotegrator.project.model.player.PlayerRequestFactory;
import slotegrator.project.model.player.PlayerResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {
    private ClientCredentialsGrantRequest clientTokenRequest;
    private ClientCredentialsGrantResponse clientTokenResponse;
    private PlayerResponse playerResponseModel;
    private PlayerRequest playerRequestModel;
    private Response response;
    private static final String BASIC_USER_TOKEN = ApiHelper.generateBasicToken(ConfigQA.getInstance().getUserName(), null);

    @BeforeMethod(onlyForGroups = "guestToken")
    private void prepareDataToGetGuestToken() {
        clientTokenRequest = ClientCredentialsGrantRequest.builder()
                .clientCredentialsType()
                .defaultScore()
                .build();
    }

    @BeforeMethod(onlyForGroups = "playerToken")
    private void prepareDataToGetPlayerToken() {
        clientTokenRequest = ClientCredentialsGrantRequest.builder()
                .passwordGrandType()
                .username(playerRequestModel.getUsername())
                .password(playerRequestModel.getPasswordChange())
                .build();
    }

    @BeforeMethod(onlyForGroups = "preparedFullClient")
    private void prepareFullPlayer() {
        playerRequestModel = PlayerRequestFactory.fullRandomPlayerRequest();
    }

    @Test(groups = "guestToken")
    public void guestTokenTest() {
        response = ApiHelper.postClientCredentialsGrant(BASIC_USER_TOKEN, clientTokenRequest);
        AssetHelper.checkStatus(response, HttpStatus.SC_OK);
        clientTokenResponse = response.as(ClientCredentialsGrantResponse.class);
        checkTokenResponse();
    }

    @Test(groups = "preparedFullClient", dependsOnMethods = "guestTokenTest")
    public void createNewPlayerTest() {
        response = ApiHelper.postPlayer(clientTokenResponse.getAccessToken(), playerRequestModel);
        AssetHelper.checkStatus(response, HttpStatus.SC_CREATED);
        response.then().body(matchesJsonSchemaInClasspath("clientResponseSchema.json"));
        playerResponseModel = response.as(PlayerResponse.class);
        checkPlayer();
    }

    @Test(dependsOnMethods = {"guestTokenTest", "createNewPlayerTest"}, groups = "playerToken")
    public void authorizationTest() {
        response = ApiHelper.postClientCredentialsGrant(BASIC_USER_TOKEN, clientTokenRequest);
        AssetHelper.checkStatus(response, HttpStatus.SC_OK);
        clientTokenResponse = response.as(ClientCredentialsGrantResponse.class);
        checkTokenResponse();
    }

    @Test(dependsOnMethods = {"authorizationTest", "guestTokenTest", "createNewPlayerTest"})
    public void getPlayerDataTest() {
        response = ApiHelper.getPlayerInfo(clientTokenResponse.getAccessToken(), playerResponseModel.getId());
        AssetHelper.checkStatus(response, HttpStatus.SC_OK);
        response.then().body(matchesJsonSchemaInClasspath("clientResponseSchema.json"));
        playerResponseModel = response.as(PlayerResponse.class);
        checkPlayer();
    }


    @Test(dependsOnMethods = {"authorizationTest", "guestTokenTest", "createNewPlayerTest"})
    public void getIncorrectDataTest() {
        response = ApiHelper.getPlayerInfo(clientTokenResponse.getAccessToken(), -1L);
        AssetHelper.checkStatus(response, HttpStatus.SC_NOT_FOUND);
    }

    private void checkPlayer() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(playerResponseModel.getUsername()).isEqualTo(playerRequestModel.getUsername());
        softAssertions.assertThat(playerResponseModel.getEmail()).isEqualTo(playerRequestModel.getEmail());
        softAssertions.assertThat(playerResponseModel.getName()).isEqualTo(playerRequestModel.getName());
        softAssertions.assertThat(playerResponseModel.getSurname()).isEqualTo(playerRequestModel.getSurname());
        softAssertions.assertAll();
    }

    private void checkTokenResponse() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(clientTokenResponse.getTokenType()).isEqualTo("Bearer");
        softAssertions.assertThat(clientTokenResponse.getAccessToken()).isNotNull();
        softAssertions.assertAll();
    }
}
