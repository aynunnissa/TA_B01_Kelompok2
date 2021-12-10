package apap.TugasAkhir.siFactory.repository;

import apap.TugasAkhir.siFactory.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemDb extends JpaRepository<ItemModel, String> {
    ItemModel findByUuid(String uuid);
}
