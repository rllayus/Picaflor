package com.rllayus.picaflor.service;

import java.util.List;

/**
 * Created by CHUPITA on 17/06/2015.
 */
public class ObjetResponse<T> {
    private int success;
    private String message;
    private List<T> values;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
