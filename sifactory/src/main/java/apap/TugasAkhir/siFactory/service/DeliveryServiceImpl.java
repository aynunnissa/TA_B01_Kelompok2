package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.DeliveryModel;
import apap.TugasAkhir.siFactory.repository.DeliveryDb;
import apap.TugasAkhir.siFactory.rest.Setting;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<DeliveryModel> getListDelivery() {
        return deliveryDb.findAllByOrderByIdDeliveryAsc();
    }

    @Override
    public Mono<String> getListIdCabang() {
        Mono<String> stringMono = webClient.get().uri("/api/cabang/listAlamat")
                .retrieve().bodyToMono(String.class);
        return stringMono;
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
