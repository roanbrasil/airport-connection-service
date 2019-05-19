package com.guestlogix.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Airport other = (Airport) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return code + " - " + name + " - " + city + " - " + country + " - " + latitute + " - " +longitude;
    }
}
