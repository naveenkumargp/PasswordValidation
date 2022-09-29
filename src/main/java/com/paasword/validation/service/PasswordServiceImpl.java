package com.paasword.validation.service;

import com.paasword.validation.exception.PasswordValidationException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordServiceImpl implements PasswordService {

  List<String> resultList;

  public PasswordServiceImpl(List<String> resultList) {
    this.resultList = resultList;
  }

  @Override
  public String validatePasswordForAtleastThreeConditions(String password) {
    validatePasswordForEightChars(password);
    validatePasswordForNotNull(password);
    validatePasswordForLowerCaseLetter(password);
    validatePasswordForUpperCaseLetter(password);
    validatePasswordForNumber(password);
    if (resultList.size() <= 2) {
      return "valid";
    }
    return "not valid";
  }

  @Override
  public String validatePasswordForNotHavingLowerCaseLetters(String password) {
    String status = validatePasswordForLowerCaseLetter(password);
    if (status == null || !(status.equalsIgnoreCase("valid"))) {
      return "not valid";
    }
    return "valid";
  }

  @Override
  public String validatePasswordForEightChars(String password) {
    String status = null;
    try {
      status = checkForEightChars(password);
    } catch (PasswordValidationException e) {
      resultList.add(e.getMessage());
      return "not valid";
    }
    return status;
  }

  private String checkForEightChars(String password) {
    if (null == password || password.length() <= 8) {
      throw new PasswordValidationException("Password should be more than 8 characters");
    }
    return "valid";
  }

  @Override
  public String validatePasswordForNotNull(String password) {
    String status = null;
    try {
      status = checkForNotNull(password);
    } catch (PasswordValidationException e) {
      resultList.add(e.getMessage());
      return "not valid";
    }
    return status;
  }

  private String checkForNotNull(String password) {
    if (password == null) {
      throw new PasswordValidationException("Password should not be null");
    }
    return "valid";
  }

  @Override
  public String validatePasswordForUpperCaseLetter(String password) {
    String status = null;
    String regex = "(?=.*[A-Z]).+$";
    String message = "Password Does not contain atleast one upper case letter";

    try {
      status = checkForValidCharacters(regex, password, message);
    } catch (PasswordValidationException e) {
      resultList.add(e.getMessage());
      return "not valid";
    }
    return status;
  }

  @Override
  public String validatePasswordForLowerCaseLetter(String password) {
    String status = null;
    String regex = "(?=.*[a-z]).+$";
    String message = "Password Does not contain atleast one lower case letter";
    try {
      status = checkForValidCharacters(regex, password, message);
    } catch (PasswordValidationException e) {
      resultList.add(e.getMessage());
      return "not valid";
    }
    return status;
  }

  @Override
  public String validatePasswordForNumber(String password) {
    String status = null;
    String regex = "(?=.*[0-9]).+$";
    String message = "Password Does not contain atleast one number";
    try {
      status = checkForValidCharacters(regex, password, message);
    } catch (PasswordValidationException e) {
      resultList.add(e.getMessage());
      return "not valid";
    }
    return status;
  }

  @Override
  public List<String> getResultStatusMessages() {
    return this.resultList;
  }

  private String checkForValidCharacters(String regex, String password, String message) {
    if (password == null) {
      throw new PasswordValidationException(message);
    }
    Pattern p = Pattern.compile(regex);
    Matcher matcher = p.matcher(password);
    if (matcher.matches() == false) {
      throw new PasswordValidationException(message);
    }
    return "valid";
  }
}
