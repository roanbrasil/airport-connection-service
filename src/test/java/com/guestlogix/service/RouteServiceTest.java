package com.guestlogix.service;


import com.guestlogix.model.*;
import com.guestlogix.repository.MemoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {

    private RouteService service ;

    @Mock
    private MemoryRepository repository;

    private List<RouteEdge> edges;
    private List<Airport> nodes;

    @Before
    public void setUp() {
        service  = new RouteService(this.repository);
    }

    @Test
    public void getShortestPath(){
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        Airport airport = new Airport("Changzhou Benniu Airport",
                "Changzhou",  "China", "CZX", "31.919701", "119.778999");
        nodes.add(airport);
        Airport airport2 = new Airport("Shenzhen Bao'an International Airport",
                "Shenzhen",  "China", "SZX", "22.63929939", "113.810997");
        nodes.add(airport2);
        Airport airport3= new Airport("Beijing Capital International Airport",
                "Beijing",  "China", "PEK", "40.08010101", "116.5849991");
        nodes.add(airport3);
        Airport airport4 = new Airport("San Francisco International Airport",
                "San Francisco",  "United States", "SFO", "37.61899948", "-122.375");
        nodes.add(airport4);

        addLane("AC", new Route("AC", "CZX", "SZX"), airport, airport2, null, 1);
        addLane("AC", new Route("AC", "SZX", "PEK"), airport2, airport3, null, 1);
        addLane("AC", new Route("AC", "PEK", "SFO"), airport3, airport4, null, 1);
        addLane("AC", new Route("AC", "SFO", "SZX"), airport4, airport2, null, 1);

        Graph graph = new Graph(nodes, edges);

        when(this.repository.getGraph()).thenReturn(graph);
        LinkedList<Airport> airportLinkedList = this.service.getShortestPath("CZX", "SZX");
        verify(repository, atLeastOnce()).getGraph();
        verify(this.repository, times(1)).getAirport("CZX");
    }

    private void addLane(String airlineId, Route route, Airport origin, Airport destination, Airline airline, int weight) {
        RouteEdge lane = new RouteEdge( airlineId, route, origin, destination, airline, weight);
        edges.add(lane);
    }
}
