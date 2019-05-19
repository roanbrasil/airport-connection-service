package com.guestlogix.repository;

import com.guestlogix.model.Airport;
import com.guestlogix.model.Graph;

public interface MemoryRepository {

    Graph getGraph();

    Airport getAirport(String key);

}
