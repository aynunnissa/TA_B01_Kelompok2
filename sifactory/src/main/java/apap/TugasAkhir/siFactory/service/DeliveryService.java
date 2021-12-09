package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.DeliveryModel;
import com.fasterxml.jackson.databind.util.JSONPObject;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    List<DeliveryModel> getListDelivery();
    DeliveryModel getDeliveryByIdDelivery(int idDelivery);
    DeliveryModel updateDelivery(DeliveryModel delivery);
    Mono<String> getListIdCabang();
    int getIdCabang(int idDelivery);
}
