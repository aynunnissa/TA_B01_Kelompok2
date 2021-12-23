package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.ItemModel;
import apap.TugasAkhir.siFactory.repository.ItemDb;
import apap.TugasAkhir.siFactory.rest.BaseResponse;
import apap.TugasAkhir.siFactory.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService{
    private final WebClient webClient;

    public ItemRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.siItemUrl).build();
    }

    @Override
    public BaseResponse getItemStatus(String uuid) {
        return this.webClient.get().uri("/api/item").retrieve().bodyToMono(BaseResponse.class).block();
    }

    @Override
    public BaseResponse getItemDetail(String uuid) {
        return this.webClient.get().uri("/api/item/"+uuid).retrieve().bodyToMono(BaseResponse.class).block();
    }

    @Override
    public BaseResponse addProposeItem(String uuid) {
        return this.webClient.get().uri("/api/item/"+uuid).retrieve().bodyToMono(BaseResponse.class).block();
    }


}
