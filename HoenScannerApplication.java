package com.skyscanner.hoen;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.jersey.jackson.JacksonFeature; // optional

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "HoenScanner";
    }

    @Override
    public void run(HoenScannerConfiguration configuration, Environment environment) throws Exception {
        final ObjectMapper mapper = environment.getObjectMapper();

        // Load hotels.json
        final TypeReference<List<SearchResult>> typeRef = new TypeReference<>() {};
        final List<SearchResult> searchResults = new ArrayList<>();

        try (InputStream hotelsStream = getClass().getResourceAsStream("/hotels.json")) {
            if (hotelsStream != null) {
                List<SearchResult> hotels = mapper.readValue(hotelsStream, typeRef);
                searchResults.addAll(hotels);
            } else {
                System.err.println("Could not find /hotels.json on classpath");
            }
        }

        // Load rental_cars.json
        try (InputStream carsStream = getClass().getResourceAsStream("/rental_cars.json")) {
            if (carsStream != null) {
                List<SearchResult> cars = mapper.readValue(carsStream, typeRef);
                searchResults.addAll(cars);
            } else {
                System.err.println("Could not find /rental_cars.json on classpath");
            }
        }

        // Register resource
        final SearchResource searchResource = new SearchResource(searchResults);
        environment.jersey().register(searchResource);

        // Optional: register JacksonFeature if needed
        environment.jersey().register(new JacksonFeature());
    }
}
