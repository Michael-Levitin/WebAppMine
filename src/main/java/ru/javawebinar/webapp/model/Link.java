package ru.javawebinar.webapp.model;
import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {

  public static Link EMPTY = new Link();
  public static long serialVersionUID = 1L;
  private String name;
  private String url;

  public Link() { // calling base constructor
    this("", null);
  }

  public Link(Link link)
  { this(link.name, link.url);
  }

  public Link(String name, String url) {
    this.name = name;
    this.url = url;
  }

  public static Link empty()
  { return EMPTY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Link link = (Link) o;
    return Objects.equals(name, link.name) &&
            Objects.equals(url, link.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, url);
  }

  @Override
  public String toString() {
    return "Link{" +
            "name='" + name + '\'' +
            ", url='" + url + '\'' +
            '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
