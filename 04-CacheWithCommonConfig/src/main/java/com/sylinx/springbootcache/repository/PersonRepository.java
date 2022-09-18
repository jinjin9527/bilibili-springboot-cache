package com.sylinx.springbootcache.repository;

import com.sylinx.springbootcache.entity.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonRepository  {

    private static List<Person> BASE_PERSON_LIST = new ArrayList<>();

    public PersonRepository(){
        Person person1 = Person.builder().id(1).name("person1").city("city1").build();
        Person person2 = Person.builder().id(2).name("person2").city("city2").build();
        Person person3 = Person.builder().id(3).name("person3").city("city3").build();
        Person person13 = Person.builder().id(13).name("person13").city("city13").build();
        BASE_PERSON_LIST.add(person1);
        BASE_PERSON_LIST.add(person2);
        BASE_PERSON_LIST.add(person3);
        BASE_PERSON_LIST.add(person13);
    }

    public boolean insertPerson(Person person){
        boolean exists = BASE_PERSON_LIST.stream().anyMatch(streamPerson -> streamPerson.getId() == person.getId());
        if(!exists) {
            BASE_PERSON_LIST.add(person);
        }
        return !exists;
    }

    @CachePut(value="persons")
    public Person updatePerson(Person person){
        Optional<Person> result = findPersonById(person.getId());
        result.ifPresent(updateTarget -> updateTarget
                .setCity(person.getCity())
                .setName(person.getName())
        );
        return result.orElse(null);
    }

    @CacheEvict(value="persons")
    public  Optional<Person> deletePerson(Integer id){
        Optional<Person> result = findPersonById(id);
        result.ifPresent(person -> BASE_PERSON_LIST.remove(person));
        return result;
    }

    @Cacheable(value="persons")
    public Optional<Person> findPersonById(Integer id){
        return BASE_PERSON_LIST.stream().filter(person -> person.getId() == id).findAny();
    }

    @Cacheable(value="persons")
    public Optional<Person> findPersonByIdWithTransaction(Integer id){
        if (id == 2) throw new RuntimeException("2");
        return BASE_PERSON_LIST.stream().filter(person -> person.getId() == id).findAny();
    }

    @Cacheable(value="persons")
    public Optional<Person> findPersonByIdWithoutTransaction(Integer id){
        return BASE_PERSON_LIST.stream().filter(person -> person.getId() == id).findAny();
    }
}
