package ru.javawebinar.webapp.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

//
public final class Resume implements Serializable, Comparable<Resume> { // { //
  public static long serialVersionUID = 1L; //

  private String uuid;
  private String fullName;
  private String homePage;
  private String location;
  private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
  private Map<SectionType, String> sections = new EnumMap<>(SectionType.class);
  // Map with Enum values as keys (supply Enum class)
//  private List<Section> sections = new LinkedList<>();

  public static final Resume EMPTY;

  static {
    EMPTY = new Resume();
//    for (SectionType type : SectionType.values()) {
//
//    }
  }

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

  public Map<ContactType, String> getContacts() {
    return contacts;
  }

  public Map<SectionType, String> getSections() {
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

  public void addSection(SectionType type, String sectionName) {
    sections.put(type, sectionName);
  }

  public void addContact(ContactType type, String value) {
    contacts.put(type, value);
  }

//  @Override
  public int compareTo(@NotNull Resume o) {
    return fullName.compareTo(o.fullName);
  }
}
