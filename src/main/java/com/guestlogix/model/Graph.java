package com.guestlogix.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Graph {

    private final List<Airport> vetexList;
    private final List<RouteEdge> routeEdgeList;

}