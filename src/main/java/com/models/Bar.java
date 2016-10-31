package com.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Menerith on 31-Oct-16.
 */
@Entity
@Table(name="bar")
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbar")
    private int idbar;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "address")
    private String address;
    @NotNull
    @Column(name = "terrasse")
    private boolean terrasse;
    @NotNull
    @Column(name = "open_hours")
    private String open_hours;
    @NotNull
    @Column(name = "happy_hours")
    private String happy_hours;
    @NotNull
    @Column(name = "longitude")
    private Double longitude;
    @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "serve", joinColumns = @JoinColumn(name = "idbar", referencedColumnName = "idbar"),
            inverseJoinColumns = @JoinColumn(name = "idbeer", referencedColumnName = "idbeer"))
    private Set<Beer> beers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isTerrasse() {
        return terrasse;
    }

    public void setTerrasse(boolean terrasse) {
        this.terrasse = terrasse;
    }

    public String getOpen_hours() {
        return open_hours;
    }

    public void setOpen_hours(String open_hours) {
        this.open_hours = open_hours;
    }

    public String getHappy_hours() {
        return happy_hours;
    }

    public void setHappy_hours(String happy_hours) {
        this.happy_hours = happy_hours;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getIdbar() {
        return idbar;
    }

    public Set<Beer> getBeers() {
        return beers;
    }
}
