package com.lambdaschool.zoos.services;

import com.lambdaschool.zoos.models.Zoo;


import java.util.List;

public interface ZooService
{
    List<Zoo> findAll();

    Zoo findZooById(long id);

    Zoo save(Zoo zoo);

    Zoo update(Zoo zoo);

    void delete(long id);
}
