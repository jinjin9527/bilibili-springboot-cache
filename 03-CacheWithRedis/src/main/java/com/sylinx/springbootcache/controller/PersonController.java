package com.sylinx.springbootcache.controller;

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

    @PostMapping("/deletePerson/{id}")
    public Optional<Person> deletePerson(@PathVariable Integer id){
        Optional<Person> result = this.personService.deletePerson(id);
        return result;
    }

    @PostMapping("/deletePersonWithBeforeInvocation/{id}")
    public Optional<Person> deletePersonWithBeforeInvocation(@PathVariable Integer id){
        Optional<Person> result = this.personService.deletePersonWithBeforeInvocation(id);
        return result;
    }
}
