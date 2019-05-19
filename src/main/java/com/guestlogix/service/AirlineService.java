package com.guestlogix.service;

import com.guestlogix.model.Airline;
import com.guestlogix.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository repository;

    public Map<String, Airline> retrieveAirline(){
        return repository.retrieveAirline();
    }
}
