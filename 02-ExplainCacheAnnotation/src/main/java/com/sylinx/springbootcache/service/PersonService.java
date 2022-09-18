package com.sylinx.springbootcache.service;

import com.sylinx.springbootcache.entity.Person;
import com.sylinx.springbootcache.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personsRepository;

    public Optional<Person> findPersonById(Integer id){
        return this.personsRepository.findPersonById(id);
    }
    public Optional<Person> findPersonByIdWithSync(Integer id){
        return this.personsRepository.findPersonByIdWithSync(id);
    }
    public Optional<Person> findPersonByIdWithMultiCaches(Integer id){
        return this.personsRepository.findPersonByIdWithMultiCaches(id);
    }
    public Optional<Person> findPersonWithCondition(Integer id) {
        return this.personsRepository.findPersonByIdWithCondition(id);
    }

    public Optional<Person> deletePerson(Integer id){
        return this.personsRepository.deletePerson(id);
    }

    public Optional<Person> deletePersonWithAllEntries(Integer id) {
        return this.personsRepository.deletePersonWithAllEntries(id);
    }

    public Optional<Person> deletePersonWithBeforeInvocation(Integer id) {
        return this.personsRepository.deletePersonWithBeforeInvocation(id);
    }

    public Person updatePerson(Person person){
        return this.personsRepository.updatePerson(person);
    }

    public Person multiOperationPerson(Person person){
        return this.personsRepository.multiOperationPerson(person);
    }

    public Person insertPerson(Person person){
        return this.personsRepository.insertPerson(person);
    }

    public Optional<Person> testCacheManagerWithCacheResolver(Integer id) {
        return this.personsRepository.testCacheManagerWithCacheResolver(id);
    }

    public Optional<Person> testKeyWithKeyGenerator(Integer id) {
        return this.personsRepository.testKeyWithKeyGenerator(id);
    }
}
