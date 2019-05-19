package com.guestlogix.repository;


import com.guestlogix.infrastructure.CsvLoader;
import com.guestlogix.model.Airline;
import com.guestlogix.model.Airport;
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
public class AirportRepositoryImpl implements AirportRepository{

    @Autowired
    private CsvLoader csvLoader;

    private Map<String, Airport> airportMap;

    @Override
    public Map<String, Airport> getAirportMap() {
        return airportMap;
    }

    @PostConstruct
    public void init(){
        airportMap = new HashMap<>();

        List<Airport> airportList = new ArrayList<>();
        airportList.addAll(csvLoader.loadObjectList(Airport.class, "data/airports.csv" )) ;

        airportList.forEach( airport -> airportMap.putIfAbsent(airport.getCode(), airport));

        log.info("Repository Loading Airport:--> {} ", airportList);

    }
}
