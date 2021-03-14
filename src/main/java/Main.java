import ru.javawebinar.webapp.model.Contact;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;


public class Main {
  public static void main(String[] args) {
    Resume R1 = new Resume("Полное имя1", "location1");
    R1.addContact(new Contact(ContactType.MAIL, "mail@yandex.ru"));
  }
}
