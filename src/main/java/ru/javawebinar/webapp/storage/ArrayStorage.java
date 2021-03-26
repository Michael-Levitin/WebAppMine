package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class ArrayStorage extends AbstractStorage<Integer> {
//  Parametrisation(?) of C with Integer, we need int, but primitive types aren't allowed

  private static final int LIMIT = 100;
  private Resume[] array = new Resume[LIMIT];
  private int size = 0;
//  private int idx;

  @Override
  public void doClear() {
    Arrays.fill(array, null);
    size = 0;
  }

  @Override
  protected void doSave(Integer ctx, Resume r) {
      array[size++] = r;
  }

  @Override
  public void doUpdate(Integer ctx, Resume r) {
    int idx = getContext(r.getUuid());
    array[idx] = r;
  }

  @Override
  public Resume doLoad(Integer index) {
//    int idx = getIndex(uuid);
    return array[index];
  }

  @Override
  public void doDelete(Integer idx) {
//    int idx = getContext(uuid);
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

  @Override
  protected Integer getContext(String uiid) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] != null)
        if (array[i].getUuid().equals(uiid))
          return i;
    }
    return -1;
  }

  @Override
  protected boolean exist(Integer idx) {
    // TODO rename to isExist later
    return idx!=-1;
  }
}
