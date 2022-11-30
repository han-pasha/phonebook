package com.phonebook.service.impl;

import com.phonebook.repository.PhoneRepository;
import com.phonebook.service.mapper.PhoneMapper;
import com.phonebook.web.rest.vm.request.Phone;
import com.phonebook.web.rest.vm.response.PhoneDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PhonebookServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PhonebookServiceImplTest {
  @MockBean
  private PhoneMapper phoneMapper;

  @MockBean
  private PhoneRepository phoneRepository;

  @Autowired
  private PhonebookServiceImpl phonebookServiceImpl;

  /**
   * Method under test: {@link PhonebookServiceImpl#initialize()}
   */
  @Test
  void testInitialize() {
    when(phoneRepository.saveAll((Iterable<Phone>) any())).thenReturn((Iterable<Phone>) mock(Iterable.class));
    phonebookServiceImpl.initialize();
    verify(phoneRepository).saveAll((Iterable<Phone>) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#addNumber(Phone)}
   */
  @Test
  void testAddNumber() {
    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Phone no isn't valid", phonebookServiceImpl.addNumber(phone));
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#addNumber(Phone)}
   */
  @Test
  void testAddNumber2() {
    Phone phone = mock(Phone.class);
    when(phone.getPhoneNumber()).thenReturn("4105551212");
    when(phone.getName()).thenReturn("Name");
    doNothing().when(phone).setCreatedBy((String) any());
    doNothing().when(phone).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone).setLastModifiedBy((String) any());
    doNothing().when(phone).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone).setName((String) any());
    doNothing().when(phone).setPhoneNumber((String) any());
    doNothing().when(phone).setUuid((String) any());
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Phone no isn't valid", phonebookServiceImpl.addNumber(phone));
    verify(phone).getName();
    verify(phone).getPhoneNumber();
    verify(phone).setCreatedBy((String) any());
    verify(phone).setCreatedDate((LocalDateTime) any());
    verify(phone).setLastModifiedBy((String) any());
    verify(phone).setLastModifiedDate((LocalDateTime) any());
    verify(phone).setName((String) any());
    verify(phone).setPhoneNumber((String) any());
    verify(phone).setUuid((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#addNumber(Phone)}
   */
  @Test
  void testAddNumber3() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setName("Name");
    phoneDto.setPhoneNo("4105551212");
    when(phoneMapper.toDto((Phone) any())).thenReturn(phoneDto);

    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    when(phoneRepository.save((Phone) any())).thenReturn(phone);
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(true);
    Phone phone1 = mock(Phone.class);
    when(phone1.getPhoneNumber()).thenReturn("089999999999");
    when(phone1.getName()).thenReturn("Name");
    doNothing().when(phone1).setCreatedBy((String) any());
    doNothing().when(phone1).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone1).setLastModifiedBy((String) any());
    doNothing().when(phone1).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone1).setName((String) any());
    doNothing().when(phone1).setPhoneNumber((String) any());
    doNothing().when(phone1).setUuid((String) any());
    phone1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setName("Name");
    phone1.setPhoneNumber("4105551212");
    phone1.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Phone number added to the phonebook", phonebookServiceImpl.addNumber(phone1));
    verify(phoneMapper).toDto((Phone) any());
    verify(phoneRepository).existsByPhoneNumber((String) any());
    verify(phoneRepository).save((Phone) any());
    verify(phone1).getName();
    verify(phone1, atLeast(1)).getPhoneNumber();
    verify(phone1).setCreatedBy((String) any());
    verify(phone1).setCreatedDate((LocalDateTime) any());
    verify(phone1).setLastModifiedBy((String) any());
    verify(phone1).setLastModifiedDate((LocalDateTime) any());
    verify(phone1).setName((String) any());
    verify(phone1).setPhoneNumber((String) any());
    verify(phone1).setUuid((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#addNumber(Phone)}
   */
  @Test
  void testAddNumber4() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setName("Name");
    phoneDto.setPhoneNo("4105551212");
    when(phoneMapper.toDto((Phone) any())).thenReturn(phoneDto);

    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    when(phoneRepository.save((Phone) any())).thenReturn(phone);
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(false);
    Phone phone1 = mock(Phone.class);
    when(phone1.getPhoneNumber()).thenReturn("089999999999");
    when(phone1.getName()).thenReturn("Name");
    doNothing().when(phone1).setCreatedBy((String) any());
    doNothing().when(phone1).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone1).setLastModifiedBy((String) any());
    doNothing().when(phone1).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone1).setName((String) any());
    doNothing().when(phone1).setPhoneNumber((String) any());
    doNothing().when(phone1).setUuid((String) any());
    phone1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setName("Name");
    phone1.setPhoneNumber("4105551212");
    phone1.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Phone no isn't in database", phonebookServiceImpl.addNumber(phone1));
    verify(phoneRepository).existsByPhoneNumber((String) any());
    verify(phone1).getName();
    verify(phone1, atLeast(1)).getPhoneNumber();
    verify(phone1).setCreatedBy((String) any());
    verify(phone1).setCreatedDate((LocalDateTime) any());
    verify(phone1).setLastModifiedBy((String) any());
    verify(phone1).setLastModifiedDate((LocalDateTime) any());
    verify(phone1).setName((String) any());
    verify(phone1).setPhoneNumber((String) any());
    verify(phone1).setUuid((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#addNumber(Phone)}
   */
  @Test
  void testAddNumber5() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setName("Name");
    phoneDto.setPhoneNo("4105551212");
    when(phoneMapper.toDto((Phone) any())).thenReturn(phoneDto);

    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    when(phoneRepository.save((Phone) any())).thenReturn(phone);
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(true);
    Phone phone1 = mock(Phone.class);
    when(phone1.getPhoneNumber()).thenReturn("Phone number added to the phonebook");
    when(phone1.getName()).thenReturn("Name");
    doNothing().when(phone1).setCreatedBy((String) any());
    doNothing().when(phone1).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone1).setLastModifiedBy((String) any());
    doNothing().when(phone1).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone1).setName((String) any());
    doNothing().when(phone1).setPhoneNumber((String) any());
    doNothing().when(phone1).setUuid((String) any());
    phone1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setName("Name");
    phone1.setPhoneNumber("4105551212");
    phone1.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Phone number added to the phonebook", phonebookServiceImpl.addNumber(phone1));
    verify(phoneMapper).toDto((Phone) any());
    verify(phoneRepository).existsByPhoneNumber((String) any());
    verify(phoneRepository).save((Phone) any());
    verify(phone1).getName();
    verify(phone1, atLeast(1)).getPhoneNumber();
    verify(phone1).setCreatedBy((String) any());
    verify(phone1).setCreatedDate((LocalDateTime) any());
    verify(phone1).setLastModifiedBy((String) any());
    verify(phone1).setLastModifiedDate((LocalDateTime) any());
    verify(phone1).setName((String) any());
    verify(phone1).setPhoneNumber((String) any());
    verify(phone1).setUuid((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#addNumber(Phone)}
   */
  @Test
  void testAddNumber6() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setName("Name");
    phoneDto.setPhoneNo("4105551212");
    when(phoneMapper.toDto((Phone) any())).thenReturn(phoneDto);

    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    when(phoneRepository.save((Phone) any())).thenReturn(phone);
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(true);
    Phone phone1 = mock(Phone.class);
    when(phone1.getPhoneNumber()).thenReturn("089999999999");
    when(phone1.getName()).thenReturn("Phone number added to the phonebook");
    doNothing().when(phone1).setCreatedBy((String) any());
    doNothing().when(phone1).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone1).setLastModifiedBy((String) any());
    doNothing().when(phone1).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone1).setName((String) any());
    doNothing().when(phone1).setPhoneNumber((String) any());
    doNothing().when(phone1).setUuid((String) any());
    phone1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setName("Name");
    phone1.setPhoneNumber("4105551212");
    phone1.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Name length is invalid", phonebookServiceImpl.addNumber(phone1));
    verify(phone1).getName();
    verify(phone1).setCreatedBy((String) any());
    verify(phone1).setCreatedDate((LocalDateTime) any());
    verify(phone1).setLastModifiedBy((String) any());
    verify(phone1).setLastModifiedDate((LocalDateTime) any());
    verify(phone1).setName((String) any());
    verify(phone1).setPhoneNumber((String) any());
    verify(phone1).setUuid((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#addNumber(Phone)}
   */
  @Test
  void testAddNumber7() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setName("Name");
    phoneDto.setPhoneNo("4105551212");
    when(phoneMapper.toDto((Phone) any())).thenReturn(phoneDto);

    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    when(phoneRepository.save((Phone) any())).thenReturn(phone);
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(true);
    Phone phone1 = mock(Phone.class);
    when(phone1.getPhoneNumber()).thenReturn("089999999999");
    when(phone1.getName()).thenReturn("foo");
    doNothing().when(phone1).setCreatedBy((String) any());
    doNothing().when(phone1).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone1).setLastModifiedBy((String) any());
    doNothing().when(phone1).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone1).setName((String) any());
    doNothing().when(phone1).setPhoneNumber((String) any());
    doNothing().when(phone1).setUuid((String) any());
    phone1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setName("Name");
    phone1.setPhoneNumber("4105551212");
    phone1.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Name length is invalid", phonebookServiceImpl.addNumber(phone1));
    verify(phone1).getName();
    verify(phone1).setCreatedBy((String) any());
    verify(phone1).setCreatedDate((LocalDateTime) any());
    verify(phone1).setLastModifiedBy((String) any());
    verify(phone1).setLastModifiedDate((LocalDateTime) any());
    verify(phone1).setName((String) any());
    verify(phone1).setPhoneNumber((String) any());
    verify(phone1).setUuid((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#updateNumber(Phone)}
   */
  @Test
  void testUpdateNumber() {
    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Phone no isn't valid", phonebookServiceImpl.updateNumber(phone));
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#updateNumber(Phone)}
   */
  @Test
  void testUpdateNumber2() {
    Phone phone = mock(Phone.class);
    when(phone.getPhoneNumber()).thenReturn("4105551212");
    when(phone.getName()).thenReturn("Name");
    doNothing().when(phone).setCreatedBy((String) any());
    doNothing().when(phone).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone).setLastModifiedBy((String) any());
    doNothing().when(phone).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone).setName((String) any());
    doNothing().when(phone).setPhoneNumber((String) any());
    doNothing().when(phone).setUuid((String) any());
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Phone no isn't valid", phonebookServiceImpl.updateNumber(phone));
    verify(phone).getName();
    verify(phone).getPhoneNumber();
    verify(phone).setCreatedBy((String) any());
    verify(phone).setCreatedDate((LocalDateTime) any());
    verify(phone).setLastModifiedBy((String) any());
    verify(phone).setLastModifiedDate((LocalDateTime) any());
    verify(phone).setName((String) any());
    verify(phone).setPhoneNumber((String) any());
    verify(phone).setUuid((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#updateNumber(Phone)}
   */
  @Test
  void testUpdateNumber3() {
    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

    Phone phone1 = new Phone();
    phone1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setName("Name");
    phone1.setPhoneNumber("4105551212");
    phone1.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    when(phoneRepository.save((Phone) any())).thenReturn(phone1);
    when(phoneRepository.getByPhoneNumber((String) any())).thenReturn(phone);
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(true);
    Phone phone2 = mock(Phone.class);
    when(phone2.getPhoneNumber()).thenReturn("089999999999");
    when(phone2.getName()).thenReturn("Name");
    doNothing().when(phone2).setCreatedBy((String) any());
    doNothing().when(phone2).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone2).setLastModifiedBy((String) any());
    doNothing().when(phone2).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone2).setName((String) any());
    doNothing().when(phone2).setPhoneNumber((String) any());
    doNothing().when(phone2).setUuid((String) any());
    phone2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone2.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone2.setName("Name");
    phone2.setPhoneNumber("4105551212");
    phone2.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Update Success", phonebookServiceImpl.updateNumber(phone2));
    verify(phoneRepository).getByPhoneNumber((String) any());
    verify(phoneRepository).existsByPhoneNumber((String) any());
    verify(phoneRepository).save((Phone) any());
    verify(phone2, atLeast(1)).getName();
    verify(phone2, atLeast(1)).getPhoneNumber();
    verify(phone2).setCreatedBy((String) any());
    verify(phone2).setCreatedDate((LocalDateTime) any());
    verify(phone2).setLastModifiedBy((String) any());
    verify(phone2).setLastModifiedDate((LocalDateTime) any());
    verify(phone2).setName((String) any());
    verify(phone2).setPhoneNumber((String) any());
    verify(phone2).setUuid((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#updateNumber(Phone)}
   */
  @Test
  void testUpdateNumber4() {
    Phone phone = mock(Phone.class);
    doNothing().when(phone).setCreatedBy((String) any());
    doNothing().when(phone).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone).setLastModifiedBy((String) any());
    doNothing().when(phone).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone).setName((String) any());
    doNothing().when(phone).setPhoneNumber((String) any());
    doNothing().when(phone).setUuid((String) any());
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("Name");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

    Phone phone1 = new Phone();
    phone1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone1.setName("Name");
    phone1.setPhoneNumber("4105551212");
    phone1.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    when(phoneRepository.save((Phone) any())).thenReturn(phone1);
    when(phoneRepository.getByPhoneNumber((String) any())).thenReturn(phone);
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(true);
    Phone phone2 = mock(Phone.class);
    when(phone2.getPhoneNumber()).thenReturn("089999999999");
    when(phone2.getName()).thenReturn("Name");
    doNothing().when(phone2).setCreatedBy((String) any());
    doNothing().when(phone2).setCreatedDate((LocalDateTime) any());
    doNothing().when(phone2).setLastModifiedBy((String) any());
    doNothing().when(phone2).setLastModifiedDate((LocalDateTime) any());
    doNothing().when(phone2).setName((String) any());
    doNothing().when(phone2).setPhoneNumber((String) any());
    doNothing().when(phone2).setUuid((String) any());
    phone2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone2.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone2.setName("Name");
    phone2.setPhoneNumber("4105551212");
    phone2.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
    assertEquals("Update Success", phonebookServiceImpl.updateNumber(phone2));
    verify(phoneRepository).getByPhoneNumber((String) any());
    verify(phoneRepository).existsByPhoneNumber((String) any());
    verify(phoneRepository).save((Phone) any());
    verify(phone).setCreatedBy((String) any());
    verify(phone).setCreatedDate((LocalDateTime) any());
    verify(phone).setLastModifiedBy((String) any());
    verify(phone).setLastModifiedDate((LocalDateTime) any());
    verify(phone, atLeast(1)).setName((String) any());
    verify(phone, atLeast(1)).setPhoneNumber((String) any());
    verify(phone).setUuid((String) any());
    verify(phone2, atLeast(1)).getName();
    verify(phone2, atLeast(1)).getPhoneNumber();
    verify(phone2).setCreatedBy((String) any());
    verify(phone2).setCreatedDate((LocalDateTime) any());
    verify(phone2).setLastModifiedBy((String) any());
    verify(phone2).setLastModifiedDate((LocalDateTime) any());
    verify(phone2).setName((String) any());
    verify(phone2).setPhoneNumber((String) any());
    verify(phone2).setUuid((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#deleteNumber(String)}
   */
  @Test
  void testDeleteNumber() {
    assertEquals("Phone no isn't valid", phonebookServiceImpl.deleteNumber("No"));
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#deleteNumber(String)}
   */
  @Test
  void testDeleteNumber2() {
    doNothing().when(phoneRepository).deleteByPhoneNumber((String) any());
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(true);
    assertEquals("Delete Success", phonebookServiceImpl.deleteNumber("089999999999"));
    verify(phoneRepository).existsByPhoneNumber((String) any());
    verify(phoneRepository).deleteByPhoneNumber((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#deleteNumber(String)}
   */
  @Test
  void testDeleteNumber3() {
    doNothing().when(phoneRepository).deleteByPhoneNumber((String) any());
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(false);
    assertEquals("Phone no isn't in database", phonebookServiceImpl.deleteNumber("089999999999"));
    verify(phoneRepository).existsByPhoneNumber((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#deleteNumber(String)}
   */
  @Test
  void testDeleteNumber4() {
    doNothing().when(phoneRepository).deleteByPhoneNumber((String) any());
    when(phoneRepository.existsByPhoneNumber((String) any())).thenReturn(true);
    assertEquals("Delete Success", phonebookServiceImpl.deleteNumber("Delete Success"));
    verify(phoneRepository).existsByPhoneNumber((String) any());
    verify(phoneRepository).deleteByPhoneNumber((String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#getListNumbers(Integer, Integer, String, String, String, String)}
   */
  @Test
  void testGetListNumbers() {
    when(phoneRepository.findAllByNameContaining((Pageable) any(), (String) any()))
        .thenReturn(new PageImpl<>(new ArrayList<>()));
    assertTrue(phonebookServiceImpl.getListNumbers(1, 3, "Sort By", "Direction", "Name Contains", "42").isEmpty());
    verify(phoneRepository).findAllByNameContaining((Pageable) any(), (String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#getListNumbers(Integer, Integer, String, String, String, String)}
   */
  @Test
  void testGetListNumbers2() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setName("Name");
    phoneDto.setPhoneNo("4105551212");
    when(phoneMapper.toDto((Phone) any())).thenReturn(phoneDto);

    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("asc");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

    ArrayList<Phone> phoneList = new ArrayList<>();
    phoneList.add(phone);
    PageImpl<Phone> pageImpl = new PageImpl<>(phoneList);
    when(phoneRepository.findAllByNameContaining((Pageable) any(), (String) any())).thenReturn(pageImpl);
    assertEquals(1, phonebookServiceImpl.getListNumbers(1, 3, "Sort By", "Direction", "Name Contains", "42").size());
    verify(phoneMapper).toDto((Phone) any());
    verify(phoneRepository).findAllByNameContaining((Pageable) any(), (String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#getListNumbers(Integer, Integer, String, String, String, String)}
   */
  @Test
  void testGetListNumbers5() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setName("Name");
    phoneDto.setPhoneNo("4105551212");
    when(phoneMapper.toDto((Phone) any())).thenReturn(phoneDto);

    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("asc");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

    ArrayList<Phone> phoneList = new ArrayList<>();
    phoneList.add(phone);
    PageImpl<Phone> pageImpl = new PageImpl<>(phoneList);
    when(phoneRepository.findAllByNameContaining((Pageable) any(), (String) any())).thenReturn(pageImpl);
    assertEquals(1, phonebookServiceImpl.getListNumbers(1, 3, "Sort By", "asc", "Name Contains", "42").size());
    verify(phoneMapper).toDto((Phone) any());
    verify(phoneRepository).findAllByNameContaining((Pageable) any(), (String) any());
  }

  /**
   * Method under test: {@link PhonebookServiceImpl#getListNumbers(Integer, Integer, String, String, String, String)}
   */
  @Test
  void testGetListNumbers6() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setName("Name");
    phoneDto.setPhoneNo("4105551212");
    when(phoneMapper.toDto((Phone) any())).thenReturn(phoneDto);

    Phone phone = new Phone();
    phone.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    phone.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
    phone.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
    phone.setName("asc");
    phone.setPhoneNumber("4105551212");
    phone.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

    ArrayList<Phone> phoneList = new ArrayList<>();
    phoneList.add(phone);
    PageImpl<Phone> pageImpl = new PageImpl<>(phoneList);
    when(phoneRepository.findAllByPhoneNumberContaining((Pageable) any(), (String) any()))
        .thenReturn(new PageImpl<>(new ArrayList<>()));
    when(phoneRepository.findAllByNameContaining((Pageable) any(), (String) any())).thenReturn(pageImpl);
    assertTrue(phonebookServiceImpl.getListNumbers(1, 3, "Sort By", "Direction", "", "42").isEmpty());
    verify(phoneRepository).findAllByPhoneNumberContaining((Pageable) any(), (String) any());
  }
}

