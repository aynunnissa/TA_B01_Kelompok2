package apap.TugasAkhir.siFactory.repository;

import apap.TugasAkhir.siFactory.model.PegawaiModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel,Long> {
    PegawaiModel findByUsername(String username);
}
