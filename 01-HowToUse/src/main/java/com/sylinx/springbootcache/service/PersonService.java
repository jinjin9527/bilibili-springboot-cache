package com.sylinx.springbootcache.service;

import com.sylinx.springbootcache.entity.Person;
import com.sylinx.springbootcache.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personsRepository;

    public Optional<Person> findPersonById(Integer id){
        return this.personsRepository.findPersonById(id);
    }

}
