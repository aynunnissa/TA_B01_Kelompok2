package apap.TugasAkhir.siFactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.TugasAkhir.siFactory.model.RequestUpdateItemModel;

@Repository
public interface RequestUpdateItemDb extends JpaRepository<RequestUpdateItemModel, Integer> {
}
