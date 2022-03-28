package com.testhome.weixin.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

class MemberTest{

    static Member member;

    @BeforeAll
    static void setUp() {
        if (member==null){
            member = new Member();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "liji","llli","ywhdj"})
    void create(String name) {

        String nameNew = name+member.random;
        String random = String.valueOf(System.currentTimeMillis()).substring(5+0,5+8);
        HashMap<String ,Object> map = new HashMap<>();
        map.put("userid",name+nameNew);
        map.put("name", name +nameNew);
        map.put("department", Arrays.asList(1,2));
        map.put("mobile","152"+random);
        map.put("email",random+"@163.com");
        member.create(map).then().statusCode(200).body("errcode",equalTo(0));

    }
}