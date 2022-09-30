package com.example.shopping.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String msg, Throwable t) {
      super(msg, t);
  }

  public BadRequestException(String msg) {
      super(msg);
  }

}