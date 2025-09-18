package com.skyscanner.hoen;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
    private final String city;
    private final String kind;
    private final String title;

    @JsonCreator
    public SearchResult(
            @JsonProperty("city") String city,
            @JsonProperty("kind") String kind,
            @JsonProperty("title") String title) {
        this.city = city;
        this.kind = kind;
        this.title = title;
    }

    @JsonProperty
    public String getCity() {
        return city;
    }

    @JsonProperty
    public String getKind() {
        return kind;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }
}
