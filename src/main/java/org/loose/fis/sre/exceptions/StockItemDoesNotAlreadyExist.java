package org.loose.fis.sre.exceptions;

public class StockItemDoesNotAlreadyExist extends Exception{

    private String nume;

    public StockItemDoesNotAlreadyExist(String nume) {
        super(String.format("The item %s does not exist in stock!", nume));
        this.nume =nume;
    }

    public String getWashTypeName() {
        return nume;
    }
}
