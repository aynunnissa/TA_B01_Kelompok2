package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.DeliveryModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryService {
    DeliveryModel addDelivery (DeliveryModel delivery);

    List<DeliveryModel> getListDelivery();

    DeliveryModel getDeliveryByIdDelivery(int idDelivery);

    DeliveryModel updateDelivery(DeliveryModel delivery);

    int getIdCabang(int idDelivery);

    JSONObject sendDelivery(JSONArray listIdCabang, int idDelivery, String username);
}
