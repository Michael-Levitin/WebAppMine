package ru.javawebinar.webapp.model;

import java.util.Date;
import java.util.List;

public class Organisation {
  private Link link;
  List<Period> periods;

  public static class Period {
    private Date startDate;
    private Date endDate;
    private String position;
    private String content;

    public Period() {
    }

    public Period(Date startDate, Date endDate, String position, String content) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.position = position;
      this.content = content;
    }
  }

}
