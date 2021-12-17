package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.ProduksiModel;
import apap.TugasAkhir.siFactory.repository.ProduksiDb;
import apap.TugasAkhir.siFactory.rest.Setting;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProduksiServiceImpl implements ProduksiService {

    public ProduksiServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.siItemUrl).build();
    }

    private final WebClient webClient;

    @Autowired
    ProduksiDb produksiDb;

    @Override
    public List<ProduksiModel> getListProduksi() {
        return produksiDb.findAllByOrderByIdProduksiAsc();
    }

    @Override
    public Mono<String> getListItem() {
        Mono<String> stringMono = webClient.get().uri("/api/item")
                .retrieve().bodyToMono(String.class);
        return stringMono;
    }
}
