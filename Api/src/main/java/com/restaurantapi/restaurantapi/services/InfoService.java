package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.model.ApplicationPropertiesTagName;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Value("${"+ ApplicationPropertiesTagName.serverport +"}")
    private String serverPort;
    @Value("${"+ ApplicationPropertiesTagName.h2ConsoleEnabled +"}")
    private String h2ConsoleEnabled;
    @Value("${"+ ApplicationPropertiesTagName.loggingLevel +"}")
    private String loggingLevel;
    @Value("${"+ ApplicationPropertiesTagName.hibernateDdlAuto +"}")
    private String hibernateDdlAuto;
    @Value("${"+ ApplicationPropertiesTagName.springDataSourceUrl +"}")
    private String springDataSourceUrl;
    @Value("${"+ ApplicationPropertiesTagName.showSql +"}")
    private String showSql;
    @Value("${"+ ApplicationPropertiesTagName.hibernateFormatSql +"}")
    private String hibernateFormatSql;
    @Value("${"+ ApplicationPropertiesTagName.hibernateType +"}")
    private String hibernateType;


    public String getInfo(){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("key",ApplicationPropertiesTagName.serverport);
        jsonObject.put("value",serverPort);
        jsonArray.add(jsonObject);

        jsonObject=new JSONObject();
        jsonObject.put("key",ApplicationPropertiesTagName.loggingLevel);
        jsonObject.put("value",loggingLevel);
        jsonArray.add(jsonObject);

        jsonObject=new JSONObject();
        jsonObject.put("key",ApplicationPropertiesTagName.hibernateDdlAuto);
        jsonObject.put("value",hibernateDdlAuto);
        jsonArray.add(jsonObject);

        jsonObject=new JSONObject();
        jsonObject.put("key",ApplicationPropertiesTagName.springDataSourceUrl);
        jsonObject.put("value",springDataSourceUrl);
        jsonArray.add(jsonObject);

        jsonObject=new JSONObject();
        jsonObject.put("key",ApplicationPropertiesTagName.showSql);
        jsonObject.put("value",showSql);
        jsonArray.add(jsonObject);

        jsonObject=new JSONObject();
        jsonObject.put("key",ApplicationPropertiesTagName.hibernateFormatSql);
        jsonObject.put("value",hibernateFormatSql);
        jsonArray.add(jsonObject);

        jsonObject=new JSONObject();
        jsonObject.put("key",ApplicationPropertiesTagName.hibernateType);
        jsonObject.put("value",hibernateType);
        jsonArray.add(jsonObject);


        return jsonArray.toJSONString();
    }
}
