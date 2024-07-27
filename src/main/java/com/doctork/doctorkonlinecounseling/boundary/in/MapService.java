package com.doctork.doctorkonlinecounseling.boundary.in;

import com.doctork.doctorkonlinecounseling.domain.Map.MapSearchResponse;
import com.doctork.doctorkonlinecounseling.domain.Map.ReverseGeocodingResponse;

public interface MapService {

    ReverseGeocodingResponse reverseGeocoding(double lat, double lng);

    MapSearchResponse search(String term, double lat, double lng);

}
