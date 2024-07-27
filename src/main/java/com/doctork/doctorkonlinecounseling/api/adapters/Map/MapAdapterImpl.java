package com.doctork.doctorkonlinecounseling.api.adapters.Map;

import com.doctork.doctorkonlinecounseling.boundary.in.MapService;
import com.doctork.doctorkonlinecounseling.domain.Map.MapSearchResponse;
import com.doctork.doctorkonlinecounseling.domain.Map.ReverseGeocodingResponse;
import org.springframework.stereotype.Component;


@Component
public class MapAdapterImpl implements MapAdapter {

    private final MapService mapService;


    public MapAdapterImpl(MapService mapService) {
        this.mapService = mapService;
    }

    @Override
    public ReverseGeocodingResponse reverseGeocoding(double lat, double lng) {
        return mapService.reverseGeocoding(lat , lng);
    }

    @Override
    public MapSearchResponse search(String term, double lat, double lng) {
        return mapService.search(term , lat , lng);
    }
}
