package com.testhome.weixin.wework.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    //创建一个随机数
    String random = String.valueOf(System.currentTimeMillis());

    Department department;

    @BeforeEach
     void setUp() {

        if (department==null){
            department = new Department();
            department.deleteAll();
        }

    }

    @Test
    void list() {


        //department.list("").then().statusCode(200).body("department.name[0]",equalTo("java自动化测试"));
        department.list("2").then().statusCode(200)
                .body("department.name[0]",equalTo("测试1"))
                .body("department.id[0]",equalTo(2));

    }

    @Test
    void createDm() {

        //department.createDm("test7","1").then().body("errcode",equalTo(60008));
       // department.createDm("test7","1").then().body("errcode",equalTo(60008));

        department.createDm("广州你好","1").then().body("errcode",equalTo(0));


    }

    @Test
    void deleteDM() {

        //department.deleteDM("5").then().statusCode(200).body("errmsg",equalTo("deleted"));

        String nameOld="testtt4";
        department.createDm(nameOld,"1");
        //String id=String.valueOf(department.list("").path("department.find{ it.name=='"+nameOld+"' }.id"));
        String id=(department.list("").path("department.find{ it.name=='"+nameOld+"' }.id")).toString();
        department.deleteDM(id).then().body("errcode",equalTo(0));

    }

    @Test
    void updateDM(){

        //创建部门后接着更新，创建新部门，获取新的部门ID才能进行部门的更新

        String nameOld="testtt4"+random;
        department.createDm(nameOld,"1");
        //String id=String.valueOf(department.list("").path("department.find{ it.name=='"+nameOld+"' }.id"));
        String id=(department.list("").path("department.find{ it.name=='"+nameOld+"' }.id")).toString();
        department.updateDM("testsss2"+random,id).then().body("errcode",equalTo(0));


        //department.createDm("test8","1").path("depart")
        //department.updateDM("4","test3","ljdfhs").then().body("errmsg",equalTo("updated"));


    }

    @Test
    void create() {

        HashMap<String, Object>map = new HashMap<String, Object>(){
            {
                put("name",String.format("lijsaa"+random));
                put("parentid","1");
        }

        };
        department.create(map).then().body("errcode",equalTo(0));
    }

    @Test
    void deleteAll() {

        department.deleteAll();
    }
}