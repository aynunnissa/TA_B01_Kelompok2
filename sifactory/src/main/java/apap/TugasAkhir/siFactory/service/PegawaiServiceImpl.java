package apap.TugasAkhir.siFactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import apap.TugasAkhir.siFactory.model.PegawaiModel;
import apap.TugasAkhir.siFactory.repository.PegawaiDb;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
    @Autowired
    private PegawaiDb pegawaiDb;

    @Override
    public PegawaiModel addPegawai(PegawaiModel pegawai) {
        String pass = encrypt(pegawai.getPassword());
        pegawai.setPassword(pass);
        return pegawaiDb.save(pegawai);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordHash = passwordEncoder.encode(password);
        return passwordHash;
    }

    @Override
    public PegawaiModel getPegawaiByUsername(String username) {
        PegawaiModel pegawai = pegawaiDb.findByUsername(username);
        return pegawai;
    }

    @Override
    public List<PegawaiModel> getListPegawai() {
        return pegawaiDb.findAll();
    }

    @Override
    public boolean isMatch(String passwordBaru, String passwordLama) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(passwordBaru, passwordLama);
    }

    @Override
    public void setPassword(PegawaiModel pegawai, String passwordBaru) {
        pegawai.setPassword(passwordBaru);
    }

    @Override
    public void addCounterPegawai(String nama) {
        PegawaiModel pegawaiInput = pegawaiDb.findByUsername(nama);
        Integer prevCounter = pegawaiInput.getCounter();
        Integer currCounter = prevCounter + 1;
        pegawaiInput.setCounter(currCounter);
    }
}