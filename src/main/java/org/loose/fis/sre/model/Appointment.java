package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Appointment {
    @Id
    private int nr;
    private String username;
    private String carwash;
    private Integer nCoins;
    private Integer sTime;

    public Appointment(int nr, String username, String carwash, Integer nCoins, Integer sTime) {
        this.nr = nr;
        this.username = username;
        this.carwash = carwash;
        this.nCoins = nCoins;
        this.sTime = sTime;
    }

    public Appointment() {
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarwash() {
        return carwash;
    }

    public void setCarwash(String carwash) {
        this.carwash = carwash;
    }

    public Integer getnCoins() {
        return nCoins;
    }

    public void setnCoins(Integer nCoins) {
        this.nCoins = nCoins;
    }

    public Integer getsTime() { return sTime; }

    public void setsTime(Integer sTime) { this.sTime = sTime; }
}
