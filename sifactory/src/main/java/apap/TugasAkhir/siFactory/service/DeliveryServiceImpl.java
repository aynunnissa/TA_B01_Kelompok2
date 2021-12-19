package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.DeliveryModel;
import apap.TugasAkhir.siFactory.repository.DeliveryDb;
import apap.TugasAkhir.siFactory.rest.Setting;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    public DeliveryServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.siRetailListIdCabang).build();
    }

    private final WebClient webClient;

    @Autowired
    DeliveryDb deliveryDb;

    @Qualifier("pegawaiServiceImpl")
    @Autowired
    private PegawaiService pegawaiService;

    @Override
    public List<DeliveryModel> getListDelivery() {
        return deliveryDb.findAllByOrderByIdDeliveryAsc();
    }

    @Override
    public JSONArray getListIdCabang(int idDelivery) {
        String response = webClient.get().uri("/api/cabang/listAlamat")
                .retrieve().bodyToMono(String.class).share().block();

        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getJSONArray("result");
    }

    @Override
    public JSONObject checkIdCabang(JSONArray listIdCabang, int idDelivery, String username) {
        JSONObject result = new JSONObject();
        int idCabang = getIdCabang(idDelivery);
        for (int i = 0; i < listIdCabang.length(); i++) {
            if (listIdCabang.getJSONObject(i).get("id").equals(idCabang)) {
                DeliveryModel delivery = getDeliveryByIdDelivery(idDelivery);
                delivery.setSent(true);
                updateDelivery(delivery);
                pegawaiService.addCounterPegawai(username);
                result.put("success", true);
                result.put("alamat", listIdCabang.getJSONObject(i).get("alamat"));
                return result;
            }
        }
        result.put("success", false);
        return result;
    }

    @Override
    public int getIdCabang(int idDelivery) {
        Optional<DeliveryModel> delivery = deliveryDb.findDeliveryModelByIdDelivery(idDelivery);
        int idCabang = -1;
        if (delivery.isPresent()) {
            idCabang = delivery.get().getIdCabang();
        }
        return idCabang;
    }

    @Override
    public DeliveryModel getDeliveryByIdDelivery(int idDelivery) {
        Optional<DeliveryModel> delivery = deliveryDb.findDeliveryModelByIdDelivery(idDelivery);
        if (delivery.isPresent())
            return delivery.get();
        else
            return null;
    }

    @Override
    public DeliveryModel updateDelivery(DeliveryModel delivery) {
        deliveryDb.save(delivery);
        return delivery;
    }
}
