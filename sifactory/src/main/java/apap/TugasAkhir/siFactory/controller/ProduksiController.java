package apap.TugasAkhir.siFactory.controller;

import apap.TugasAkhir.siFactory.model.ProduksiModel;
import apap.TugasAkhir.siFactory.service.ProduksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        return "viewall-produksi";
    }
}
