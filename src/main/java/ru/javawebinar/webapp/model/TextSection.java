package ru.javawebinar.webapp.model;

import java.io.Serializable;
import java.util.List;

public class TextSection extends Section implements Serializable{

  public static long serialVersionUID = 1L;

  private String title;
  private String comment;
}
