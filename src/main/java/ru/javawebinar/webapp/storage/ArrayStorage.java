package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class ArrayStorage extends AbstractStorage {

  private static final int LIMIT = 100;
  private Resume[] array = new Resume[LIMIT];
  private int size = 0;

  @Override
  public void doClear() {
    Arrays.fill(array, null);
    size = 0;
  }

  @Override
  public void doSave(Resume r) {
      array[size++] = r;
  }

  @Override
  public void doUpdate(Resume r) {
    int idx = getIndex(r.getUuid());
    array[idx] = r;
  }

  @Override
  public Resume doLoad(String uuid) {
    int idx = getIndex(uuid);
    return array[idx];
  }

  @Override
  public void doDelete(String uuid) {
    int idx = getIndex(uuid);
    int numMoved = size - idx - 1;
    if (numMoved > 0)
      System.arraycopy(array, idx+1, array, idx, numMoved);
    array[--size] = null; // Clear to let GC to do it's magic
  }

  @Override
  protected List<Resume> doGetAll() {
    return Arrays.asList(Arrays.copyOf(array, size));
  }

  @Override
  public int size() {
    int arraySize = 0;
    for (Resume resume : array) {
      if (resume == null || resume.getUuid() == null)
        break;
      arraySize++;
    }
    return arraySize;
  }

  public int getIndex(String uiid) {
      for (int i = 0; i < array.length; i++) {
        if (array[i] != null)
          if (array[i].getUuid().equals(uiid))
            return i;
      }
      return -1;
  }

  @Override
  protected boolean exist(String uiid) {
    return getIndex(uiid) != -1;
  }
}
