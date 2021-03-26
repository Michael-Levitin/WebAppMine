package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {

  private Map<String, Resume> map = new HashMap<>();
//  SortedMap<String, String> fileExtensions = new TreeMap<>(String::compareToIgnoreCase);
//  SortedMap<String, String> fileExtensions = new TreeMap<>(Comparator.reverseOrder());

  @Override
  public void doClear() {
    map.clear();
  }

  @Override
  protected String getContext(String uiid) {
    return uiid;
  }

  @Override
  protected boolean exist(String context) {
    return (map.containsKey(context));
  }

  @Override
  protected void doSave(String uiid, Resume r) {
    map.put(uiid, r);
  }

  @Override
  protected void doUpdate(String uiid, Resume r) {
    map.put(uiid, r);
  }

  @Override
  protected Resume doLoad(String uiid) {
    return map.get(uiid);
  }

  @Override
  protected void doDelete(String uiid) {
    map.remove(uiid);
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
