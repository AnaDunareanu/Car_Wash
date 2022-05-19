package org.loose.fis.sre.exceptions;

public class StockItemDoesNotAlreadyExist extends Exception{

    private String nume;

    public StockItemDoesNotAlreadyExist(String nume) {
        super(String.format("Wash type %s does not exist!", nume));
        this.nume =nume;
    }

    public String getWashTypeName() {
        return nume;
    }
}
