package apap.TugasAkhir.siFactory.controller;

import apap.TugasAkhir.siFactory.model.DeliveryModel;
import apap.TugasAkhir.siFactory.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DeliveryController {
    
    @Qualifier("deliveryServiceImpl")
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("delivery/viewAll")
    public String viewAllDelivery(Model model) {
        List<DeliveryModel> listDelivery = deliveryService.getListDelivery();

        model.addAttribute("listDelivery", listDelivery);

        return "viewall-delivery";
    }

}
