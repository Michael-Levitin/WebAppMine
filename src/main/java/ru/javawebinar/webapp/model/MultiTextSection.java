package ru.javawebinar.webapp.model;

import java.io.Serializable;
import java.util.List;

public class MultiTextSection extends Section implements Serializable {
  public static long serialVersionUID = 1L;
  private List<String> values;
}
