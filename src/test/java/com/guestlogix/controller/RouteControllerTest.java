package com.guestlogix.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guestlogix.exception.ApiError;
import com.guestlogix.model.Airline;
import com.guestlogix.model.Airport;
import com.guestlogix.service.RouteService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RouteController.class)
public class RouteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RouteService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getShortestPath() throws Exception {

        LinkedList<Airport> airportLinkedList = new LinkedList<>();

        Airport airport = new Airport("Changzhou Benniu Airport",
                "Changzhou",  "China", "CZX", "31.919701", "119.778999");
        airportLinkedList.add(airport);
        Airport airport2 = new Airport("Shenzhen Bao'an International Airport",
                "Shenzhen",  "China", "SZX", "22.63929939", "113.810997");
        airportLinkedList.add(airport2);
        Airport airport3= new Airport("Beijing Capital International Airport",
                "Beijing",  "China", "PEK", "40.08010101", "116.5849991");
        airportLinkedList.add(airport3);
        Airport airport4 = new Airport("San Francisco International Airport",
                "San Francisco",  "United States", "SFO", "37.61899948", "-122.375");
        airportLinkedList.add(airport4);

        when(this.service.getShortestPath("CZX", "SFO")).thenReturn(airportLinkedList);

        mvc.perform(get("/route/origin/CZX/destination/SFO")
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(response -> {
                    String json = response.getResponse().getContentAsString();
                    LinkedList<Airport> airports = new ObjectMapper().readValue(json,
                            new TypeReference<LinkedList<Airport>>() {});
                    assertThat(airports).hasSize(4);
                    assertThat(airports.getFirst()).isEqualToComparingFieldByField(airport);
                    assertThat(airports.getLast()).isEqualToComparingFieldByField(airport4);

                });

    }

    @Test
    public void notFoundShortestPath() throws Exception{
        when(this.service.getShortestPath("CZX", "CHI")).thenReturn(null);

        mvc.perform(get("/route/origin/CZX/destination/CHI")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(response -> {
                    String json = response.getResponse().getContentAsString();
                    ApiError apiError = new ObjectMapper().readValue(json,
                            ApiError.class);
                    assertThat(apiError.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
                    assertThat(apiError.getStatusCode()).isEqualTo(404);
                });
    }

}
