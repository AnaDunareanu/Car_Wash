package org.loose.fis.sre.exceptions;

public class WashTypeDoesNotAlreadyExists extends Exception {
    private String nume;

    public WashTypeDoesNotAlreadyExists(String nume) {
        super(String.format("Wash type %s does not exist!", nume));
        this.nume =nume;
    }

    public String getWashTypeName() {
        return nume;
    }
}
