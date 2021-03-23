package com.lambdaschool.zoos.services;

import com.lambdaschool.zoos.controllers.ZooController;
import com.lambdaschool.zoos.models.Animal;
import com.lambdaschool.zoos.models.Telephone;
import com.lambdaschool.zoos.models.Zoo;
import com.lambdaschool.zoos.models.ZooAnimal;
import com.lambdaschool.zoos.repositories.AnimalRepository;
import com.lambdaschool.zoos.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "zooService")
public class ZooServiceImpl implements ZooService
{
    @Autowired
    ZooRepository zoorepos;

    @Autowired
    private AnimalRepository animalrepos;

    @Override
    public List<Zoo> findAll()
    {
        List<Zoo> zoo = new ArrayList<>();
        zoorepos.findAll()
                .iterator()
                .forEachRemaining(zoo::add);
        return zoo;
    }

    @Override
    public Zoo findZooById(long id)
    {
        return zoorepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Zoo id "+ id + " not found!"));
    }

    @Transactional
    @Override
    public Zoo save(Zoo zoo)
    {
        Zoo newZoo = new Zoo();

        if (zoo.getZooid() != 0)
        {
            Zoo oldZoo = zoorepos.findById(zoo.getZooid())
                    .orElseThrow(()-> new EntityNotFoundException("Zoo id "+ zoo.getZooid() + " not found!"));

            newZoo.setZooid(zoo.getZooid());
        }

        newZoo.setZooname(zoo.getZooname());

        newZoo.getAnimals()
                .clear();
        for (ZooAnimal za : zoo.getAnimals())
        {
            Animal newAnimal = animalrepos.findById(za.getAnimal()
            .getAnimalid())
                    .orElseThrow(()-> new EntityNotFoundException("Animal id " + za.getAnimal().getAnimalid()+ " not found!"));

            newZoo.getAnimals()
                    .add(new ZooAnimal(newZoo, newAnimal, za.getIncomingzoo()));
        }

        newZoo.getTelephones()
                .clear();
        for (Telephone t : zoo.getTelephones())
        {
            newZoo.getTelephones()
                    .add(new Telephone(t.getPhonetype(),
                            t.getPhonenumber(), newZoo));
        }
        return zoorepos.save(newZoo);
    }

    @Transactional
    @Override
    public Zoo update(Zoo zoo)
    {
        Zoo currentZoo = zoorepos.findById(zoo.getZooid())
                .orElseThrow(()-> new EntityNotFoundException("Zoo id " + zoo.getZooid()));
        if (zoo.getZooname() != null)
        {
            currentZoo.setZooname(zoo.getZooname());
        }

        if (zoo.getAnimals().size() > 0)
        {
            for (ZooAnimal za : zoo.getAnimals())
            {
                Animal newAnimal = animalrepos.findById(za.getAnimal()
                .getAnimalid())
                        .orElseThrow(()-> new EntityNotFoundException("Animal id " + za.getAnimal().getAnimalid()));
                currentZoo.getAnimals()
                        .add(new ZooAnimal(currentZoo, newAnimal, za.getIncomingzoo()));
            }
        }

        if (zoo.getTelephones().size() > 0)
        {
            currentZoo.getAnimals()
                    .clear();
            for (Telephone t : zoo.getTelephones())
            {
                currentZoo.getTelephones()
                        .add(new Telephone(t.getPhonetype(),
                                t.getPhonenumber(),
                                currentZoo));
            }
        }

        return zoorepos.save(currentZoo);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (zoorepos.findById(id)
        .isPresent())
        {
            zoorepos.deleteById(id);
        }else
        {
            throw new EntityNotFoundException("Zoo id " + id + " not found!");
        }
    }
}
