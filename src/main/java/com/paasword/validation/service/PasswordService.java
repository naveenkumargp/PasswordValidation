package com.paasword.validation.service;

import java.util.List;

public interface PasswordService {

  public String validatePasswordForAtleastThreeConditions(String password);

  public String validatePasswordForNotHavingLowerCaseLetters(String password);

  public String validatePasswordForEightChars(String str);

  public String validatePasswordForNotNull(String str);

  public String validatePasswordForUpperCaseLetter(String str);

  public String validatePasswordForLowerCaseLetter(String str);

  public String validatePasswordForNumber(String str);

  public List<String> getResultStatusMessages();
}
