package com.doctork.doctorkonlinecounseling.domain.Map;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapSearchResponse {

    private int count;
    private List<MapSearchResponseItem> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MapSearchResponseItem> getItems() {
        return items;
    }

    public void setItems(List<MapSearchResponseItem> items) {
        this.items = items;
    }
}
