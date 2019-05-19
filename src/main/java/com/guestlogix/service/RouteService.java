package com.guestlogix.service;

import com.guestlogix.model.*;
import com.guestlogix.repository.MemoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RouteService {

    @Autowired
    private MemoryRepository repository;

    private  List<Airport> nodes;
    private  List<RouteEdge> routeEdges;
    private Set<Airport> settledNodes;
    private Set<Airport> unSettledNodes;
    private Map<Airport, Airport> predecessors;
    private Map<Airport, Integer> distance;

    public LinkedList<Airport> getShortestPath(String origin, String destination) {

        this.setGraphData();
        this.execute(this.repository.getAirport(origin));

        LinkedList<Airport> path = new LinkedList<>();
        LinkedList<Route> route = new LinkedList<>();
        Airport step = this.repository.getAirport(destination);
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);

        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);

        return path;
    }

    private void setGraphData(){
        Graph graph = this.repository.getGraph();
        this.nodes = new ArrayList<>(graph.getVetexList());
        this.routeEdges = new ArrayList<>(graph.getRouteEdgeList());
    }

    private void execute(Airport origin) {
        this.settledNodes = new HashSet<>();
        this.unSettledNodes = new HashSet<>();
        this.distance = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.distance.put(origin, 0);
        this.unSettledNodes.add(origin);
        while (unSettledNodes.size() > 0) {
            Airport node = this.getMinimum(unSettledNodes);
            this.settledNodes.add(node);
            this.unSettledNodes.remove(node);
            this.findMinimalDistances(node);
        }
    }


    private void findMinimalDistances(Airport node) {
        List<Airport> adjacentNodes = this.getNeighbors(node);
        for (Airport target : adjacentNodes) {
            if (this.getShortestDistance(target) > this.getShortestDistance(node)
                    + this.getDistance(node, target)) {
                this.distance.put(target, this.getShortestDistance(node)
                        + this.getDistance(node, target));
                this.predecessors.put(target, node);
                this.unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Airport node, Airport target) {
        for (RouteEdge routeEdge : routeEdges) {
            if (routeEdge.getOrigin().equals(node)
                    && routeEdge.getDestination().equals(target)) {
                return routeEdge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Airport> getNeighbors(Airport node) {
        List<Airport> neighbors = new ArrayList<>();
        for (RouteEdge routeEdge : routeEdges) {
            if (routeEdge.getOrigin().equals(node)
                    && !this.isSettled(routeEdge.getDestination())) {
                neighbors.add(routeEdge.getDestination());
            }
        }
        return neighbors;
    }

    private Airport getMinimum(Set<Airport> vertexes) {
        Airport minimum = null;
        for (Airport vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (this.getShortestDistance(vertex) < this.getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Airport vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Airport destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

}