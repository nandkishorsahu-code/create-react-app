package com.skyscanner.hoen;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
    private final String city;

    @JsonCreator
    public Search(@JsonProperty("city") String city) {
        this.city = city;
    }

    @JsonProperty
    public String getCity() {
        return city;
    }
}
