package com.guestlogix.controller;

import com.guestlogix.model.Airline;
import com.guestlogix.service.AirlineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/airline")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AirlineController {

    @Autowired
    private AirlineService service;

    @GetMapping("")
    public ResponseEntity<Map<String, Airline>> retrieveAirline(){

        return ResponseEntity.status(HttpStatus.OK).body(this.service.retrieveAirline());
    }
}
