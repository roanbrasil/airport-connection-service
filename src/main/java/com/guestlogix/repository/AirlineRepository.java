package com.guestlogix.repository;

import com.guestlogix.model.Airline;

import java.util.Map;

public interface AirlineRepository {

    Map<String, Airline> retrieveAirline();
}
