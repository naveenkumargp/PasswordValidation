package com.paasword.validation.service;

import java.util.List;

public interface PasswordService {

  public String validatePasswordForAtleastThreeConditions();

  public String validatePasswordForNotHavingLowerCaseLetters();

  public String validatePasswordForEightChars();

  public String validatePasswordForNotNull();

  public String validatePasswordForUpperCaseLetter();

  public String validatePasswordForLowerCaseLetter();

  public String validatePasswordForNumber();

  public List<String> getResultStatusMessages();
}
