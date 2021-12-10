package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.RequestUpdateItemModel;
import apap.TugasAkhir.siFactory.rest.RequestDTO;

public interface RequestUpdateItemRestService {

    RequestUpdateItemModel createRequestUpdateItem(RequestDTO requestUpdateItem);
}
