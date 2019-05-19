package com.guestlogix.controller;


import com.guestlogix.exception.EntityNotFoundException;
import com.guestlogix.model.Airport;
import com.guestlogix.model.Route;
import com.guestlogix.service.RouteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@Slf4j
@RestController
@RequestMapping("/route")
public class RouteController {

    private RouteService service;

    @Autowired
    public RouteController(RouteService service){
        this.service = service;
    }

    @GetMapping("/origin/{origin}/destination/{destination}")
    public ResponseEntity<LinkedList<Airport>> retrieveShortest(
            @PathVariable(value = "origin") String origin,
            @PathVariable(value = "destination") String destination) throws EntityNotFoundException {

        LinkedList<Airport> airportLinkedList = this.service.getShortestPath(origin, destination);
        if(airportLinkedList == null) {
            throw new EntityNotFoundException(
                    Route.class,
                    origin, "Not Found this Route from this Airport",
                    destination , "Not Found this Route to this Airport");
        }

        return ResponseEntity.status(HttpStatus.OK).body(airportLinkedList);
    }
}
