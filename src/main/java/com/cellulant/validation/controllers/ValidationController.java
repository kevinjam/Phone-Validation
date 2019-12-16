package com.cellulant.validation.controllers;

import com.cellulant.validation.model.Result;
import com.cellulant.validation.model.ValidateModel;
import com.cellulant.validation.services.ValidationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ValidationController {
    private static final String HOME  = "/";
    private static final String PHONENUMBRT  = "/validate_number";
    private static final String GET_PHONE_NUMBER  = "/numbers_to_validate";
    private static final String GET_VALIDATED_NUMBER  = "/validated_numbers/{id}";
    private static final String VALIDATE_TBL  = "validate_tbl";

    private static Result response = new Result();


    @Autowired
    private ValidationServices validationServices;

    @RequestMapping(value = HOME, method = RequestMethod.POST)
    public String test(){
        return "OOPs";
    }


    @PostMapping(value = "/addNumbers", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> createUser(@RequestBody final ValidateModel user) {
        validationServices.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/get_addNumbers", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<ValidateModel>> getUsers() {
        final List<ValidateModel> users = validationServices.findByPattern("*");
        System.out.println("USERS " + users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = PHONENUMBRT, method = RequestMethod.POST)
    public ResponseEntity postPhoneNumber(@RequestBody ValidateModel validate){

        if (validate.getPhoneNumber().isEmpty()){
            response.setResult(false);
            response.setStatusCode(404);
            response.setMessage("Phone Number is Missing in the PayLoad");
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }else if(validate.getCountry().isEmpty()){
            response.setResult(false);
            response.setStatusCode(400);
            response.setMessage("Country is Missing posted PayLoad");
            validationServices.save(validate);
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        } else {
            response.setResult(true);
            response.setStatusCode(200);
            response.setMessage("Successful posted PayLoad");
            validationServices.save(validate);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

    }


    @RequestMapping(value = GET_PHONE_NUMBER, method = RequestMethod.POST)
    public ResponseEntity getPhoneNumber(@RequestBody ValidateModel validateModel) {
        final List<ValidateModel> numberList = validationServices.findByPattern("*");
        if (validateModel.getCountry().isEmpty()) {
            response.setResult(true);
            response.setStatusCode(400);
            response.setMessage("Country is Missing in the Payload");
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }else {
        System.out.println("USERS " + numberList);
        response.setNumbers(numberList);
        response.setResult(true);
        response.setStatusCode(200);
        response.setMessage("Success");
        System.out.println("List returned " + numberList);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }

    }

    @RequestMapping(value = GET_VALIDATED_NUMBER, method = RequestMethod.PUT)
    public ResponseEntity getPhoneNumberValidated(@PathVariable("id") final String validID,
                                                  @RequestBody final ValidateModel validateModel){
            if (validID.isEmpty()) {
                response.setResult(false);
                response.setStatusCode(400);
                response.setMessage("Phone Number is Missing");
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {
                validateModel.setId(validID);
                validationServices.update(validateModel);
                response.setResult(true);
                response.setStatusCode(200);
                response.setMessage("Successful Updated");
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }
    }


    //delete cache
}


