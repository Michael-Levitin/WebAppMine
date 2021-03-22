package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

abstract public class AbstractStorage implements IStorrage {
  protected final Logger logger = Logger.getLogger(getClass().getName());
  // if it's static => mistake

  @Override
  public void clear() {
    logger.info("Delete all resumes");
    doClear();
  }

  protected abstract void doClear();

  protected abstract boolean exist(String uiid);

  @Override
  public void save(Resume r) {
    logger.info("Saving resume with Uiid = " + r.getUuid());
    if (exist(r.getUuid()))
      throw new WebAppException("Resume" + r.getUuid() + "already exists");
    doSave(r);
  }

  protected abstract void doSave(Resume r);

  @Override
  public void update(Resume r) {
    logger.info("Updated resume with uiid = " + r.getUuid());
    if (!exist(r.getUuid()))
      throw new WebAppException("Resume with uiid = " + r.getUuid() + "doesn't exists");
    doUpdate(r);
  }

  protected abstract void doUpdate(Resume r);

  @Override
  public Resume load(String uuid) {
    logger.info("Loading resume with uiid = " + uuid);
    if (!exist(uuid))
      throw new WebAppException("Resume with uiid = " + uuid + "doesn't exists");
    return doLoad(uuid);
  }

  protected abstract Resume doLoad(String uuid);

  @Override
  public void delete(String uuid) {
    logger.info("Deleting resume with uiid = " + uuid);
    if (!exist(uuid))
      throw new WebAppException("Resume with uiid = " + uuid + "doesn't exists");
    doDelete(uuid);
  }

  protected abstract void doDelete(String uuid);

  @Override
  public Collection<Resume> getAllSorted() {
    logger.info("GetAllSorted");
    List<Resume> list = doGetAll();
    list.sort(Comparator.comparing(Resume::getFullName) // Comparator chain
            .thenComparing(Resume::getUuid));
    return list;
//    Same, without Comparator chain, method reference and list-specific sort
   /* Collections.sort(list, new Comparator<Resume>() {
      @Override
      public int compare(Resume o1, Resume o2) {
        int cmp = o1.getFullName().compareTo(o2.getFullName());
        if (cmp != 0) return cmp;
        return o1.getUuid().compareTo(o2.getUuid());
      }
    });
    return list;*/
//    -------------------------
//      return o1.getUuid().compareTo(o2.getUuid());//        return   o1.getContacts(ContactType.MAIL).compareTo(o2.getContacts(ContactType.MAIL));
//        Cannot do like this - could be NPE, cause ContactType can be null

//    return Collections.singletonList(new Resume()); return collection of one
  }

  protected abstract List<Resume> doGetAll();

  public abstract int size();

}


