package com.guestlogix.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "Name", "City", "Country", "IATA 3", "Latitute",  "Longitude"})
public class Airport {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("City")
    private String city;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("IATA 3")
    private String code;

    @JsonProperty("Latitute")
    private String latitute;

    @JsonProperty("Longitude")
    private String longitude;
}
