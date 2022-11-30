package com.phonebook.web.rest.vm.request;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Phone extends AbstractAuditEntity {
  @Id
  @Column(length = 36, nullable = false, unique = true)
  private String uuid = UUID.randomUUID().toString();
  @Column(length = 32, nullable = false)
  private String name;
  @Column(length = 12, nullable = false, unique = true)
  private String phoneNumber;

  public Phone(String createdBy, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate, String uuid, String name, String phoneNumber) {
    super(createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    this.uuid = uuid;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  public Phone(String name, String phoneNumber) {
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  public Phone() {
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
