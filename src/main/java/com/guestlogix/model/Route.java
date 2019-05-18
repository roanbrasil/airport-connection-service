package com.guestlogix.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "Airline Id", "Origin", "Destination"})
public class Route {

    @JsonProperty("Airline Id")
    private String airlineId;

    @JsonProperty("Origin")
    private String origin;

    @JsonProperty("Destination")
    private String destination;

}
