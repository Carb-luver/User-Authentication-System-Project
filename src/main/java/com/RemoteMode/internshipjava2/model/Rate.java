package com.RemoteMode.internshipjava2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    @JsonProperty
    @Getter
    String name;

    @Getter
    @JsonProperty
    String unit;

    @Getter
    @JsonProperty
    Long value;

    @Getter
    @JsonProperty
    String type;

}


