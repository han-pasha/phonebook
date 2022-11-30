package com.phonebook.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonebook.web.rest.vm.request.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PhonebookControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void getAllList() throws Exception {
    String expected = "[{\"name\":\"zulham\",\"phoneNo\":\"087777777777\"},{\"name\":\"kiki\",\"phoneNo\":\"089999999999\"},{\"name\":\"hamud\",\"phoneNo\":\"08888888888\"},{\"name\":\"fariz\",\"phoneNo\":\"086666666666\"},{\"name\":\"diky\",\"phoneNo\":\"085555555555\"},{\"name\":\"cinta\",\"phoneNo\":\"084444444444\"},{\"name\":\"bunga\",\"phoneNo\":\"083333333333\"},{\"name\":\"azka\",\"phoneNo\":\"081111111111\"},{\"name\":\"astuti\",\"phoneNo\":\"082222222222\"}]";
    this.mockMvc.perform(
            get("http://localhost:8010/phone/get-all"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(expected));
  }

  @Test
  void givenNameZulham_ShouldReturnZulhamContact() throws Exception {
    this.mockMvc.perform(
            get("http://localhost:8010/phone/get-all?name=zulham"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("zulham"))
        .andExpect(jsonPath("$[0].phoneNo").value("087777777777"));
  }

  @Test
  void getAllList_withFilter_shouldReturn3Account() throws Exception {
    this.mockMvc.perform(
            get("http://localhost:8010/phone/get-all?pageNo=1&pageSize=3"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("fariz"))
        .andExpect(jsonPath("$[0].phoneNo").value("086666666666"))
        .andExpect(jsonPath("$[1].name").value("diky"))
        .andExpect(jsonPath("$[1].phoneNo").value("085555555555"))
        .andExpect(jsonPath("$[2].name").value("cinta"))
        .andExpect(jsonPath("$[2].phoneNo").value("084444444444"));
  }

  @Test
  void giveValidPhone_shouldReturnSuccess() throws Exception {
    Phone phone = new Phone("astaga", "082108210821");

    this.mockMvc.perform(
        post("http://localhost:8010/phone/add")
            .content(objectMapper.writeValueAsString(phone))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("Phone number added to the phonebook"));
  }

  @Test
  void givenInvalidName_messageShouldReturnNameIsInvalid() throws Exception {
    Phone phone = new Phone("as", "082108210821");

    this.mockMvc.perform(
            post("http://localhost:8010/phone/add")
                .content(objectMapper.writeValueAsString(phone))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("Name length isn't valid"));
  }

  @Test
  void givenInvalidNo_messageShouldReturnNoIsInvalid() throws Exception {
    Phone phone = new Phone("astaga", "0200000000");

    this.mockMvc.perform(
            post("http://localhost:8010/phone/add")
                .content(objectMapper.writeValueAsString(phone))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("Phone no isn't valid"));
  }

  @Test
  void givenValidData_updateShouldBeSuccess() throws Exception {
    Phone phone = new Phone("astaga", "082222222222");

    this.mockMvc.perform(
            put("http://localhost:8010/phone/update")
                .content(objectMapper.writeValueAsString(phone))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("Update Success"));
  }

  @Test
  void givenInvalidData_updateShouldBeSuccess() throws Exception {
    Phone phone = new Phone("as", "082222222222");

    this.mockMvc.perform(
            put("http://localhost:8010/phone/update")
                .content(objectMapper.writeValueAsString(phone))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("Name length is invalid"));
  }

  @Test
  void deletePhone_phoneIsInDatabase_shouldReturnSuccess() throws Exception {
    String no = "082222222222";
    this.mockMvc.perform(
            delete("http://localhost:8010/phone/delete/"+no))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("Delete Success"));
  }

  @Test
  void deletePhone_phoneIsNotInDatabase_shouldReturnSuccess() throws Exception {
    String no = "082108210821";
    this.mockMvc.perform(
            delete("http://localhost:8010/phone/delete/"+no))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("Phone no isn't in database"));
  }
}

