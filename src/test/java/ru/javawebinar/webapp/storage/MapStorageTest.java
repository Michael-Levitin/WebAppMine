package ru.javawebinar.webapp.storage;

public class MapStorageTest extends AbstractStorageTest {
  { storage = new MapStorage();
  } // {} - for loading after @BeforeEach is loaded (from Array/MapStorageTest )
}
