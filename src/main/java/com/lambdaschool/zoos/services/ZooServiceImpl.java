package com.lambdaschool.zoos.services;

import com.lambdaschool.zoos.controllers.ZooController;
import com.lambdaschool.zoos.models.Zoo;
import com.lambdaschool.zoos.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "zooService")
public class ZooServiceImpl implements ZooService
{
    @Autowired
    ZooRepository zoorepos;

    @Override
    public List<Zoo> findAll()
    {
        List<Zoo> zoo = new ArrayList<>();
        zoorepos.findAll()
                .iterator()
                .forEachRemaining(zoo::add);
        return zoo;
    }

}
