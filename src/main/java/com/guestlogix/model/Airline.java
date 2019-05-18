package com.guestlogix.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "Name", "2 Digit Code", "3 Digit Code", "Country" })
public class Airline {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("2 Digit Code")
    private String twoDigitCode;

    @JsonProperty("3 Digit Code")
    private String threeDigitCode;

    @JsonProperty("Country")
    private String country;
}
