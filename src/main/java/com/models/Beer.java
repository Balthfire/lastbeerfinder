package com.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Created by Menerith on 31-Oct-16.
 */

@Entity
@Table(name="beer")
public class Beer {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbeer")
    private String idbeer;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "idorigin")
    private Origin origin;
    @ManyToOne
    @JoinColumn(name = "idbeertype")
    private Type type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "serve", joinColumns = @JoinColumn(name = "idbeer", referencedColumnName = "idbeer"),
            inverseJoinColumns = @JoinColumn(name = "idbar", referencedColumnName = "idbar"))
    private List<Bar> bars;

    public String getIdbeer() {
        return idbeer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @JsonBackReference
    public List<Bar> getBars() {
        return bars;
    }

}
