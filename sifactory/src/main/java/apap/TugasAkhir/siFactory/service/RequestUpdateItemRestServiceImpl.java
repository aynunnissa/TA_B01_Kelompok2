package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.RequestUpdateItemModel;
import apap.TugasAkhir.siFactory.repository.RequestUpdateItemDb;
import apap.TugasAkhir.siFactory.rest.RequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RequestUpdateItemRestServiceImpl implements RequestUpdateItemRestService {

    @Autowired
    private RequestUpdateItemDb requestUpdateItemDb;

    @Override
    public RequestUpdateItemModel createRequestUpdateItem(RequestDTO requestUpdateItemModel) {
        RequestUpdateItemModel newRequest = requestUpdateItemModel.convertToRequestUpdateItemModel();
        newRequest.setExecuted(false);
        newRequest.setDeliveryModel(null);
        return requestUpdateItemDb.save(newRequest);
    }
}
