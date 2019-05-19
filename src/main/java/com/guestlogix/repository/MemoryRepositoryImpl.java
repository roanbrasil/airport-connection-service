package com.guestlogix.repository;

import com.guestlogix.infrastructure.CsvLoader;
import com.guestlogix.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class MemoryRepositoryImpl implements MemoryRepository {

    @Autowired
    private CsvLoader csvLoader;

    private Map<String, Airport> airportMap;
    private Map<String, Airline> airlineMap;

    //vertex
    private List<Airport> airportList;
    //edge
    private List<RouteEdge> routeRouteEdgeList;

    private Graph graph;

    @PostConstruct
    public void init(){

        this.loadAirport();
        this.loadAirline();
        this.loadRoute();

        this.graph = new Graph(this.airportList, this.routeRouteEdgeList);
    }

    private void loadRoute(){
        List<Route> localRouteList = new ArrayList<>();
        localRouteList.addAll(this.csvLoader.loadObjectList(Route.class, "data/routes.csv" )) ;

        this.routeRouteEdgeList = localRouteList.stream()
                .map(route -> {
                    Airport origin = this.airportMap.get(route.getOrigin());
                    Airport destination = this.airportMap.get(route.getDestination());
                    Airline airline = this.airlineMap.get(route.getAirlineId());
                    if(origin == null){
                        origin = new Airport();
                        origin.setCode(route.getOrigin());
                        origin = this.saveUnknownAirport(origin, route);

                        this.airportMap.putIfAbsent(route.getOrigin(), origin);
                    }
                    if(destination == null){
                        destination = new Airport();
                        destination.setCode(route.getDestination());
                        destination =  this.saveUnknownAirport(destination, route);

                        this.airportMap.putIfAbsent(route.getDestination(), destination);
                    }

                    final RouteEdge routeEdge = new RouteEdge(route.getAirlineId(), route, origin, destination, airline, 1);
                    return routeEdge;
                })
                .collect(Collectors.toList());
    }

    private Airport saveUnknownAirport(Airport airport, Route route){
        airport.setCity("Unknown City Name");
        airport.setCountry("Unknown Country Name");
        airport.setName("Unknown Airport Name");
        airport.setLatitute("Unknown Latitute");
        airport.setLongitude("Unknown Longitude");

        return airport;
    }

    private void loadAirport(){
        this.airportMap = new HashMap<>();

        List<Airport> localAirportList = new ArrayList<>();
        localAirportList.addAll(this.csvLoader.loadObjectList(Airport.class, "data/airports.csv" )) ;

        this.airportMap = localAirportList.stream()
                .filter( airport -> !("\\N").equals(airport.getCode()))
                .collect(Collectors.toMap(Airport::getCode, airport -> airport));

        this.airportList = localAirportList.stream()
                .filter( airport -> !("\\N").equals(airport.getCode()))
                .collect(Collectors.toList());
    }

    private void loadAirline(){
        this.airlineMap = new HashMap<>();

        List<Airline> localAirlineList = new ArrayList<>();
        localAirlineList.addAll(this.csvLoader.loadObjectList(Airline.class, "data/airlines.csv" )) ;

        this.airlineMap = localAirlineList.stream()
                .collect(Collectors.toMap(Airline::getTwoDigitCode, airline -> airline));

    }

    @Override
    public Graph getGraph() {
        return this.graph;
    }

    @Override
    public Airport getAirport(String key){
        return this.airportMap.get(key);
    }

}
