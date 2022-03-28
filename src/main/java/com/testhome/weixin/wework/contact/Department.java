package com.testhome.weixin.wework.contact;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

public class Department extends Contact{

    //获取部门列表
    public Response list(String id){
        Response response =  requestSpecification
                .param("id",id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().extract().response();
        reset();
        return response;
    }

    //创建部门
    public Response createDm(String name, String parentid){

        reset();

        String body=JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json"))
                .set("$.name",name)
                .set("$.parentid",parentid).jsonString();
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().statusCode(200).extract().response();

    }

    public Response create(HashMap<String, Object>map){

        reset();

        DocumentContext documentContext = JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/create.json"));
        map.entrySet().forEach(entry ->{
            documentContext.set(entry.getKey(),entry.getValue());
        });
        return requestSpecification
                .body(documentContext.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().statusCode(200).extract().response();

    }

    //更新部门信息

    public Response updateDM(String name, String id){

        reset();

        String body=JsonPath.parse(this.getClass().getResourceAsStream("/data/update.json"))
                .set("$.name",name)
                .set("$.id",id).jsonString();
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().statusCode(200).extract().response();

    }

    //从har中读取请求，进行更新,引擎
    public Response update(HashMap<String ,Object>map){
       return templateFromhar(
               "demo.json",
               "文件中地址",
               map);
    }


    //删除部门
    public Response deleteDM(String id){
        reset();
        return requestSpecification
                .param("id",id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().statusCode(200).extract().response();

    }

    public Response deleteAll(){
        reset();
        List<Integer> idList = list("").then().log().all().extract().path("department_id");
        System.out.println(idList);
        //循环删除所有数据操作
        idList.forEach(id->delete(id.toString()));
        return null;
    }
}
