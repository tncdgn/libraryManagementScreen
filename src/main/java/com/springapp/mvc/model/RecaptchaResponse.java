package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 * Created by kafein on 10/26/2017.
 */
public class RecaptchaResponse {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("error-codes")
    private Collection<String> errorCodes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Collection<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(Collection<String> errorCodes) {
        this.errorCodes = errorCodes;
    }
}
