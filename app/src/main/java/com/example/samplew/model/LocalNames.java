package com.example.samplew.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalNames {
    @SerializedName("ca")
    @Expose
    private String ca;
    @SerializedName("es")
    @Expose
    private String es;

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }
}
