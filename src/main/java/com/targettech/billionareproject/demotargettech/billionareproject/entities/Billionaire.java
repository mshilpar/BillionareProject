package com.targettech.billionareproject.demotargettech.billionareproject.entities;
import java.util.Arrays;
import java.util.Comparator;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Billionaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Image is mandatory")
    private String pic;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "netWorth is mandatory")
    private Double netWorth;
    @NotBlank(message = "companiesOwned is mandatory")
    private String companiesOwned;


    public Billionaire() {}

    public Billionaire(long id, @NotBlank(message = "Name is mandatory") String name, @NotNull(message = "netWorth is mandatory") Double netWorth, @NotBlank(message = "companiesOwned is mandatory") String companiesOwned) {
        this.id = id;
        this.name = name;
        this.netWorth = netWorth;
        this.companiesOwned = companiesOwned;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    public String getCompaniesOwned() {
        return companiesOwned;
    }

    public void setCompaniesOwned(String companiesOwned) {
        this.companiesOwned = companiesOwned;
    }

    @Override
    public String toString() {
        return "Billionaire{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                ", netWorth=" + netWorth +
                ", companiesOwned='" + companiesOwned + '\'' +
                '}';
    }
}

