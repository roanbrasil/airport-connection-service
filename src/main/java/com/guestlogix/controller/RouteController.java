package com.guestlogix.controller;


import com.guestlogix.model.Airport;
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
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RouteController {

    @Autowired
    private RouteService service;

    @GetMapping("/origin/{origin}/destination/{destination}")
    public ResponseEntity<LinkedList<Airport>> retrieveShortest(
            @PathVariable(value = "origin") String origin,
            @PathVariable(value = "destination") String destination){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getShortestPath(origin, destination));
    }
}
