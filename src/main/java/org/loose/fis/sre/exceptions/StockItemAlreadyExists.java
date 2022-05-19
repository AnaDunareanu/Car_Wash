package org.loose.fis.sre.exceptions;

public class StockItemAlreadyExists extends Exception{
    private String nume;

    public StockItemAlreadyExists(String nume) {
        super(String.format("Item %s is already in stock!", nume));
        this.nume =nume;
    }

    public String getWashTypeName() {
        return nume;
    }
}
