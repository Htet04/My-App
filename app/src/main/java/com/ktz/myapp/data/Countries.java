package com.ktz.myapp.data;

public class Countries {
    private String name,Iso2,Iso3;

    public Countries(String name, String iso2, String iso3) {
        this.name = name;
        Iso2 = iso2;
        Iso3 = iso3;
    }

    public String getName() {
        return name;
    }

    public String getIso2() {
        return Iso2;
    }

    public String getIso3() {
        return Iso3;
    }
}
