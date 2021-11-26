package apap.TugasAkhir.siFactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.TugasAkhir.siFactory.model.MesinModel;

@Repository
public interface MesinDb extends JpaRepository <MesinModel,Long> {    
}

