package com.guestlogix.repository;

import com.guestlogix.infrastructure.CsvLoader;
import com.guestlogix.model.Airline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class AirlineRepositoryImpl implements AirlineRepository {

    @Autowired
    private CsvLoader csvLoader;

    private Map<String, Airline> airlineMap;

    @Override
    public Map<String, Airline> retrieveAirline() {
        return airlineMap;
    }

    @PostConstruct
    public void init(){
        airlineMap = new HashMap<>();

        List<Airline> airlineList = new ArrayList<>();
        airlineList.addAll(csvLoader.loadObjectList(Airline.class, "data/airlines.csv" )) ;

        airlineList.forEach( airline -> airlineMap.putIfAbsent(airline.getTwoDigitCode(), airline));

        log.info("Repository Loading Airline:--> {} ", airlineList);

    }

}
