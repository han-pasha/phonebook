package com.phonebook.service;

import com.phonebook.web.rest.vm.request.Phone;
import com.phonebook.web.rest.vm.response.PhoneDto;

import java.util.List;

public interface PhonebookService {
  String addNumber(Phone incoming);
  String updateNumber(Phone incoming);
  String deleteNumber(String no);
  List<PhoneDto> getListNumbers(Integer pageNo, Integer pageSize, String sortBy, String direction, String nameContains, String numberContains);
}
