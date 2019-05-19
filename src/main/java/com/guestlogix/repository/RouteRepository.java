package com.guestlogix.repository;

import com.guestlogix.model.Route;

import java.util.Map;

public interface RouteRepository {

    Map<String, Route> getRouteMap();
}
