package com.paasword.validation;

import com.paasword.validation.service.PasswordServiceImpl;

import java.util.ArrayList;

public class Driver {
  public static void main(String[] args) {
    String password = "Welcome@123";
    PasswordServiceImpl passwordService = new PasswordServiceImpl(new ArrayList<String>());
    System.out.println(passwordService.validatePasswordForAtleastThreeConditions(password));
  }
}
