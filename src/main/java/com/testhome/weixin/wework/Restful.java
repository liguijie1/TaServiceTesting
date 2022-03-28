package com.testhome.weixin.wework;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Restful {

    HashMap<String, Object> query =new HashMap<String, Object>();
    public RequestSpecification requestSpecification=given();

    //提供各种各样的数据调用
    public Response send(){
        //遍历所有数据进行循环，为某一个参数
        requestSpecification = given().log().all();
        query.entrySet().forEach(entry->{
            requestSpecification.queryParam(entry.getKey(),entry.getValue());

        });

        return requestSpecification.when().request("get","baodu.com");
    }

    public static String template(String path,HashMap<String ,Object>map){

        DocumentContext documentContext = JsonPath.parse(Restful.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry ->{
            documentContext.set(entry.getKey(),entry.getValue());
        });
        return documentContext.jsonString();
    }

    //给一个模板数据,引擎,从har中读取请求,并发送
    public Response templateFromhar(String path, String urlRegex, HashMap<String ,Object>map){

        //从har中读取请求，进行更新
        DocumentContext documentContext = JsonPath.parse(Restful.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry ->{
            documentContext.set(entry.getKey(),entry.getValue());
        });

        //从文件中读出想要数据
        String method = documentContext.read("method");
        String url = documentContext.read("url");

        //取到值后改值
        return requestSpecification.when().request(method,url);

    }

    public Response templateFromYaml(String path, HashMap<String ,Object>map){
        return null;

    }

}
