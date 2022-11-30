package com.phonebook.web.rest.vm.response;

public class PhoneDto {

  private String name;
  private String phoneNo;

  public PhoneDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }
}
