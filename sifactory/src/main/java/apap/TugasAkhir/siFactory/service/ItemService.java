package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.ItemModel;
import apap.TugasAkhir.siFactory.model.MesinModel;
import apap.TugasAkhir.siFactory.model.PegawaiModel;
import apap.TugasAkhir.siFactory.model.RequestUpdateItemModel;
import apap.TugasAkhir.siFactory.rest.ItemDetail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ItemService {

    Boolean addItem(String nama, int harga, int stok, int kategori, PegawaiModel pegawai);

    List<ItemModel> getListKategori();

    String getKategoryItem (String uuid);
    Integer getIdKategori(String kategori);
    String updatestok(String uuid, Integer jumlahstok, long idMesin, PegawaiModel pegawai, long ruiId);
    RequestUpdateItemModel getRequestUpdateItem (long rui);
    List<RequestUpdateItemModel> getListRequestUpdateItem ();
}
