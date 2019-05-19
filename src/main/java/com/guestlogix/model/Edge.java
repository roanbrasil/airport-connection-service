package com.guestlogix.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Edge  {

    private final String id;
    private final Vertex origin;
    private final Vertex destination;
    private final int weight;
}