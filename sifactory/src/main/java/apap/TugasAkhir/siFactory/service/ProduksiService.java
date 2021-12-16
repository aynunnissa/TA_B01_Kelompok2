package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.ProduksiModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProduksiService {
    List<ProduksiModel> getListProduksi();

    Mono<String> getListItem();
}
