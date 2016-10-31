package com.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Menerith on 31-Oct-16.
 */

@Entity
@Table(name="beer")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbeer")
    private int idbeer;
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
    private Set<Bar> bars;


}
