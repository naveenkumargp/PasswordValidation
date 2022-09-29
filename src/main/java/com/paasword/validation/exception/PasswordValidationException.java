package com.paasword.validation.exception;

public class PasswordValidationException extends RuntimeException{

  String message;

  public PasswordValidationException(String message){
    super(message);
    this.message=message;
  }
}
