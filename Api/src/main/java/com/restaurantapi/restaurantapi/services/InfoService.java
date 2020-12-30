package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.ApplicationPropertiesTagNameDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoService {
  private static final String VALUE = "value";
  private static final String KEY = "key";

  @Value("${" + ApplicationPropertiesTagNameDTO.SERVER_PORT + "}")
  private String serverPort;

  @Value("${" + ApplicationPropertiesTagNameDTO.SPRING_H_2_CONSOLE_ENABLED + "}")
  private String h2ConsoleEnabled;

  @Value("${" + ApplicationPropertiesTagNameDTO.LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_CONTEXT + "}")
  private String loggingLevel;

  @Value("${" + ApplicationPropertiesTagNameDTO.SPRING_JPA_HIBERNATE_DDL_AUTO + "}")
  private String hibernateDdlAuto;

  @Value("${" + ApplicationPropertiesTagNameDTO.SPRING_DATASOURCE_URL + "}")
  private String springDataSourceUrl;

  @Value("${" + ApplicationPropertiesTagNameDTO.SPRING_JPA_SHOW_SQL + "}")
  private String showSql;

  @Value("${" + ApplicationPropertiesTagNameDTO.HIBERNATE_FORMAT_SQL + "}")
  private String hibernateFormatSql;

  @Value("${" + ApplicationPropertiesTagNameDTO.HIBERNATE_TYPE + "}")
  private String hibernateType;

  @Value("${" + ApplicationPropertiesTagNameDTO.SPRING_PROFILES_ACTIVE + "}")
  private String springProfiles;

  @Autowired private ApplicationContext context;

  public String getInfo() {
    JSONArray jsonArray = new JSONArray();
    JSONObject jsonObject = new JSONObject();

    jsonObject.put(KEY, ApplicationPropertiesTagNameDTO.SERVER_PORT);
    jsonObject.put(VALUE, serverPort);
    jsonArray.add(jsonObject);

    jsonObject = new JSONObject();
    jsonObject.put(KEY, ApplicationPropertiesTagNameDTO.LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_CONTEXT);
    jsonObject.put(VALUE, loggingLevel);
    jsonArray.add(jsonObject);

    jsonObject = new JSONObject();
    jsonObject.put(KEY, ApplicationPropertiesTagNameDTO.SPRING_JPA_HIBERNATE_DDL_AUTO);
    jsonObject.put(VALUE, hibernateDdlAuto);
    jsonArray.add(jsonObject);

    jsonObject = new JSONObject();
    jsonObject.put(KEY, ApplicationPropertiesTagNameDTO.SPRING_DATASOURCE_URL);
    jsonObject.put(VALUE, springDataSourceUrl);
    jsonArray.add(jsonObject);

    jsonObject = new JSONObject();
    jsonObject.put(KEY, ApplicationPropertiesTagNameDTO.SPRING_JPA_SHOW_SQL);
    jsonObject.put(VALUE, showSql);
    jsonArray.add(jsonObject);

    jsonObject = new JSONObject();
    jsonObject.put(KEY, ApplicationPropertiesTagNameDTO.HIBERNATE_FORMAT_SQL);
    jsonObject.put(VALUE, hibernateFormatSql);
    jsonArray.add(jsonObject);

    jsonObject = new JSONObject();
    jsonObject.put(KEY, ApplicationPropertiesTagNameDTO.HIBERNATE_TYPE);
    jsonObject.put(VALUE, hibernateType);
    jsonArray.add(jsonObject);

    return jsonArray.toJSONString();
  }

  public String getSpringProfiles() {
    JSONArray jsonArray = new JSONArray();
    JSONObject jsonObject;
    jsonObject = new JSONObject();
    jsonObject.put(KEY, ApplicationPropertiesTagNameDTO.SPRING_PROFILES_ACTIVE);
    jsonObject.put(VALUE, springProfiles);
    jsonArray.add(jsonObject);
    return jsonArray.toJSONString();
  }

  public List<String> getBeanNames() {

    List<String> beanNamesList = new ArrayList<>();

    for (String beanName : context.getBeanDefinitionNames()) {
      beanNamesList.add(beanName);
    }

    return beanNamesList;
  }
}
