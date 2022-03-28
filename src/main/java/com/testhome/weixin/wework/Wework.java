package com.testhome.weixin.wework;

import io.restassured.RestAssured;

public class Wework {

    private static String token;

    public static String getWeworkToken(String secret){

        return  RestAssured.given().log().all()
                .queryParam("corpid", WeworkConfig.getInstance().corpid)
                .queryParam("corpsecret", secret)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all().statusCode(200)
                .extract().path("access_token");
    }


    public static String gettoken(){
        //todo 支持两种不同的token
        if(token==null){
            token=getWeworkToken(WeworkConfig.getInstance().contactsecret);

        }
        return token;


    }
}
