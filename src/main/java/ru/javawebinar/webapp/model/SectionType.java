package ru.javawebinar.webapp.model;

import java.io.Serializable;

public enum SectionType implements Serializable {
  ACHIEVEMENT("Достижения"),
  EDUCATION("Образование"),
  EXPIRIENCE("Опыт работы"),
  OBJECTIVE("Позиция"),
  QUALIFICATIONS("Квалификация");

  public static long serialVersionUID = 1L;

  private String title;

  SectionType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
