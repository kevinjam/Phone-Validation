package com.cellulant.validation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Result {

    private Boolean result;
    private String message;
    private Integer statusCode;
    @JsonIgnoreProperties()
    private List<ValidateModel> numbers;


    public Result() {
    }



    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<ValidateModel> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<ValidateModel> numbers) {
        this.numbers = numbers;
    }
}
