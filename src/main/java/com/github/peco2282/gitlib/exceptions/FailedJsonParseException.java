package com.github.peco2282.gitlib.exceptions;

@SuppressWarnings("unused")
public class FailedJsonParseException extends RuntimeException {
  public FailedJsonParseException() {super();}
  public FailedJsonParseException(String message) {super(message);}
  public FailedJsonParseException(Exception e) {super(e);}
  public FailedJsonParseException(String message, Exception e) {super(message, e);}
}
