package com.guestlogix.repository;

import com.guestlogix.model.Airport;

import java.util.Map;

public interface AirportRepository {

    Map<String, Airport> getAirportMap();
}
