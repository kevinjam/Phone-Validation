package com.cellulant.validation.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
class ResponseModel {
    var result: Boolean? = null
    var message: String? = null
    var statusCode: Int? = null

    @JsonIgnoreProperties("fullName","msisdn")
    var validate = ArrayList<ValidateModel>()

}