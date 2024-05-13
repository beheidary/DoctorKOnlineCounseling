package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos;

import java.util.List;

public class SearchResultDto {
    private long totalHits;
    private String totalHitsRelation;
    private Double maxScore;
    private String scrollId;
    private String pointInTimeId;
    private List<SearchHitDto> searchHits;
    // You might need to add aggregations if required

    // Getters and setters
    public long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(long totalHits) {
        this.totalHits = totalHits;
    }

    public String getTotalHitsRelation() {
        return totalHitsRelation;
    }

    public void setTotalHitsRelation(String totalHitsRelation) {
        this.totalHitsRelation = totalHitsRelation;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public String getPointInTimeId() {
        return pointInTimeId;
    }

    public void setPointInTimeId(String pointInTimeId) {
        this.pointInTimeId = pointInTimeId;
    }

    public List<SearchHitDto> getSearchHits() {
        return searchHits;
    }

    public void setSearchHits(List<SearchHitDto> searchHits) {
        this.searchHits = searchHits;
    }
}
