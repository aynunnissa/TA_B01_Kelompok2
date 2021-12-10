package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.ItemModel;
import apap.TugasAkhir.siFactory.repository.ItemDb;
import apap.TugasAkhir.siFactory.rest.setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private final WebClient webClient;

    public ItemServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(setting.siItemUrl)
//                                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Autowired
    ItemDb itemDb;

    @Override
    public ItemModel findByUuid(String uuid) {
        return itemDb.findByUuid(uuid);
    }

    @Override
    public List<ItemModel> getListItem() {
        return itemDb.findAll();
    }
}
