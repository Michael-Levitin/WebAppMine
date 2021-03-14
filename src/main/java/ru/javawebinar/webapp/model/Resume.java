package ru.javawebinar.webapp.model;

import org.jetbrains.annotations.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume>{
  private String uuid;
  private String fullName;
  private String homePage;
  private String location;
  private List<Contact> contacts = new LinkedList<>();
  private List<Section> sections = new LinkedList<>();

  public Resume(String fullName, String location) {
    this(UUID.randomUUID().toString(), fullName, location);
  } // generates unique number/string

  public Resume(String uuid, String fullName, String location) {
    this.uuid = uuid;
    this.fullName = fullName;
    this.location = location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Resume resume = (Resume) o;
    return Objects.equals(uuid, resume.uuid) &&
            Objects.equals(fullName, resume.fullName) &&
            Objects.equals(homePage, resume.homePage) &&
            Objects.equals(location, resume.location) &&
            Objects.equals(contacts, resume.contacts) &&
            Objects.equals(sections, resume.sections);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, fullName, homePage, location, contacts, sections);
  }

  public String getUuid() {
    return uuid;
  }

  public String getFullName() {
    return fullName;
  }

  public String getHomePage() {
    return homePage;
  }

  public String getLocation() {
    return location;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public List<Section> getSection() {
    return sections;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  public void setSections(List<Section> sections) {
    this.sections = sections;
  }

  public void addSection(Section s) {
    sections.add(s);
  }

  public void addContact(Contact c) {
    contacts.add(c);
  }

  @Override
  public int compareTo(@NotNull Resume o) {
    return fullName.compareTo(o.fullName);
  }
}
