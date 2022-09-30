package com.example.shopping.filters;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterOperationEnum {

  EQUALS("eq"),
  NOT_EQUALS("neq"),
  GREATER_THAN("gt"),
  LESS_THAN("lt"),
  IN("in"),
  LIKE("like");


  private final String value;

  FilterOperationEnum(String value) {
      this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
      return String.valueOf(value);
  }

  public static FilterOperationEnum fromValue(String value) {
      for (FilterOperationEnum op : FilterOperationEnum.values()) {

          //Case insensitive operation name
          if (String.valueOf(op.value).equalsIgnoreCase(value)) {
              return op;
          }
      }
      return null;
  }

}