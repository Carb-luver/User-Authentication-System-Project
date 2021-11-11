package com.RemoteMode.internship2correction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Rate {

    @JsonProperty
    String name;
    
    @JsonProperty
    String unit;

    @JsonProperty
    Long value;

    @JsonProperty
    String type;

}


