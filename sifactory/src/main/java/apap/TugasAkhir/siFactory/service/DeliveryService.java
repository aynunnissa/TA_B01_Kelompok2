package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.DeliveryModel;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.json.JSONArray;
import org.json.JSONObject;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    List<DeliveryModel> getListDelivery();

    DeliveryModel getDeliveryByIdDelivery(int idDelivery);

    DeliveryModel updateDelivery(DeliveryModel delivery);

    // Mono<String> getListIdCabang();
    JSONArray getListIdCabang(int idDelivery);

    int getIdCabang(int idDelivery);

    JSONObject checkIdCabang(JSONArray listIdCabang, int idDelivery, String username);
}
