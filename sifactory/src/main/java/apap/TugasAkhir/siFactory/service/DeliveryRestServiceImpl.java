package apap.TugasAkhir.siFactory.service;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.TugasAkhir.siFactory.rest.Setting;

@Service
@Transactional
public class DeliveryRestServiceImpl implements DeliveryRestService {

    public DeliveryRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.siRetailListIdCabang).build();
    }

    private final WebClient webClient;

    @Override
    public JSONArray getListIdCabang(int idDelivery) {
        String response = webClient.get().uri("/api/cabang/listAlamat")
                .retrieve().bodyToMono(String.class).share().block();

        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getJSONArray("result");
    }
}
