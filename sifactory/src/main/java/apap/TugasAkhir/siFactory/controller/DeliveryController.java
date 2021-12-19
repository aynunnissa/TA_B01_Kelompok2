package apap.TugasAkhir.siFactory.controller;

import apap.TugasAkhir.siFactory.model.DeliveryModel;
import apap.TugasAkhir.siFactory.model.PegawaiModel;
import apap.TugasAkhir.siFactory.service.DeliveryService;
import apap.TugasAkhir.siFactory.service.PegawaiService;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeliveryController {

    @Qualifier("deliveryServiceImpl")
    @Autowired
    private DeliveryService deliveryService;

    @Qualifier("pegawaiServiceImpl")
    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/delivery/viewAll")
    public String viewAllDelivery(HttpServletRequest request, Model model) {
        PegawaiModel pegawai = pegawaiService.getPegawaiByUsername(request.getRemoteUser());
        String role = pegawai.getRole().getRole();
        List<DeliveryModel> listDelivery = deliveryService.getListDelivery();
        if (role.equals("STAFF_OPERASIONAL")) {
            model.addAttribute("listDelivery", listDelivery);
        } else if (role.equals("STAFF_KURIR")) {
            listDelivery = listDelivery.stream()
                    .filter(delivery -> delivery.getPegawai().getIdPegawai().equals(pegawai.getIdPegawai()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("listDelivery", listDelivery);
        model.addAttribute("role", role);
        return "viewall-delivery";
    }

    @PutMapping("/delivery/send/{idDelivery}")
    private String sendDelivery(
            Model model,
            HttpServletRequest request,
            @PathVariable int idDelivery) {
        JSONArray listIdCabang = deliveryService.getListIdCabang(idDelivery);
        model.addAttribute("idDelivery", idDelivery);

        JSONObject result = deliveryService.checkIdCabang(listIdCabang, idDelivery, request.getRemoteUser());

        if (result.getBoolean("success")) {
            System.out.println(result.getBoolean("success"));
            model.addAttribute("success", true);
            model.addAttribute("alamat", result.getString("alamat"));
            return "send-delivery";
        }
        model.addAttribute("success", false);
        return "send-delivery";
    }

}
