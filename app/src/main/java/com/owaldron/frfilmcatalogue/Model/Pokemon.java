package com.owaldron.frfilmcatalogue.Model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private static final long id = 1L;

    private String name;
    private String types;
    private int pokeid;
    private String height;
    private String weight;
    private String desc;
    private String sprite;
    private String moto;
    private int gen;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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
