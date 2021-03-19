package com.lambdaschool.zoos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "zooanimals")
@IdClass(ZooAnimalId.class)
public class ZooAnimal  extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "animalid")
    @JsonIgnoreProperties(value = "zoo", allowSetters = true)
    private Animal animal;

    @Id
    @ManyToOne
    @JoinColumn(name = "zooid")
    @JsonIgnoreProperties(value = "animal", allowSetters = true)
    private Zoo zoo;

    public ZooAnimal()
    {
        //JPA use
    }

    public ZooAnimal(Animal animal, Zoo zoo)
    {
        this.animal = animal;
        this.zoo = zoo;
    }

    public Animal getAnimal()
    {
        return animal;
    }

    public void setAnimal(Animal animal)
    {
        this.animal = animal;
    }

    public Zoo getZoo()
    {
        return zoo;
    }

    public void setZoo(Zoo zoo)
    {
        this.zoo = zoo;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZooAnimal that = (ZooAnimal) o;
        return ((this.getZoo() == null) ? 0 : this.getZoo().getZooid()) ==
                ((this.getZoo() == null) ? 0 : this.getZoo().getZooid()) &&
                ((this.getAnimal() == null) ? 0 : this.getAnimal().getAnimalid()) ==
                        ((this.getAnimal() == null) ? 0 : this.getAnimal().getAnimalid());
    }

    @Override
    public int hashCode()
    {
        return 75;
    }
}
