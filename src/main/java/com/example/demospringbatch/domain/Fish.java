package com.example.demospringbatch.domain;

public class Fish {
    Long id;
    String name;
    Long sizeMin;
    Long sizeMax;

    public Fish() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSizeMin() {
        return sizeMin;
    }

    public void setSizeMin(Long sizeMin) {
        this.sizeMin = sizeMin;
    }

    public Long getSizeMax() {
        return sizeMax;
    }

    public void setSizeMax(Long sizeMax) {
        this.sizeMax = sizeMax;
    }
}
