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
@Table(name="type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idbeertype")
    private int idbeer;
    @NotNull
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Beer> beers;


    public int getIdbeer() {
        return idbeer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonBackReference
    public List<Beer> getBeers() {
        return beers;
    }
}
