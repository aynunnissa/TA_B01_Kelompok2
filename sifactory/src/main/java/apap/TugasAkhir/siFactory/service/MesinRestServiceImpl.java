package apap.TugasAkhir.siFactory.service;

import apap.TugasAkhir.siFactory.model.MesinModel;
import apap.TugasAkhir.siFactory.repository.MesinDb;
import org.springframework.stereotype.Service;

import apap.TugasAkhir.siFactory.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MesinRestServiceImpl implements MesinRestService {
    @Autowired
    private MesinDb mesinDb;

    @Override
    public List<MesinModel> retrieveMesin() {
        return mesinDb.findAll();
    }
}
