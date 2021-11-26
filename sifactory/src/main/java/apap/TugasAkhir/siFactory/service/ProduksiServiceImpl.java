package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.ProduksiModel;
import apap.TugasAkhir.siFactory.repository.ProduksiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProduksiServiceImpl implements ProduksiService {

    @Autowired
    ProduksiDb produksiDb;

    @Override
    public List<ProduksiModel> getListProduksi() {
        return produksiDb.findAllByOrderByIdProduksiAsc();
    }
}
