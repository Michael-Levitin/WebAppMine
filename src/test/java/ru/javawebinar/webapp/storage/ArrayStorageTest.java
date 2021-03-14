package ru.javawebinar.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.webapp.model.Contact;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;


class ArrayStorageTest {
  private Resume R1, R2, R3;
  private static ArrayStorage storage = new ArrayStorage();

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
  void clearTest() {
    storage.clear();
    Assertions.assertEquals(0, storage.size());
  }

  @Test
  void testSave() {
    storage.delete(R1.getUuid());
    storage.save(R1);
    Resume loadedResume = storage.load(R1.getUuid());
    Assertions.assertEquals(R1.getUuid(), loadedResume.getUuid());
  }

  @Test
  void testGetAllSorted() {
    Resume[] src = new Resume[]{R1, R2, R3};
    Arrays.sort(src);
    Assertions.assertArrayEquals(src, storage.getAllSorted().toArray());
  }

  @Test
  void testUpdate() {
    R2.setFullName("Update_name");
    storage.update(R2);
    Assertions.assertEquals(R2, storage.load(R2.getUuid()));
  }

  @Test
  void testLoad() {
    Resume loadedResume = storage.load(R1.getUuid());
    Assertions.assertEquals(R1.getUuid(), loadedResume.getUuid());
  }

//     Assertions.assertThrows(WebAppException.class, () -> {
//
//  });

  @Test
  void testDelete() {
    storage.delete(R1.getUuid());
    Assertions.assertEquals(2, storage.size());
  }

  @Test
  void sizeTest() {
    Assertions.assertEquals(3, storage.size());
  }
}
