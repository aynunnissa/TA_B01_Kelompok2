package apap.TugasAkhir.siFactory.controller;

import apap.TugasAkhir.siFactory.model.DeliveryModel;
import apap.TugasAkhir.siFactory.service.DeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.coyote.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.standard.serializer.StandardJavaScriptSerializer;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeliveryController {
    
    @Qualifier("deliveryServiceImpl")
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/delivery/viewAll")
    public String viewAllDelivery(Model model) {
        List<DeliveryModel> listDelivery = deliveryService.getListDelivery();

        model.addAttribute("listDelivery", listDelivery);

        return "viewall-delivery";
    }

    @GetMapping("/delivery/send/{idDelivery}")
    private String getTest(
            Model model,
            @PathVariable int idDelivery
    ) {
        String response = deliveryService.getListIdCabang().share().block();
        int idCabang = deliveryService.getIdCabang(idDelivery);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray listIdCabang = jsonObject.getJSONArray("listCabang");

        model.addAttribute("idDelivery", idDelivery);

        for (int i = 0; i < listIdCabang.length(); i++) {
            if (listIdCabang.getJSONObject(i).get("id_cabang").equals(idCabang)) {
                model.addAttribute("success", true);
                model.addAttribute("alamat", listIdCabang.getJSONObject(i).get("alamat"));
                DeliveryModel delivery = deliveryService.getDeliveryByIdDelivery(idDelivery);
                delivery.setSent(true);
                deliveryService.updateDelivery(delivery);
                return "send-delivery";
            }
        }
        model.addAttribute("success", false);
        return "send-delivery";
    }

}
