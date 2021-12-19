package apap.TugasAkhir.siFactory.controller;

import apap.TugasAkhir.siFactory.model.ProduksiModel;
import apap.TugasAkhir.siFactory.service.ProduksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProduksiController {

    @Qualifier("produksiServiceImpl")
    @Autowired
    private ProduksiService produksiService;

    @GetMapping("produksi/viewAll")
    public String viewAllProduksi(Model model) {
        List<ProduksiModel> listProduksi = produksiService.getListProduksi();

        model.addAttribute("listProduksi", listProduksi);

        String items = produksiService.getListItem().share().block();
        JSONObject jsonObject = new JSONObject(items);
        JSONArray listItem = jsonObject.getJSONArray("result");
        ArrayList<String> listUUIDItem = new ArrayList<String>();
        for (int i = 0; i < listItem.length(); i++) {
            listUUIDItem.add(listItem.getJSONObject(i).get("uuid").toString());
        }

        model.addAttribute("listUUIDItem", listUUIDItem);

        return "viewall-produksi";
    }
}
