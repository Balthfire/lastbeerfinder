package com.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Menerith on 31-Oct-16.
 */
@Entity
@Table(name="origin")
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorigin")
    private int idorigin;
    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL)
    private Set<Beer> beers;

    public int getIdorigin() {
        return idorigin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Beer> getBeers() {
        return beers;
    }
}
