package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.ItemModel;

import java.util.List;

public interface ItemService {
    ItemModel findByUuid(String uuid);
    List<ItemModel> getListItem();
}
