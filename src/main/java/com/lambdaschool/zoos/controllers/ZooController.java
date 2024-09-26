package com.lambdaschool.zoos.controllers;

import com.lambdaschool.zoos.models.Zoo;
import com.lambdaschool.zoos.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/zoos")
public class ZooController
{
    @Autowired
    private ZooService zooService;

    @GetMapping(value = "/zoos", produces = "application/json")
    public ResponseEntity<?> listAllZoos()
    {
        List<Zoo> zoo = zooService.findAll();
        return new ResponseEntity<>(zoo, HttpStatus.OK);
    }

    @GetMapping(value = "/zoo/{id}", produces = "application/json")
    public ResponseEntity<?> findZooById(@PathVariable long id)
    {
        Zoo z = zooService.findZooById(id);
        return new ResponseEntity<>(z, HttpStatus.OK);
    }

    @PostMapping(value = "/zoo", consumes = "application/json")
    public ResponseEntity<?> addNewZoo(@Valid @RequestBody Zoo newZoo)
        throws URISyntaxException
    {
        newZoo.setZooid(0);
        newZoo = zooService.save(newZoo);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newZooURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{zooid}")
                .buildAndExpand(newZoo.getZooid())
                .toUri();
        responseHeaders.setLocation(newZooURI);

        return new ResponseEntity<>(null, responseHeaders,HttpStatus.CREATED);
    }

    @PutMapping(value = "/zoo/{id}", consumes = "application/json")
    public ResponseEntity<?> putUpdateZoo(
            @RequestBody
            Zoo updateZoo,
            @PathVariable
            long id)
    {
        updateZoo.setZooid(id);
        zooService.save(updateZoo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/zoo/{id}", consumes = "application/json")
    public ResponseEntity<?> patchUpdateZoo(
            @RequestBody
            Zoo updateZoo,
            @PathVariable
            long id)
    {
        updateZoo.setZooid(id);
        zooService.update(updateZoo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/zoo/{id}")
    public ResponseEntity<?> deleteZoo(
            @PathVariable
            long id)
    {
        zooService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
