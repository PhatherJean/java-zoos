package com.lambdaschool.zoos.repositories;

import com.lambdaschool.zoos.models.Zoo;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ZooRepository extends CrudRepository<Zoo, Long>
{
    // ??
//    ArrayList<Zoo> findByZoonameContainingIgnoringCase(String name);
}
