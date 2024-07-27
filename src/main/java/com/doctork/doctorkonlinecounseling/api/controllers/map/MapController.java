package com.doctork.doctorkonlinecounseling.api.controllers.map;



import com.doctork.doctorkonlinecounseling.api.adapters.Map.MapAdapter;
import com.doctork.doctorkonlinecounseling.domain.Map.MapSearchResponse;
import com.doctork.doctorkonlinecounseling.domain.Map.ReverseGeocodingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@RequestMapping("/api/map/")
public class MapController {

    private final MapAdapter mapAdapter;

    public MapController(MapAdapter mapAdapter) {
        this.mapAdapter = mapAdapter;
    }
    @Operation(summary = "Reverse Geocode a Location")
    @GetMapping("/reverse-geocoding")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReverseGeocodingResponse.class)))
    public DeferredResult<ResponseEntity<?>> reverseGeocoding(@RequestParam double lat, @RequestParam double lng) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        ReverseGeocodingResponse response = mapAdapter.reverseGeocoding(lat, lng);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(response));
        return result;
    }

    @Operation(summary = "Search Locations by Term")
    @GetMapping("/search")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = MapSearchResponse.class)))
    public DeferredResult<ResponseEntity<?>> search(@RequestParam String term, @RequestParam double lat, @RequestParam double lng) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        MapSearchResponse response = mapAdapter.search(term, lat, lng);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(response));
        return result;
    }
}
