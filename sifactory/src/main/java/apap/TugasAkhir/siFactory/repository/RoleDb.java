package apap.TugasAkhir.siFactory.repository;

import apap.TugasAkhir.siFactory.model.RoleModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface RoleDb extends JpaRepository<RoleModel,Long> {
}
