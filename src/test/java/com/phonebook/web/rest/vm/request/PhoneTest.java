package com.phonebook.web.rest.vm.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class PhoneTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Phone#Phone()}
   *   <li>{@link Phone#setName(String)}
   *   <li>{@link Phone#setPhoneNumber(String)}
   *   <li>{@link Phone#setUuid(String)}
   *   <li>{@link Phone#getName()}
   *   <li>{@link Phone#getPhoneNumber()}
   *   <li>{@link Phone#getUuid()}
   * </ul>
   */
  @Test
  void testConstructor() {
    Phone actualPhone = new Phone();
    actualPhone.setName("Name");
    actualPhone.setPhoneNumber("4105551212");
    actualPhone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Name", actualPhone.getName());
    assertEquals("4105551212", actualPhone.getPhoneNumber());
    assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualPhone.getUuid());
  }

  /**
   * Method under test: {@link Phone#Phone()}
   */
  @Test
  void testConstructor2() {
    Phone actualPhone = new Phone();
    assertNull(actualPhone.getCreatedBy());
    assertNull(actualPhone.getPhoneNumber());
    assertNull(actualPhone.getName());
    assertNull(actualPhone.getLastModifiedDate());
    assertNull(actualPhone.getLastModifiedBy());
    assertNull(actualPhone.getCreatedDate());
  }

  /**
   * Method under test: {@link Phone#Phone(String, LocalDateTime, String, LocalDateTime, String, String, String)}
   */
  @Test
  void testConstructor3() {
    LocalDateTime createdDate = LocalDateTime.of(1, 1, 1, 1, 1);
    Phone actualPhone = new Phone("Jan 1, 2020 8:00am GMT+0100", createdDate, "Jan 1, 2020 9:00am GMT+0100",
        LocalDateTime.of(1, 1, 1, 1, 1), "01234567-89AB-CDEF-FEDC-BA9876543210", "Name", "4105551212");

    assertEquals("Jan 1, 2020 8:00am GMT+0100", actualPhone.getCreatedBy());
    assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualPhone.getUuid());
    assertEquals("4105551212", actualPhone.getPhoneNumber());
    assertEquals("Name", actualPhone.getName());
    assertEquals("Jan 1, 2020 9:00am GMT+0100", actualPhone.getLastModifiedBy());
    assertEquals("01:01", actualPhone.getLastModifiedDate().toLocalTime().toString());
    assertEquals("0001-01-01", actualPhone.getCreatedDate().toLocalDate().toString());
  }
}

