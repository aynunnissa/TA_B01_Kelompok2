package apap.TugasAkhir.siFactory.restcontroller;

import apap.TugasAkhir.siFactory.model.MesinModel;
import apap.TugasAkhir.siFactory.rest.MesinDetail;
import apap.TugasAkhir.siFactory.service.MesinRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class MesinRestController {
    @Autowired
    private MesinRestService mesinRestService;

    @GetMapping(value = "/list-mesin")
    private MesinDetail<List<MesinModel>> retrieveListModel() {
        MesinDetail<List<MesinModel>> response = new MesinDetail<>();
        List<MesinModel> listMesin = mesinRestService.retrieveMesin();
        response.setStatus(200);
        response.setMessage("success");
        response.setResult(listMesin);
        return response;
    }
}
