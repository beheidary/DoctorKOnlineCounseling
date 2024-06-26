package com.doctork.doctorkonlinecounseling.common.exceptions;



public class FieldItemError {

    private String field;
    private String message;
    private Object value;

    public FieldItemError(String field, String message, Object value) {
        this.field = field;
        this.message = message;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
