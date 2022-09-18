package com.sylinx.springbootcache.repository;

import com.sylinx.springbootcache.entity.Person;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@CacheConfig(cacheNames = "persons")
public class PersonRepository  {

    private static final List<Person> BASE_PERSON_LIST = new ArrayList<>();

    public PersonRepository(){
        Person person1 = Person.builder().id(1).name("person1").city("city1").build();
        Person person2 = Person.builder().id(2).name("person2").city("city2").build();
        Person person3 = Person.builder().id(3).name("person3").city("city3").build();
        BASE_PERSON_LIST.add(person1);
        BASE_PERSON_LIST.add(person2);
        BASE_PERSON_LIST.add(person3);
    }

    public Person insertPerson(Person person){
        Optional<Person> result = existsPerson(person);
        if(result.isEmpty()) {
            BASE_PERSON_LIST.add(person);
        }
        return result.isEmpty() ? person : null;
    }

    @CachePut(key="#person.id", unless="#result == null")
    public Person updatePerson(Person person){
        Optional<Person> result = doUpdatePerson(person);
        return result.orElse(null);
    }

    @Cacheable(key="#id")
    public Optional<Person> findPersonById(Integer id){
        return existsPerson(id);
    }

    @Cacheable(key="#p0", condition = "#p0 != 1", unless = "#result?.id == 2")
    public Optional<Person> findPersonByIdWithCondition(Integer id){
        return existsPerson(id);
    }

    @Cacheable(key="#p0", sync = true)
    public Optional<Person> findPersonByIdWithSync(Integer id){
        return existsPerson(id);
    }

    @Cacheable(cacheNames = {"multiple1", "multiple2"}, cacheManager = "multiOperationCacheManager", key="#p0")
    public Optional<Person> findPersonByIdWithMultiCaches(Integer id){
        return BASE_PERSON_LIST.stream().filter(person -> person.getId() == id).findAny();
    }

    @Caching(
            cacheable = {@Cacheable(cacheResolver = "multiCacheResolver", keyGenerator="myKeyGenerator", unless="#result == null")},
            put= {@CachePut(cacheResolver = "multiCacheResolver", keyGenerator="myKeyGenerator", unless="#result == null")},
            evict = {@CacheEvict(cacheResolver = "multiCacheResolver", keyGenerator="myKeyGenerator", condition = "#person.id == -1", allEntries = true)})
    public Person multiOperationPerson(Person person){
        Optional<Person> existsResult = existsPerson(person);
        if(existsResult.isEmpty()) {
            BASE_PERSON_LIST.add(person);
            return person;
        } else {
            Optional<Person> updateResult = doUpdatePerson(person);
            return updateResult.orElse(null);
        }
    }

    @CacheEvict(key="#id")
    public  Optional<Person> deletePerson(Integer id){
        Optional<Person> result = findPersonById(id);
        result.ifPresent(BASE_PERSON_LIST::remove);
        return result;
    }

    @CacheEvict(key="#id", allEntries = true)
    public  Optional<Person> deletePersonWithAllEntries(Integer id){
        Optional<Person> result = findPersonById(id);
        result.ifPresent(BASE_PERSON_LIST::remove);
        return result;
    }

    @CacheEvict(key="#id", beforeInvocation = true)
    public Optional<Person> deletePersonWithBeforeInvocation(Integer id) {
        Optional<Person> result = findPersonById(id);
        result.ifPresent(BASE_PERSON_LIST::remove);
        return result;
    }

    private Optional<Person> existsPerson(Person person) {
        return BASE_PERSON_LIST.stream().filter(streamPerson -> streamPerson.getId() == person.getId()).findAny();
    }

    private Optional<Person> existsPerson(Integer id) {
        return BASE_PERSON_LIST.stream().filter(streamPerson -> streamPerson.getId() == id).findAny();
    }

    private Optional<Person> doUpdatePerson(Person person) {
        Optional<Person> result = existsPerson(person);
        result.ifPresent(updateTarget -> updateTarget
                .setCity(person.getCity())
                .setName(person.getName())
        );
        return result;
    }

//    @Cacheable(cacheResolver = "multiCacheResolver", cacheManager = "multiOperationCacheManager", key="#p0")
    // 启动时报错
    // These attributes are mutually exclusive: the cache manager is used to configure a default cache resolver if none is set. If a cache resolver is set, the cache manager won't be used.
    public Optional<Person> testCacheManagerWithCacheResolver(Integer id) {
        return existsPerson(id);
    }

//    @Cacheable(key="#p0", keyGenerator = "myKeyGenerator")
    // 启动时报错
    //  These attributes are mutually exclusive: either set the SpEL expression used tocompute the key at runtime or set the name of the KeyGenerator bean to use.
    public Optional<Person> testKeyWithKeyGenerator(Integer id) {
        return existsPerson(id);
    }
}
