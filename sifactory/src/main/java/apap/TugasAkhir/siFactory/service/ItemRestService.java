package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.ItemModel;
import apap.TugasAkhir.siFactory.rest.BaseResponse;

import java.util.List;
import java.util.UUID;

public interface ItemRestService {
    BaseResponse getItemStatus(String uuid);
    BaseResponse getItemDetail(String uuid);
    BaseResponse addProposeItem(String uuid);
}
