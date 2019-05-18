package com.guestlogix.domain;

import com.guestlogix.infrastructure.CsvLoader;
import com.guestlogix.model.Airline;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class AirlineCsvLoadService {

    private List<Airline> airlineList;

    @Autowired
    private CsvLoader csvLoader;

    @PostConstruct
    public void init(){
        airlineList = new ArrayList<>();
        airlineList.addAll(csvLoader.loadObjectList(Airline.class, "data/airlines.csv" )) ;
        System.out.println(airlineList);

    }
}
