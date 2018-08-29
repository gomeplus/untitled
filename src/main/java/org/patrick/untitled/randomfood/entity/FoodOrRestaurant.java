package org.patrick.untitled.randomfood.entity;

import javax.persistence.*;

@Entity
@Table
public class FoodOrRestaurant {
    @Id
    @Column
    private String name;

    @Column
    private String note;

    public FoodOrRestaurant() {

    }

    public FoodOrRestaurant(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
