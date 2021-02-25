package com.owaldron.frfilmcatalogue.Model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private static final long id = 1L;

    private String name;
    private String types;
    private int pokeid;
    private int height;
    private int weight;
    private String sprite;
    private String moto;

    public static long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getPokeid() {
        return pokeid;
    }

    public void setPokeid(int pokeid) {
        this.pokeid = pokeid;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getMoto() {
        return moto;
    }

    public void setMoto(String moto) {
        this.moto = moto;
    }

    public Pokemon() {
    }
}
