package ru.javawebinar.webapp.model;

public class OrganisationSection extends Section{
  private SectionType type;
  private String sectionName;
  private String sectionDescriptions;

  public OrganisationSection(String sectionName, String sectionDescriptions) {
    this.sectionName = sectionName;
    this.sectionDescriptions = sectionDescriptions;
  }

  



}
