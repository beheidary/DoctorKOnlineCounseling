package com.doctork.doctorkonlinecounseling.api.adapters.Map;

import com.doctork.doctorkonlinecounseling.domain.Map.MapSearchResponse;
import com.doctork.doctorkonlinecounseling.domain.Map.ReverseGeocodingResponse;


public interface MapAdapter {

    ReverseGeocodingResponse reverseGeocoding(double lat, double lng);

    MapSearchResponse search(String term, double lat, double lng);



}
