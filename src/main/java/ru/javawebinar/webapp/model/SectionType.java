package ru.javawebinar.webapp.model;

public enum SectionType {
  ACHIEVEMENT("Достижения"),
  EDUCATION("Образование"),
  EXPIRIENCE("Опыт работы"),
  OBJECTIVE("Позиция"),
  QUALIFICATIONS("Квалификация");

  private String title;

  SectionType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
