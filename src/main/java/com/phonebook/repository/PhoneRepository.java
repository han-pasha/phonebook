package com.phonebook.repository;

import com.phonebook.web.rest.vm.request.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, String> {
  Boolean existsByPhoneNumber(String phoneNumber);
  Phone getByPhoneNumber(String phoneNumber);
  void deleteByPhoneNumber(String phoneNumber);
  Page<Phone> findAllByNameContaining(Pageable page, String name);
  Page<Phone> findAllByPhoneNumberContaining(Pageable page, String no);
}
