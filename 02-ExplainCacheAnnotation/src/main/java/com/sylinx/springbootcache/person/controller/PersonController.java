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

    @PostMapping("/findPersonById/{id}")
    public Optional<Person> findPersonById(@PathVariable Integer id){
        return this.personService.findPersonById(id);
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

    @PostMapping("/deletePersonWithAllEntries/{id}")
    public Optional<Person> deletePersonWithAllEntries(@PathVariable Integer id){
        Optional<Person> result = this.personService.deletePersonWithAllEntries(id);
        return result;
    }

    @PostMapping("/deletePersonWithBeforeInvocation/{id}")
    public Optional<Person> deletePersonWithBeforeInvocation(@PathVariable Integer id){
        Optional<Person> result = this.personService.deletePersonWithBeforeInvocation(id);
        return result;
    }

    @PostMapping("/findPersonByIdWithSync/{id}")
    public Optional<Person> findPersonByIdWithSync(@PathVariable Integer id){
        return this.personService.findPersonByIdWithSync(id);
    }

    @PostMapping("/findPersonWithCondition/{id}")
    public Optional<Person> findPersonWithCondition(@PathVariable Integer id){
        return this.personService.findPersonWithCondition(id);
    }

    @PostMapping("/findPersonByIdWithMultiCaches/{id}")
    public Optional<Person> findPersonByIdWithMultiCaches(@PathVariable Integer id){
        return this.personService.findPersonByIdWithMultiCaches(id);
    }

    @PostMapping("/testCacheManagerWithCacheResolver/{id}")
    public Optional<Person> testCacheManagerWithCacheResolver(@PathVariable Integer id){
        return this.personService.testCacheManagerWithCacheResolver(id);
    }

    @PostMapping("/testKeyWithKeyGenerator/{id}")
    public Optional<Person> testKeyWithKeyGenerator(@PathVariable Integer id){
        return this.personService.testKeyWithKeyGenerator(id);
    }

    @PostMapping("/insertPerson")
    public String insertPerson(@RequestBody Person person){
        Person result = this.personService.insertPerson(person);
        return result != null? "insert success": "insert fail";
    }

    @PostMapping("/multiOperationPerson")
    public Person multiOperationPerson(@RequestBody Person person){
        Person result = this.personService.multiOperationPerson(person);
        return result;
    }
}
