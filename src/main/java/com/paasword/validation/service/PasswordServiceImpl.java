package com.paasword.validation.service;

import com.paasword.validation.exception.PasswordValidationException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordServiceImpl implements PasswordService {

  List<String> resultList;
  private String password;

  public PasswordServiceImpl(List<String> resultList, String password) {
    this.resultList = resultList;
    this.password =password;
  }

  @Override
  public String validatePasswordForAtleastThreeConditions() {
    validatePasswordForEightChars();
    validatePasswordForNotNull();
    validatePasswordForLowerCaseLetter();
    validatePasswordForUpperCaseLetter();
    validatePasswordForNumber();
    if (resultList.size() <= 2) {
      return "valid";
    }
    return "not valid";
  }

  @Override
  public String validatePasswordForNotHavingLowerCaseLetters() {
    String status = validatePasswordForLowerCaseLetter();
    if (status == null || !(status.equalsIgnoreCase("valid"))) {
      return "not valid";
    }
    return "valid";
  }

  @Override
  public String validatePasswordForEightChars() {
    String status = null;
    try {
      status = checkForEightChars();
    } catch (PasswordValidationException e) {
      resultList.add(e.getMessage());
      return "not valid";
    }
    return status;
  }

  private String checkForEightChars() {
    if (null == password || password.length() <= 8) {
      throw new PasswordValidationException("Password should be more than 8 characters");
    }
    return "valid";
  }

  @Override
  public String validatePasswordForNotNull() {
    String status = null;
    try {
      status = checkForNotNull();
    } catch (PasswordValidationException e) {
      resultList.add(e.getMessage());
      return "not valid";
    }
    return status;
  }

  private String checkForNotNull() {
    if (password == null) {
      throw new PasswordValidationException("Password should not be null");
    }
    return "valid";
  }

  @Override
  public String validatePasswordForUpperCaseLetter() {
    String status = null;
    String regex = "(?=.*[A-Z]).+$";
    String message = "Password Does not contain atleast one upper case letter";

    try {
      status = checkForValidCharacters(regex, message);
    } catch (PasswordValidationException e) {
      resultList.add(e.getMessage());
      return "not valid";
    }
    return status;
  }

  @Override
  public String validatePasswordForLowerCaseLetter() {
    String status = null;
    String regex = "(?=.*[a-z]).+$";
    String message = "Password Does not contain atleast one lower case letter";
    try {
      status = checkForValidCharacters(regex, message);
    } catch (PasswordValidationException e) {
      resultList.add(e.getMessage());
      return "not valid";
    }
    return status;
  }

  @Override
  public String validatePasswordForNumber() {
    String status = null;
    String regex = "(?=.*[0-9]).+$";
    String message = "Password Does not contain atleast one number";
    try {
      status = checkForValidCharacters(regex, message);
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

  private String checkForValidCharacters(String regex, String message) {
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
