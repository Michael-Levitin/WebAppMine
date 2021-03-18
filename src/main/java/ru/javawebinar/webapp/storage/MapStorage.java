package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

  private TreeMap<String, Resume> map = new TreeMap<>();
//  SortedMap<String, Resume> fileExtensions = new TreeMap<>(String::compareToIgnoreCase);
//  SortedMap<String, String> fileExtensions = new TreeMap<>(Comparator.reverseOrder());

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public void doSave(Resume r) {
    if (map.containsKey(r.getUuid()))
      throw new WebAppException("Resume" + r.getUuid() + "already exists");
    map.put(r.getUuid(), r);
  }

  @Override
  public void update(Resume r) {
    logger.info("Updated resume with uiid = " + r.getUuid());
    if (! map.containsKey(r.getUuid()))
      throw new WebAppException("Resume with uiid = " + r.getUuid() + "doesn't exists");
    map.remove(r.getUuid());
    map.put(r.getUuid(), r);
  }

  @Override
  public Resume load(String uuid) {
    logger.info("Loading resume with uiid = " + uuid);
    if (! map.containsKey(uuid))
      throw new WebAppException("Resume with uiid = " + uuid + " doesn't exists");
    return map.get(uuid);
  }

  @Override
  public void delete(String uuid) {
    logger.info("Deleting resume with uiid = " + uuid);
    if (! map.containsKey(uuid))
      throw new WebAppException("Resume with uiid = " + uuid + " doesn't exists");
    map.remove(uuid);
  }

  @Override
  public Collection<Resume> getAllSorted() {
    return (Collection<Resume>) map;
  }

  @Override
  public int size() {
    return map.size();
  }
}
