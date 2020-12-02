package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.model.ApplicationPropertiesTagName;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
    private static final String VALUE = "value";
    private static final String KEY = "key";
    @Value("${" + ApplicationPropertiesTagName.SERVERPORT + "}")
    private String serverPort;
    @Value("${" + ApplicationPropertiesTagName.h2ConsoleEnabled + "}")
    private String h2ConsoleEnabled;
    @Value("${" + ApplicationPropertiesTagName.loggingLevel + "}")
    private String loggingLevel;
    @Value("${" + ApplicationPropertiesTagName.hibernateDdlAuto + "}")
    private String hibernateDdlAuto;
    @Value("${" + ApplicationPropertiesTagName.springDataSourceUrl + "}")
    private String springDataSourceUrl;
    @Value("${" + ApplicationPropertiesTagName.showSql + "}")
    private String showSql;
    @Value("${" + ApplicationPropertiesTagName.hibernateFormatSql + "}")
    private String hibernateFormatSql;
    @Value("${" + ApplicationPropertiesTagName.hibernateType + "}")
    private String hibernateType;

    @Value("${" + ApplicationPropertiesTagName.springProfilesActive + "}")
    private String springProfiles;

    public String getInfo() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(KEY, ApplicationPropertiesTagName.SERVERPORT);
        jsonObject.put(VALUE, serverPort);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put(KEY, ApplicationPropertiesTagName.loggingLevel);
        jsonObject.put(VALUE, loggingLevel);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put(KEY, ApplicationPropertiesTagName.hibernateDdlAuto);
        jsonObject.put(VALUE, hibernateDdlAuto);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put(KEY, ApplicationPropertiesTagName.springDataSourceUrl);
        jsonObject.put(VALUE, springDataSourceUrl);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put(KEY, ApplicationPropertiesTagName.showSql);
        jsonObject.put(VALUE, showSql);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put(KEY, ApplicationPropertiesTagName.hibernateFormatSql);
        jsonObject.put(VALUE, hibernateFormatSql);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put(KEY, ApplicationPropertiesTagName.hibernateType);
        jsonObject.put(VALUE, hibernateType);
        jsonArray.add(jsonObject);



        return jsonArray.toJSONString();
    }
    public String getSpringProfiles(){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject = new JSONObject();
        jsonObject.put(KEY, ApplicationPropertiesTagName.springProfilesActive);
        jsonObject.put(VALUE, springProfiles);
        jsonArray.add(jsonObject);
        return jsonArray.toJSONString();
    }
}
