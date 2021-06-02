package com.example.convertdata.domain.entities.types;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderState {
  DRAFT,
  NEW,
  CONFIRMED,
  SHIPPING,
  COMPLETED,
  FINISHED,
  CANCELED;

}
