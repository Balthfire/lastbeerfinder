package com.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private Double description;
    @ManyToOne
    @JoinColumn(name = "idorigin")
    private Origin origin;
    @ManyToOne
    @JoinColumn(name = "idbeertype")
    private Type type;

    public Origin getOrigin(){
        return origin;
    }

    public Type getBeerType(){
        return type;
    }
}
