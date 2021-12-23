package apap.TugasAkhir.siFactory.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.TugasAkhir.siFactory.model.MesinModel;
import apap.TugasAkhir.siFactory.repository.MesinDb;

@Service
@Transactional
public class MesinServiceImpl implements MesinService {
    @Autowired
    MesinDb mesinDb;

    @Override
    public List<MesinModel> getListMesin() {
        return mesinDb.findAll();
    }

    @Override
    public List<MesinModel> getListKategoriMesin() {
        return mesinDb.findAll();
    }
//    @Override
//    public List<MesinModel> getListKategoriMesin() {
//        return mesinDb.findAll().stream().map(MesinModel, MesinModel.getIdKategori()).collect(Collectors.toList());
//    }
}
