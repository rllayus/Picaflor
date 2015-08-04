package com.rllayus.picaflor.service;

/**
 * Created by CHUPITA on 21/06/2015.
 */
public  class BaseResponse {
    protected Boolean error;
    protected Object objects;


    protected BaseResponse() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Object getObjects() {
        return objects;
    }

    public void setObjects(Object objects) {
        this.objects = objects;
    }
}
