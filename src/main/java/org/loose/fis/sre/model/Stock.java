package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Stock {
    @Id
    private int nr;
    private String carwashname;
    private String stockName;

    public Stock(String carwashname, String stockName,int nr) {
        this.carwashname = carwashname;
        this.stockName = stockName;
        this.nr=nr;
    }

    public Stock() {
    }

    public int getNr() {return nr;}

    public void setNr(int nr) {this.nr = nr;}

    public String getCarWashName() {
        return carwashname;
    }

    public void setCarWashName(String carwashname) {
        this.carwashname = carwashname;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String nume) {
        this.stockName = nume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stk = (Stock) o;

        if (carwashname != null ? !carwashname.equals(stk.carwashname) : stk.carwashname != null) return false;
        if (stockName != null ? !stockName.equals(stk.stockName) : stk.stockName != null) return false;
        return true;
    }
}
