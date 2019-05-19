package com.guestlogix.repository;

import com.guestlogix.infrastructure.CsvLoader;
import com.guestlogix.model.Airport;
import com.guestlogix.model.Route;
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
public class RouteRepositoryImpl implements RouteRepository{

    @Autowired
    private CsvLoader csvLoader;

    private Map<String, Route> routeMap;

    @Override
    public Map<String, Route> getRouteMap() {
        return routeMap;
    }


    @PostConstruct
    public void init(){
        routeMap = new HashMap<>();

        List<Route> routeList = new ArrayList<>();
        routeList.addAll(csvLoader.loadObjectList(Route.class, "data/routes.csv" )) ;

        routeList.forEach( route -> routeMap.putIfAbsent(route.getAirlineId(), route));

        log.info("Repository Loading Route:--> {} ", routeList);

    }
}
