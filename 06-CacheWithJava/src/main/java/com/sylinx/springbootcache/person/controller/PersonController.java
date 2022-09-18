package com.sylinx.springbootcache.person.controller;

import com.sylinx.springbootcache.person.entity.Person;
import com.sylinx.springbootcache.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/findPerson/{id}")
    public Optional<Person> findPerson(@PathVariable Integer id){
        Optional<Person> person = this.personService.findPersonById(id);
        return person;
    }

    @PostMapping("/insertPerson")
    public String insertPerson(@RequestBody Person person){
        boolean result = this.personService.insertPerson(person);
        return result?"insert success":"insert fail";
    }

    @PostMapping("/updatePerson")
    public Person updatePerson(@RequestBody Person person){
        Person result = this.personService.updatePerson(person);
        return result;
    }

    @PostMapping("/deletePerson/{id}")
    public Optional<Person> deletePerson(@PathVariable Integer id){
        Optional<Person> result = this.personService.deletePerson(id);
        return result;
    }
}
