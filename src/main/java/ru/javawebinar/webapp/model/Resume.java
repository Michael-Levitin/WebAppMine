package ru.javawebinar.webapp.model;

import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class Resume implements Comparable<Resume> { // { //
  private String uuid;
  private String fullName;
  private String homePage;
  private String location;
  private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
  // Map with Enum values as keys (supply Enum class)
  private List<Section> sections = new LinkedList<>();

  public Resume(String fullName, String location) {
    // base constructor
    this(UUID.randomUUID().toString(), fullName, location);
  } // generates unique number/string

  public Resume(String uuid, String fullName, String location) {
    this.uuid = uuid;
    this.fullName = fullName;
    this.location = location;
  }

  public Resume() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Resume resume = (Resume) o;
    return uuid.equals(resume.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
  // hashcode should be calculated from immutable field


//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (o == null || getClass() != o.getClass()) return false;
//    Resume resume = (Resume) o;
//    return uuid.equals(resume.uuid) &&
//            Objects.equals(fullName, resume.fullName) &&
//            Objects.equals(homePage, resume.homePage) &&
//            Objects.equals(location, resume.location) &&
//            Objects.equals(contacts, resume.contacts) &&
//            Objects.equals(sections, resume.sections);
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(uuid, fullName, homePage, location, contacts, sections);
//  }

  @Override
  public String toString() {
    return "Resume{" +
            "uuid='" + uuid + '\'' +
            ", fullName='" + fullName + '\'' +
            ", homePage='" + homePage + '\'' +
            ", location='" + location + '\'' +
            ", contacts=" + contacts +
            ", sections=" + sections +
            '}';
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

  public  String getContacts(ContactType type) {
    return contacts.get(type);
  }

  public List<Section> getSection() {
    return sections;
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

  public void setContacts(Map<ContactType, String> contacts) {
    this.contacts = contacts;
  }

  public void setSections(List<Section> sections) {
    this.sections = sections;
  }

  public void addSection(Section s) {
    sections.add(s);
  }

  public void addContact(ContactType type, String value) {
    contacts.put(type, value);
  }

//  @Override
  public int compareTo(@NotNull Resume o) {
    return fullName.compareTo(o.fullName);
  }
}
