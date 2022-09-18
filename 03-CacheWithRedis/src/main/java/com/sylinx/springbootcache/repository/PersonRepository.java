package com.sylinx.springbootcache.repository;

import com.sylinx.springbootcache.entity.Person;
import org.springframework.cache.annotation.CacheEvict;
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
        BASE_PERSON_LIST.add(person1);
        BASE_PERSON_LIST.add(person2);
        BASE_PERSON_LIST.add(person3);
    }

    @Cacheable(value={"persons"
//            , "orders"
    })
    public Optional<Person> findPersonById(Integer id){
        System.out.println("findPerson is executed : " + id);
        return BASE_PERSON_LIST.stream().filter(person -> person.getId()==id).findAny();
    }
    @CacheEvict(value="persons", key="#id")
    public  Optional<Person> deletePerson(Integer id){
        Optional<Person> result = findPersonById(id);
        result.ifPresent(BASE_PERSON_LIST::remove);
        return result;
    }
    @CacheEvict(value="persons", key="#id", beforeInvocation = true)
    public Optional<Person> deletePersonWithBeforeInvocation(Integer id) {
        Optional<Person> result = findPersonById(id);
        result.ifPresent(BASE_PERSON_LIST::remove);
        return result;
    }
}
