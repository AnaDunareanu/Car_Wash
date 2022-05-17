package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class CarWash {
    @Id
    private String carwashname;
    private String administrator;
    private String address;

    public CarWash(String carwashname, String address, String administartor) {
        this.carwashname = carwashname;
        this.address = address;
        this.administrator = administartor;
    }

    public CarWash() {
    }

    public String getCarWashName() {
        return carwashname;
    }

    public void setCarWashName(String carwashname) {
        this.carwashname = carwashname;
    }

    public String getCarWashAdress() {
        return address;
    }

    public void setCarWashAdress(String adress) {
        this.address = adress;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarWash carwash = (CarWash) o;

        if (carwashname != null ? !carwashname.equals(carwash.carwashname) : carwash.carwashname != null) return false;
        if (address != null ? !address.equals(carwash.address) : carwash.address != null) return false;
        if (administrator != null ? !administrator.equals(carwash.administrator) : carwash.administrator != null) return false;
        return true;
    }

}
