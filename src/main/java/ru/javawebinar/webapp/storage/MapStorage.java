package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

  private Map<String, Resume> map = new HashMap<>();
//  SortedMap<String, String> fileExtensions = new TreeMap<>(String::compareToIgnoreCase);
//  SortedMap<String, String> fileExtensions = new TreeMap<>(Comparator.reverseOrder());

  @Override
  public void doClear() {
    map.clear();
  }

  @Override
  protected boolean exist(String uiid) {
    return map.containsKey(uiid);
  }

  @Override
  public void doSave(Resume r) {
  map.put(r.getUuid(), r);
  }

  @Override
  public void doUpdate(Resume r) {
    map.put(r.getUuid(), r);
  }

  @Override
  public Resume doLoad(String uuid) {
    return map.get(uuid);
  }

  @Override
  public void doDelete(String uuid) {
    map.remove(uuid);
  }

  @Override
  public List<Resume> doGetAll() {
    return new ArrayList<>(map.values());
  }

  @Override
  public int size() {
    return map.size();
  }
}