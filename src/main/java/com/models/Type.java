package com.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Menerith on 31-Oct-16.
 */
@Entity
@Table(name="type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbeertype")
    private int idbeer;
    @NotNull
    @Column(name = "name")
    private String name;

    public int getIdbeer() {
        return idbeer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
