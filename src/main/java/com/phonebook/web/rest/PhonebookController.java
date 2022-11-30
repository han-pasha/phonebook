package com.phonebook.web.rest;

import com.phonebook.service.PhonebookService;
import com.phonebook.web.rest.vm.request.Phone;
import com.phonebook.web.rest.vm.response.PhoneDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.phonebook.common.ApiContract.*;

@RestController
@RequestMapping(PHONE_API)
public class PhonebookController {

  private final PhonebookService phonebookService;

  public PhonebookController(PhonebookService phonebookService) {
    this.phonebookService = phonebookService;
  }

  @PostMapping(path = ADD,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> addPhonebook(@RequestBody Phone incoming) {
    return ResponseEntity.ok().body(phonebookService.addNumber(incoming));
  }

  @PutMapping(path = UPDATE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> updatePhonebook(@RequestBody Phone incoming) {
    return ResponseEntity.ok().body(phonebookService.updateNumber(incoming));
  }

  @GetMapping(path = GET_ALL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PhoneDto>> getAllPhoneNumbers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "name") String sortBy,
                                                           @RequestParam(defaultValue = "") String direction,
                                                           @RequestParam(defaultValue = "") String name,
                                                           @RequestParam(defaultValue = "") String phoneNo) {
    return ResponseEntity.ok().body(phonebookService.getListNumbers(pageNo, pageSize, sortBy, direction, name, phoneNo));
  }

  @DeleteMapping(path = DELETE + "{name}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> deletePhonebook(@PathVariable String name) {
    return ResponseEntity.ok().body(phonebookService.deleteNumber(name));
  }
}
