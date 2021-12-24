package apap.TugasAkhir.siFactory.repository;
import apap.TugasAkhir.siFactory.model.DeliveryModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryDb extends JpaRepository<DeliveryModel, Long> {
    List<DeliveryModel> findAllByOrderByIdDeliveryAsc();
    Optional<DeliveryModel> findDeliveryModelByIdDelivery(int idDelivery);

}
