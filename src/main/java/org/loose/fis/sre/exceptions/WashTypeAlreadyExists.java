package org.loose.fis.sre.exceptions;

public class WashTypeAlreadyExists extends Exception {

    private String nume;

    public WashTypeAlreadyExists(String nume) {
        super(String.format("Wash type %s already exists!", nume));
        this.nume =nume;
    }

    public String getWashTypeName() {
        return nume;
    }
}