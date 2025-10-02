package com.java_journey.chicago_food_deserts;

import org.json.JSONArray;
import org.json.JSONObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles fetching and parsing data from the City of Chicago's public APIs.
 */
public class DataReader {

    private final HttpClient client = HttpClient.newHttpClient();
    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    /**
     * Fetches data from a given URL as a String.
     */
    private String fetchData(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Fetches and parses full-service grocery stores.
     */
    public List<Store> getStores() throws IOException, InterruptedException {
        System.out.println("Loading data directly from Chicago Data Portal APIs...");
        String url = "https://data.cityofchicago.org/resource/uupf-x98e.json?$limit=50000";
        String jsonData = fetchData(url);

        List<Store> stores = new ArrayList<>();
        JSONArray rawData = new JSONArray(jsonData);
        System.out.println("Initial data loaded. Total records: " + rawData.length());

        Set<String> excludedWords = Set.of("LIQUOR", "GAS", "TOBACCO", "CELL", "PHARMACY");

        for (int i = 0; i < rawData.length(); i++) {
            JSONObject obj = rawData.getJSONObject(i);
            String businessActivity = obj.optString("business_activity", "").toUpperCase();
            String dbaName = obj.optString("dba_name", "").toUpperCase();
            
            // Positive filtering: Must be a grocery store
            if (businessActivity.contains("GROCERY STORE")) {
                // Negative filtering: Exclude stores likely not to be full-service
                boolean isExcluded = excludedWords.stream().anyMatch(dbaName::contains);
                if (!isExcluded && obj.has("latitude") && obj.has("longitude")) {
                    double lat = obj.getDouble("latitude");
                    double lon = obj.getDouble("longitude");
                    stores.add(new Store(obj.getString("dba_name"), lat, lon));
                }
            }
        }
        System.out.println("Final number of full-service grocery stores found: " + stores.size());
        return stores;
    }

    /**
     * Fetches and parses neighborhood boundary data into JTS Polygon objects.
     */
    public List<Neighborhood> getNeighborhoods() throws IOException, InterruptedException {
        String url = "https://data.cityofchicago.org/resource/igwz-8jzy.json";
        String jsonData = fetchData(url);

        List<Neighborhood> neighborhoods = new ArrayList<>();
        JSONArray rawData = new JSONArray(jsonData);

        for (int i = 0; i < rawData.length(); i++) {
            JSONObject obj = rawData.getJSONObject(i);
            String communityName = obj.getString("community").toUpperCase();
            JSONObject multiPolygonJson = obj.getJSONObject("the_geom");
            
            // GeoJSON describes polygons with an outer shell and optional inner holes.
            // We need to parse this structure to create a JTS Polygon.
            JSONArray polygonsData = multiPolygonJson.getJSONArray("coordinates");
            
            // This example simplifies by taking the first polygon of a multipolygon
            JSONArray polygonData = polygonsData.getJSONArray(0); 
            JSONArray shellData = polygonData.getJSONArray(0);

            List<Coordinate> coordinates = new ArrayList<>();
            for(int j = 0; j < shellData.length(); j++) {
                JSONArray coordJson = shellData.getJSONArray(j);
                coordinates.add(new Coordinate(coordJson.getDouble(0), coordJson.getDouble(1)));
            }

            LinearRing shell = geometryFactory.createLinearRing(coordinates.toArray(new Coordinate[0]));
            Polygon polygon = geometryFactory.createPolygon(shell);
            
            neighborhoods.add(new Neighborhood(communityName, polygon));
        }
        System.out.println("Neighborhoods data loaded successfully.");
        return neighborhoods;
    }
}

// Simple record classes to hold our data
record Store(String name, double latitude, double longitude) {}
record Neighborhood(String name, Geometry geometry) {}
