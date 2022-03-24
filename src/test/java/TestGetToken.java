import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class TestGetToken {

    @Test
    public void testgetToken(){

        RestAssured.given().log().all()
                .queryParam("corpid","ww659b2f5fc83da2a2")
                .queryParam("corpsecret","vOW9aLAGSe1zfGdGojFaSVhIOU_TRp0jQ44-to5OBYE")
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all().statusCode(200).body("errcode",equalTo(0));

/**/
    }
}
