package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.DeliveryModel;
import apap.TugasAkhir.siFactory.repository.DeliveryDb;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryDb deliveryDb;

    @Qualifier("pegawaiServiceImpl")
    @Autowired
    private PegawaiService pegawaiService;

    @Override
    public List<DeliveryModel> getListDelivery() {
        return deliveryDb.findAllByOrderByIdDeliveryAsc();
    }

    @Override
    public JSONObject sendDelivery(JSONArray listIdCabang, int idDelivery, String username) {
        JSONObject result = new JSONObject();
        int idCabang = getIdCabang(idDelivery);
        for (int i = 0; i < listIdCabang.length(); i++) {
            if (listIdCabang.getJSONObject(i).get("id").equals(idCabang)) {
                DeliveryModel delivery = getDeliveryByIdDelivery(idDelivery);
                LocalDate tanggalKirim = LocalDate.now();
                delivery.setSent(true);
                delivery.setTanggalDikirim(tanggalKirim);
                updateDelivery(delivery);
                pegawaiService.addCounterPegawai(username);
                result.put("success", true);
                result.put("alamat", listIdCabang.getJSONObject(i).get("alamat"));
                return result;
            }
        }
        result.put("success", false);
        return result;
    }

    @Override
    public int getIdCabang(int idDelivery) {
        Optional<DeliveryModel> delivery = deliveryDb.findDeliveryModelByIdDelivery(idDelivery);
        int idCabang = -1;
        if (delivery.isPresent()) {
            idCabang = delivery.get().getIdCabang();
        }
        return idCabang;
    }

    @Override
    public DeliveryModel getDeliveryByIdDelivery(int idDelivery) {
        Optional<DeliveryModel> delivery = deliveryDb.findDeliveryModelByIdDelivery(idDelivery);
        if (delivery.isPresent())
            return delivery.get();
        else
            return null;
    }

    @Override
    public DeliveryModel updateDelivery(DeliveryModel delivery) {
        deliveryDb.save(delivery);
        return delivery;
    }
}
