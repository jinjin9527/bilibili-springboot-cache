package com.sylinx.springbootcache.person.controller;

import com.sylinx.springbootcache.entity.Person;
import com.sylinx.springbootcache.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/findPerson/{id}")
    public Optional<Person> findPerson(@PathVariable Integer id){
        return this.personService.findPersonById(id);
    }
}
