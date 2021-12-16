package apap.TugasAkhir.siFactory.restcontroller;

import apap.TugasAkhir.siFactory.model.ItemModel;
import apap.TugasAkhir.siFactory.service.ItemRestService;
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
public class ItemRestController {
    @Autowired
    private ItemRestService itemRestService;

//    @GetMapping (value = "/item")
//    private List<ItemModel> retrieveListItem(){
//        return itemRestService.retrieveListItem();
//    }
}
