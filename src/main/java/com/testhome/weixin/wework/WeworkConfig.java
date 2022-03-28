package com.testhome.weixin.wework;

public class WeworkConfig {


    //应用ID
    public String agentId="1000002";
    //
    public String secret="vOW9aLAGSe1zfGdGojFaSVhIOU_TRp0jQ44-to5OBYE";
    //通讯录
    public String contactsecret="RgB1fIqsuLng72-jztKDr5VPPdPvGX8fBCMRrBV66_Y";
    //企业ID
    public String corpid="ww659b2f5fc83da2a2";

    private static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance(){
        if(weworkConfig==null){
            weworkConfig = new WeworkConfig();
        }
        return weworkConfig;
    }

    public static void load(String path){

        //todo : resd from yaml or json

    }


}
