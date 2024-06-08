package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SuggestedSentencesOutputDto {
    private Map<String, List<Suggestion>> suggest;

    public Map<String, List<Suggestion>> getSuggest() {
        return suggest;
    }

    public void setSuggest(Map<String, List<Suggestion>> suggest) {
        this.suggest = suggest;
    }

    public static class Suggestion {
        private int length;
        private int offset;
        private String text;
        private List<Option> options;

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Option> getOptions() {
            return options;
        }

        public void setOptions(List<Option> options) {
            this.options = options;
        }
    }

    public static class Option {
        private String text;
        private double score;
        private int freq;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getFreq() {
            return freq;
        }

        public void setFreq(int freq) {
            this.freq = freq;
        }
    }
}
