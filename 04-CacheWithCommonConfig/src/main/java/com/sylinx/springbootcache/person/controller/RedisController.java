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
public class RedisController {

    @Autowired
    private PersonService personService;

    @PostMapping("/findPerson/{id}")
    public Optional<Person> findPerson(@PathVariable Integer id){
        return this.personService.findPersonById(id);
    }

    @PostMapping("/findPersonWithTransaction/{id}")
    public Optional<Person> findPersonWithTransaction(@PathVariable Integer id) throws Exception {
        Optional<Person> personById = this.personService.findPersonByIdWithTransaction(id);
        return personById;
    }

    @PostMapping("/findPersonWithoutTransaction/{id}")
    public Optional<Person> findPersonWithoutTransaction(@PathVariable Integer id) throws Exception {
        Optional<Person> personById = this.personService.findPersonByIdWithoutTransaction(id);
        return personById;
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
