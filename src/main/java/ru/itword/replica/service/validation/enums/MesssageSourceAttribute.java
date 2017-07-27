package ru.itword.replica.service.validation.enums;

/**
 * Created by Itword on 28.07.2017.
 */
public enum MesssageSourceAttribute {
    ERROR("error."), MESSAGE("message.");

    MesssageSourceAttribute(String attribute) {
        this.attribute = attribute;
    }

    private String attribute;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
