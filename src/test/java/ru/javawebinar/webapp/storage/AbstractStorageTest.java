package ru.javawebinar.webapp.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Contact;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

 import static org.junit.jupiter.api.Assertions.*;

abstract public class AbstractStorageTest  {
  private Resume R1, R2, R3;
  protected IStorrage storage;

/*  static { // static initialisation block
   }

  @BeforeClass // run once before
  static void beforeAll() {
    // in this case - same as static
  }*/

  @BeforeEach
  void beforeEach() {
    R1 = new Resume("Полное имя1", "location1");
    R1.addContact(new Contact(ContactType.MAIL, "mail@yandex.ru"));
    R1.addContact(new Contact(ContactType.PHONE, "11111"));
    R2 = new Resume("Полное имя2", "location2");
    R2.addContact(new Contact(ContactType.SKYPE, "krolik"));
    R2.addContact(new Contact(ContactType.PHONE, "22222"));
    R3 = new Resume("Полное имя3", "location3");
    R3.addContact(new Contact(ContactType.ICQ, "65454821"));
    R3.addContact(new Contact(ContactType.PHONE, "3333"));

    storage.clear();
    storage.save(R3);
    storage.save(R1);
    storage.save(R2);
  }

  @Test
  void testClear() {
    storage.clear();
    assertEquals(0, storage.size());
  }

  @Test
  void testSave() {
    storage.delete(R1.getUuid());
    storage.save(R1);
    Resume loadedResume = storage.load(R1.getUuid());
    assertEquals(R1.getUuid(), loadedResume.getUuid());
  }

  @Test
  void testSaveException() {
    assertThrows(WebAppException.class, () -> storage.save(R1));

  }

  @Test
  void testGetAllSorted() {
    Resume[] src = new Resume[]{R1, R2, R3};
    Arrays.sort(src);
    assertArrayEquals(src, storage.getAllSorted().toArray());
//    List<Resume> list = Arrays.asList(R1,R2,R3);
//    Collections.sort(list);
//    assertEquals(list, storage.getAllSorted());
  }

  @Test
  void testUpdate() {
    R2.setFullName("Update_name");
    storage.update(R2);
    assertEquals(R2, storage.load(R2.getUuid()));
  }

  @Test
  void testUpdateException() {
    R2.setFullName("Update_name");
    storage.update(R2);
    assertThrows(WebAppException.class, () -> assertEquals(R2, storage.load("dummy")));
  }

  @Test
  void testLoad() {
    assertEquals(R1, storage.load(R1.getUuid()));
    assertEquals(R2, storage.load(R2.getUuid()));
    assertEquals(R3, storage.load(R3.getUuid()));
  }

  @Test
  void testLoadException() {
    assertThrows(WebAppException.class, () -> storage.load("dummy"));
  }

  @Test
  void testDelete() {
    storage.delete(R1.getUuid());
    assertEquals(2, storage.size());
  }

  @Test
  void testDeleteException() {
    assertThrows(WebAppException.class, () ->  storage.delete("dummy"));
  }

  @Test
  void testSize() {
    assertEquals(3, storage.size());
  }

  public static boolean compareResume(Resume r1, Resume r2)
  { if (r1 == r2)
    return true;
    if (r1 == null || r2 == null || r1.getClass() != r2.getClass())
      return false;
    return r1.getUuid().equals(r2.getUuid()) &&
            Objects.equals(r1.getFullName(), r2.getFullName()) &&
            Objects.equals(r1.getHomePage(), r2.getHomePage()) &&
            Objects.equals(r1.getLocation(), r2.getLocation()) &&
            Objects.equals(r1.getContacts(), r2.getContacts()) &&
            Objects.equals(r1.getSection(),  r2.getSection());
  }
}
