package com.coom.ath.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@SerdeImport(ParameterStoreDto.class)
public class ParameterStoreDto {
    @JsonProperty
    protected String urlDynamo;
    @JsonProperty
    protected String region;
    @JsonProperty
    protected String arnSecret;
    @JsonProperty
    protected String tabla;
    @JsonProperty
    protected String urlConnection;

}
