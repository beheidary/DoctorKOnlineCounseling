package com.doctork.doctorkonlinecounseling.UseCase.Map;

import com.doctork.doctorkonlinecounseling.boundary.in.MapService;
import com.doctork.doctorkonlinecounseling.domain.Map.MapSearchResponse;
import com.doctork.doctorkonlinecounseling.domain.Map.ReverseGeocodingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MapServiceImpl implements MapService {


    @Value("${neshan.api.key}")
    private String apiKey;
    @Value("${neshan.api.reverse.url}")
    private String reverseApiUrl;
    @Value("${neshan.api.search.url}")
    private String searchApiUrl;

    private final RestTemplate restTemplate;

    public MapServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ReverseGeocodingResponse reverseGeocoding(double lat, double lng) {

        String url = String.format(reverseApiUrl, lat, lng);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Api-Key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, ReverseGeocodingResponse.class).getBody();
    }

    @Override
    public MapSearchResponse search(String term, double lat, double lng) {
        String url = String.format(searchApiUrl,term, lat, lng);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Api-Key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, MapSearchResponse.class).getBody();
    }
}
