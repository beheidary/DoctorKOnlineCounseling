package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SuggestedSentencesOutputDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)

public class AutoCompleteOutputDto {


    private Map<String, List<SuggestedSentencesOutputDto.Suggestion>> suggest;

    public Map<String, List<SuggestedSentencesOutputDto.Suggestion>> getSuggest() {
        return suggest;
    }

    public void setSuggest(Map<String, List<SuggestedSentencesOutputDto.Suggestion>> suggest) {
        this.suggest = suggest;
    }


    public static class Suggestion {
        private int length;
        private int offset;
        private String text;
        private List<SuggestedSentencesOutputDto.Option> options;

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

        public List<SuggestedSentencesOutputDto.Option> getOptions() {
            return options;
        }

        public void setOptions(List<SuggestedSentencesOutputDto.Option> options) {
            this.options = options;
        }
    }
    public static class Option {

        private String text;
        private String _index;
        private String _id;
        private Long _score;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String get_index() {
            return _index;
        }

        public void set_index(String _index) {
            this._index = _index;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public Long get_score() {
            return _score;
        }

        public void set_score(Long _score) {
            this._score = _score;
        }
    }
}
