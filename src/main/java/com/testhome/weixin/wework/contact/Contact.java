package com.testhome.weixin.wework.contact;

import com.testhome.weixin.wework.Restful;
import com.testhome.weixin.wework.Wework;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Contact extends Restful {

    //创建一个随机数
    String random = String.valueOf(System.currentTimeMillis());

    //基类,接口，把token这些全部许要调用的东西处理好
    public Contact(){
        reset();

    }

    public void reset(){
        //初始化自带token等基础内容
        requestSpecification=given()
                .log().all()
                .queryParam("access_token", Wework.gettoken())
                .contentType(ContentType.JSON);//编码格式
                //这里的断言尽量只到http层次即可

    }
}
