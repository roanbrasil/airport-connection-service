package com.guestlogix.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class RouteEdge {

    private final String id;
    private final Airport origin;
    private final Airport destination;
    private final Airline airline;
    private final int weight;
}