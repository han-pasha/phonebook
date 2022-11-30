package com.phonebook.web.rest.vm.response;

public class WrapperResponse {
    private String message;
    private Boolean status;

    public WrapperResponse(String message, Boolean status) {
      this.message = message;
      this.status = status;
    }

    public WrapperResponse() {
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public Boolean getStatus() {
      return status;
    }

    public void setStatus(Boolean status) {
      this.status = status;
    }
}
