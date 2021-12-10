package apap.TugasAkhir.siFactory.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDetail {
    @JsonProperty(value = "uuid")
    private String uuid;

    @JsonProperty(value = "nama")
    private String nama;

    @JsonProperty(value = "harga")
    private String harga;
    
    @JsonProperty(value = "stok")
    private String stok;

    @JsonProperty(value = "kategori")
    private String kategori;

}
