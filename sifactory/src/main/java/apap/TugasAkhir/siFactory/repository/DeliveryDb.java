package apap.TugasAkhir.siFactory.repository;
import apap.TugasAkhir.siFactory.model.DeliveryModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 



import java.util.List;

@Repository
public interface DeliveryDb extends JpaRepository<DeliveryModel, Long> {
    List<DeliveryModel> findAllByOrderByIdDeliveryAsc();


}
