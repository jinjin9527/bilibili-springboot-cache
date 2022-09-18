package com.sylinx.springbootcache.person.controller;

import com.sylinx.springbootcache.entity.Person;
import com.sylinx.springbootcache.service.PersonService;
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

    @PostMapping("/findPerson1/{id}")
    public Optional<Person> findPerson1(@PathVariable Integer id){
        return this.personService.find1PersonById(id);
    }

    @PostMapping("/findPerson2/{id}")
    public Optional<Person> findPerson2(@PathVariable Integer id){
        return this.personService.find2PersonById(id);
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
