package apap.TugasAkhir.siFactory.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultRequestItemDetail {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "status")
    private String status;
    
    @JsonProperty(value = "stock")
    private String stock;

    @JsonProperty(value = "price")
    private String price;

    @JsonProperty(value = "category")
    private String category;

    @JsonProperty(value = "cluster")
    private String cluster;
}
