package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.PegawaiModel;

import java.util.List;

public interface PegawaiService {
    PegawaiModel addPegawai(PegawaiModel pegawai);
    String encrypt(String password);
    PegawaiModel getPegawaiByUsername(String username);
    List<PegawaiModel> getListPegawai();
//    boolean isMatch(String passwordBaru, String passwordLama);
    void setPassword(PegawaiModel pegawai, String passwordBaru);
    void addCounterPegawai(String nama);
    boolean checkUsername(String username);
}
