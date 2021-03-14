package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

public class ArrayStorage implements IStorrage {

  private static final int LIMIT = 100;
  private Resume[] array = new Resume[LIMIT];
//  protected Logger LOGGER = Logger.getLogger(getClass().getName());
  private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());
  private int size = 0;

  @Override
  public void clear() {
    Arrays.fill(array, null);
  }

  @Override
  public void save(Resume r) {
    LOGGER.info("Saving resume with Uiid" + r.getUuid());
    int idx =  getIndex(r.getUuid());
    if (idx != -1)
//      try {
//        throw new WebAppException("Resume already exists");
//      } catch (WebAppException e) {
//        LOGGER.log(Level.SEVERE, e.getMessage(), e);
//        throw new IllegalStateException(e); // обертывание exception
//      }
        throw new WebAppException("Resume" + r.getUuid() + "already exists");
      array[size++] = r;
  }

  @Override
  public void update(Resume r) {
    LOGGER.info("Updated resume with uiid = " + r.getUuid());
    int idx = getIndex(r.getUuid());
    if (idx == -1)
      throw new WebAppException("Resume with uiid = " + r.getUuid() + "doesn't exists");
    array[idx] = r;
  }

  @Override
  public Resume load(String uuid) {
    LOGGER.info("Loading resume with uiid = " + uuid);
    int idx = getIndex(uuid);
    if (idx == -1)
      throw new WebAppException("Resume with uiid = " + uuid + " doesn't exists");
    return array[idx];
  }

  @Override
  public void delete(String uuid) {
    LOGGER.info("Deleting resume with uiid = " + uuid);
    int idx = getIndex(uuid);
    if (idx == -1)
       throw new WebAppException("Resume with uiid = " + uuid + " doesn't exists");
    int numMoved = size - idx - 1;
    if (numMoved > 0)
      System.arraycopy(array, idx+1, array, idx, numMoved);
    array[--size] = null; // Clear to let GC to do it's magic
  }

  @Override
  public Collection<Resume> getAllSorted() {
    Arrays.sort(array, 0, size);
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

  protected int getIndex(String uuid) {
      for (int i = 0; i < array.length; i++) {
        if (array[i] != null)
          if (array[i].getUuid().equals(uuid))
            return i;
      }
      return -1;
  }
}
