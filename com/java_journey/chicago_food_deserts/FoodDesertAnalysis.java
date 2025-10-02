package com.java_journey.chicago_food_deserts;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FoodDesertAnalysis {

    public static void main(String[] args) {
        try {
            DataReader dataReader = new DataReader();
            List<Store> stores = dataReader.getStores();
            List<Neighborhood> neighborhoods = dataReader.getNeighborhoods();

            // --- Geospatial Analysis ---
            System.out.println("\nStarting geospatial analysis...");
            GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

            // Create 1/2-mile buffers around each store.
            // Note: This is an approximation. 1 degree of latitude is ~69 miles.
            // 0.5 miles / 69 miles/degree ≈ 0.0072 degrees.
            double bufferDistanceDegrees = 0.0072;
            
            List<Geometry> buffers = new ArrayList<>();
            for (Store store : stores) {
                Point point = geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(store.longitude(), store.latitude()));
                buffers.add(point.buffer(bufferDistanceDegrees));
            }

            // Merge all buffers into a single "accessible zone"
            Geometry accessibleZones = buffers.stream().reduce(Geometry::union).orElse(geometryFactory.createPolygon());
            
            // Find food deserts by finding the difference between neighborhoods and the accessible zone
            List<Neighborhood> foodDeserts = new ArrayList<>();
            for (Neighborhood neighborhood : neighborhoods) {
                Geometry desertArea = neighborhood.geometry().difference(accessibleZones);
                if (!desertArea.isEmpty()) {
                    foodDeserts.add(new Neighborhood(neighborhood.name(), desertArea));
                }
            }
            System.out.println("Geospatial analysis finished.");

            // --- Deeper Analysis: Population ---
            System.out.println("\nStarting population analysis...");
            // Manually created demographics data, similar to the Python notebook
            Map<String, Integer> populationData = Map.ofEntries(
                Map.entry("ROGERS PARK", 54991), Map.entry("WEST RIDGE", 77085),
                Map.entry("LINCOLN SQUARE", 44439), Map.entry("AUBURN GRESHAM", 48743),
                Map.entry("SOUTH CHICAGO", 30374), Map.entry("OHARE", 12790),
                Map.entry("SOUTH DEERING", 13735), Map.entry("AUSTIN", 98218),
                Map.entry("EAST SIDE", 21724), Map.entry("ASHBURN", 46415),
                Map.entry("DUNNING", 41816), Map.entry("MORGAN PARK", 21739)
            );

            // Filter for deserts in neighborhoods where we have population data
            List<Neighborhood> desertsWithPop = foodDeserts.stream()
                .filter(d -> populationData.containsKey(d.name()))
                .collect(Collectors.toList());

            // Calculate total affected population (simplification: counts full population if any part is a desert)
            long totalAffectedPopulation = desertsWithPop.stream()
                .map(d -> populationData.get(d.name()))
                .distinct() // Avoid double counting if a neighborhood is split into multiple geometries
                .mapToLong(Integer::longValue)
                .sum();
            
            System.out.printf("Total estimated population living in a food desert in Chicago: %,d\n", totalAffectedPopulation);


            // --- Visualization ---
            MapGenerator mapGenerator = new MapGenerator();
            mapGenerator.generateMap(accessibleZones, foodDeserts, stores);

        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred during the analysis: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
