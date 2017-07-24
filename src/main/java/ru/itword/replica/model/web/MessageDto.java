package ru.itword.replica.model.web;

import java.util.List;
import java.util.Map;

/**
 * Created by Itword on 23.07.2017.
 */
public class MessageDto {
    private boolean success;
    private Object content;
    private Map<String, String> fieldErrors;
    private List<String> errors;
    private List<String> messages;

    public Object getContent() {
        return content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
