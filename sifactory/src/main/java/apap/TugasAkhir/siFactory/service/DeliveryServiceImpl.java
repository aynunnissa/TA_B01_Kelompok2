package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.DeliveryModel;
import apap.TugasAkhir.siFactory.repository.DeliveryDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryDb deliveryDb;

    @Override
    public List<DeliveryModel> getListDelivery() {
        return deliveryDb.findAllByOrderByIdDeliveryAsc();
    }

}
