package com.skyscanner.hoen;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {

    private final List<SearchResult> searchResults;

    public SearchResource(List<SearchResult> searchResults) {
        this.searchResults = Objects.requireNonNull(searchResults);
    }

    @POST
    public Response search(final Search search) {
        if (search == null || search.getCity() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Missing 'city' in search body").build();
        }

        final String city = search.getCity().trim();
        List<SearchResult> matches = searchResults.stream()
                .filter(r -> r.getCity() != null &&
                             r.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());

        return Response.ok(matches).build();
    }
}
