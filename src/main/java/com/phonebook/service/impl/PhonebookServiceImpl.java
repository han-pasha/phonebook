package com.phonebook.service.impl;

import com.phonebook.repository.PhoneRepository;
import com.phonebook.service.PhonebookService;
import com.phonebook.service.mapper.PhoneMapper;
import com.phonebook.web.rest.vm.request.Phone;
import com.phonebook.web.rest.vm.response.PhoneDto;
import com.phonebook.web.rest.vm.response.WrapperResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Transactional
@Service
public class PhonebookServiceImpl implements PhonebookService {

  private final PhoneMapper phoneMapper;
  private final PhoneRepository phoneRepository;

  Pattern phoneNoPattern = Pattern.compile("^08([0-9]{8,10})$");

  public PhonebookServiceImpl(PhoneMapper phoneMapper, PhoneRepository phoneRepository) {
    this.phoneMapper = phoneMapper;
    this.phoneRepository = phoneRepository;
  }

  @PostConstruct
  void initialize() {
    var listPhone = List.of(
        new Phone("azka", "081111111111"),
        new Phone("astuti", "082222222222"),
        new Phone("bunga", "083333333333"),
        new Phone("cinta", "084444444444"),
        new Phone("diky", "085555555555"),
        new Phone("fariz", "086666666666"),
        new Phone("zulham", "087777777777"),
        new Phone("hamud", "08888888888"),
        new Phone("kiki", "089999999999"));
    phoneRepository.saveAll(listPhone);
  }

  @Override
  public String addNumber(Phone incoming) {
    if (!checkName(incoming.getName())) return "Name length isn't valid";
    if (!checkNumber(incoming.getPhoneNumber())) return "Phone no isn't valid";
    if (isInDatabase(incoming.getPhoneNumber())) return "Phone no is in database";

    phoneMapper.toDto(phoneRepository.save(incoming));
    return "Phone number added to the phonebook";
  }

  @Override
  public String updateNumber(Phone incoming) {
    var validation = checkIncomingData(incoming);
    if (!validation.getStatus()) return validation.getMessage();

    var result = phoneRepository.getByPhoneNumber(incoming.getPhoneNumber());
    result.setName(incoming.getName());
    result.setPhoneNumber(incoming.getPhoneNumber());

    phoneRepository.save(result);
    return "Update Success";
  }

  @Transactional
  @Override
  public String deleteNumber(String no) {
    if (!checkNumber(no)) return "Phone no isn't valid";
    if (!isInDatabase(no)) return "Phone no isn't in database";

    phoneRepository.deleteByPhoneNumber(no);
    return "Delete Success";
  }

  @Override
  public List<PhoneDto> getListNumbers(Integer pageNo, Integer pageSize, String sortBy, String direction, String nameContains, String numberContains) {
    Sort.Direction direct = direction.contains("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Pageable paging = PageRequest.of(pageNo, pageSize, direct, sortBy);
    Page<Phone> pagedResult = determinePagedResult(paging, nameContains, numberContains);
    var content = pagedResult.getContent().stream().map(phoneMapper::toDto).collect(Collectors.toList());
    return pagedResult.hasContent() ? content : List.of();
  }

  private Page<Phone> determinePagedResult(Pageable paging, String nameContains, String numberContains) {
    if (!nameContains.isBlank() && numberContains.isBlank()) {
      return phoneRepository.findAllByNameContaining(paging, nameContains);
    } else if (nameContains.isBlank() && !numberContains.isBlank()) {
      return phoneRepository.findAllByPhoneNumberContaining(paging, numberContains);
    } else if (!nameContains.isBlank() && !numberContains.isBlank()) {
      return phoneRepository.findAllByNameContaining(paging, nameContains);
    } else {
      return phoneRepository.findAll(paging);
    }
  }

  private Boolean checkNumber(String no) {
    Matcher phoneNoMatcher = phoneNoPattern.matcher(no);
    return phoneNoMatcher.find() || no.length() > 10;
  }

  private Boolean checkName(String name) {
    return name.length() > 3 && name.length() < 32;
  }

  private Boolean isInDatabase(String no) {
    return phoneRepository.existsByPhoneNumber(no);
  }

  private WrapperResponse checkIncomingData(Phone phone) {
    if (!checkName(phone.getName())) return new WrapperResponse("Name length is invalid", false);
    if (!checkNumber(phone.getPhoneNumber())) return new WrapperResponse("Phone no isn't valid", false);
    if (!isInDatabase(phone.getPhoneNumber())) return new WrapperResponse("Phone no isn't in database", false);
    return new WrapperResponse("", true);
  }
}
