package com.doctork.doctorkonlinecounseling.domain.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReverseGeocodingResponse {


    private String status;
    private String neighbourhood;
    @JsonProperty("municipality_zone")
    private String municipalityZone;
    private String state;
    private String city;
    @JsonProperty("in_traffic_zone")
    private boolean inTrafficZone;
    @JsonProperty("in_odd_even_zone")
    private boolean inOddEvenZone;
    @JsonProperty("route_name")
    private String routeName;
    @JsonProperty("route_type")
    private String routeType;
    private String place;
    private String district;
    @JsonProperty("formatted_address")
    private String formattedAddress;
    private String village;




    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getMunicipalityZone() {
        return municipalityZone;
    }

    public void setMunicipalityZone(String municipalityZone) {
        this.municipalityZone = municipalityZone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isInTrafficZone() {
        return inTrafficZone;
    }

    public void setInTrafficZone(boolean inTrafficZone) {
        this.inTrafficZone = inTrafficZone;
    }

    public boolean isInOddEvenZone() {
        return inOddEvenZone;
    }

    public void setInOddEvenZone(boolean inOddEvenZone) {
        this.inOddEvenZone = inOddEvenZone;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }


}
