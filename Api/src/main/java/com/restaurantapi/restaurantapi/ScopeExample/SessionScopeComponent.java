package com.restaurantapi.restaurantapi.scopeExample;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class SessionScopeComponent implements Serializable {

  private static int instanceCount;

  public SessionScopeComponent() {
    instanceCount++;
  }

  public void printInstanceInfo() {
    System.out.println("SessionScope Count" + instanceCount);
  }
}
