package com.phonebook.service.mapper;

import com.phonebook.web.rest.vm.request.Phone;
import com.phonebook.web.rest.vm.response.PhoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PhoneMapper {

  @Mapping(target = "name", source = "name")
  @Mapping(target = "phoneNo", source = "phoneNumber")
  public abstract PhoneDto toDto(Phone incoming);
}
