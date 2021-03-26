package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.io.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class FileStorage extends AbstractStorage<File>  {

  public static long serialVersionUID = 1L;

  private File dir;

  public FileStorage(String path) {
    this.dir = new File(path);
    if (!dir.isDirectory() || !dir.canWrite()){
      throw new IllegalArgumentException("'" + path + "' is not directory or writable ");
    }
  }

  @Override
  protected void doClear() {
    File[] files = dir.listFiles();
    assert files != null;
    for (File file: files)
      doDelete(file);
  }

  @Override
  protected File getContext(String fileName) {
    return new File(fileName);
  }

  @Override
  protected boolean exist(File fileName) {
    return new File(String.valueOf(fileName)).exists();
  }

  @Override
  protected void doSave(File fileName, Resume r) {
    try {
      fileName.createNewFile();
      write(fileName, r);
    } catch (IOException e) {
      throw new WebAppException("File " + fileName.getAbsolutePath() + " cannot be created", r, e);
    }
  }

  protected void write(File fileName, Resume r) {
    try (FileOutputStream fos = new FileOutputStream(fileName);
         DataOutputStream dos = new DataOutputStream(fos)) {
      dos.writeUTF(r.getFullName());
      dos.writeUTF(r.getLocation());
      dos.writeUTF(r.getHomePage());
      Map<ContactType, String> contacts = r.getContacts();
      dos.writeInt(contacts.size());  // writing to file the number of contacts
      for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
        dos.writeInt(entry.getKey().ordinal());
        // ordinal() - № of enum
        dos.writeUTF(entry.getValue());
//        dos.writeUTF(entry.getKey().name() + ": " + entry.getValue());
        // name() - for ENUM returns the name of enums (vs. title)
        //TODO Section implementation
      }
    } catch (IOException e) {
      throw new WebAppException("File " + fileName.getAbsolutePath() + " cannot be created", r, e);
    }
  }

  @Override
  protected void doUpdate(File fileName, Resume r) {
      write(fileName, r);
  }

  @Override
  protected Resume doLoad(File fileName) {
    return read(fileName);
  }

  protected Resume read(File fileName) {
    Resume r = new Resume();
    try (InputStream is = new FileInputStream(fileName);
         DataInputStream dis = new DataInputStream(is)){
      r.setFullName(dis.readUTF());
      r.setLocation(dis.readUTF());
      r.setHomePage(dis.readUTF());
      int contactSize = dis.readInt();
      for (int i = 0; i < contactSize; i++)
        r.addContact(ContactType.values()[dis.readInt()], dis.readUTF());
//        r.addContact(ContactType.VALUES[dis.readInt()], dis.readUTF());
        //Add contact from № of enum and String

      //TODO Section implementation
      return null;
    } catch (IOException e) {
      throw new WebAppException("File " + fileName.getAbsolutePath() + " read unsuccessful", e);
    }
  }

    @Override
  protected void doDelete(File fileName) {
    if (!fileName.delete())
      throw new WebAppException("File " + fileName.getAbsolutePath() + " cannot be deleted");
  }

  @Override
  protected List<Resume> doGetAll() {
   // TODO get all
    return null;
  }

  @Override
  public int size() {
    String[] list = dir.list();
    if (list == null)
      throw new WebAppException("Directory " + dir.getAbsolutePath() + " is empty");
    return list.length;
  }
}
