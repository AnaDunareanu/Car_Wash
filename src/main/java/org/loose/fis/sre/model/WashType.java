package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class WashType {
    @Id
    private String carwashname;
    private String nume;

    public WashType(String carwashname, String nume) {
        this.carwashname = carwashname;
        this.nume = nume;
    }

    public WashType() {
    }

    public String getCarWashName() {
        return carwashname;
    }

    public void setCarWashName(String carwashname) {
        this.carwashname = carwashname;
    }

    public String getWashTypeName() {
        return nume;
    }

    public void setWashTypeName(String nume) {
        this.nume = nume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WashType wash = (WashType) o;

        if (carwashname != null ? !carwashname.equals(wash.carwashname) : wash.carwashname != null) return false;
        if (nume != null ? !nume.equals(wash.nume) : wash.nume != null) return false;
        return true;
    }

}
