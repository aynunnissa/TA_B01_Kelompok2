package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.MesinModel;
import apap.TugasAkhir.siFactory.model.PegawaiModel;
import apap.TugasAkhir.siFactory.model.RequestUpdateItemModel;
import apap.TugasAkhir.siFactory.rest.ItemDetail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ItemService {
    String getKategoryItem (String uuid);
    Integer getIdKategori(String kategori);
    String updatestok(String uuid, Integer jumlahstok, long idMesin, PegawaiModel pegawai, Long ruiId);
    RequestUpdateItemModel getRequestUpdateItem (long rui);
    List<RequestUpdateItemModel> getListRequestUpdateItem ();
}
