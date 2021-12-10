package apap.TugasAkhir.siFactory.controller;

import apap.TugasAkhir.siFactory.model.ItemModel;
import apap.TugasAkhir.siFactory.model.PegawaiModel;
import apap.TugasAkhir.siFactory.rest.BaseResponse;
import apap.TugasAkhir.siFactory.service.ItemRestService;
import apap.TugasAkhir.siFactory.service.ItemService;
import apap.TugasAkhir.siFactory.service.PegawaiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
//    @Qualifier("itemServiceImpl")
    @Autowired
    PegawaiService pegawaiService;

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRestService itemRestService;

    @RequestMapping(value = "/item-detail", method = RequestMethod.GET)
    private String getItemDetail(Authentication auth, Model model) throws WebClientException {
        PegawaiModel pegawai = pegawaiService.getPegawaiByUsername(auth.getName());
        Long role = pegawai.getRole().getIdRole();

        BaseResponse baseResponse = itemRestService.getItemStatus(auth.getName());
        Object itemDetail = baseResponse.getResult();
        model.addAttribute("itemDetail", itemDetail);

        return "viewall-item";
    }

    @GetMapping(value ="/item-detail/{uuid}")
    private String detailItem(
            @PathVariable ("uuid") String uuid,
            Authentication auth,
            Model model
    ) {
//        ItemModel item = itemService.findByUuid(auth.getName());
        BaseResponse baseResponse = itemRestService.getItemDetail(uuid);
        Object itemDetail = baseResponse.getResult();
//        System.out.println(baseResponse.getResult());
        model.addAttribute("itemDetail", itemDetail);
        model.addAttribute("isUser", true);

        return "detail-item";
    }

}
