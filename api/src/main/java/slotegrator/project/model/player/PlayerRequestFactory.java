package slotegrator.project.model.player;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;

import static slotegrator.project.helper.DataHelper.getRandomName;
import static slotegrator.project.helper.DataHelper.getRandomSurName;

public class PlayerRequestFactory {

    public static PlayerRequest fullRandomPlayerRequest() {
        PlayerRequest request = new PlayerRequest();
        request.setUsername(RandomStringUtils.random(15, true, false));
        request.setPasswordChange(new String(Base64.encodeBase64(RandomStringUtils.random(10, true, true)
                .getBytes(StandardCharsets.US_ASCII))));
        request.setPasswordRepeat(request.getPasswordChange());
        request.setEmail(RandomStringUtils.random(8, true, true) + "@example.com");
        request.setName(getRandomName());
        request.setSurname(getRandomSurName());
        //Все перепробовал для этого параметра, сервис его никак не хочет воспринимать
        //Выдает ошибку {
        //    "name": "Forbidden",
        //    "message": "Invalid currency.",
        //    "code": 2011,
        //    "status": 403
        //}
        //request.setCurrencyCode(CommonCollectionUtils.getRandomCollectionElement(Currency.getAvailableCurrencies()).getCurrencyCode());
        return request;
    }
}
