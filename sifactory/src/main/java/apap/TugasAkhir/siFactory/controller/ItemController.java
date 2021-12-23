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
import apap.TugasAkhir.siFactory.model.MesinModel;
import apap.TugasAkhir.siFactory.model.PegawaiModel;
import apap.TugasAkhir.siFactory.model.ProduksiModel;
import apap.TugasAkhir.siFactory.model.RequestUpdateItemModel;
import apap.TugasAkhir.siFactory.rest.ItemDetail;
import apap.TugasAkhir.siFactory.service.ItemService;
import apap.TugasAkhir.siFactory.service.MesinService;
import apap.TugasAkhir.siFactory.service.PegawaiService;
import apap.TugasAkhir.siFactory.service.ProduksiService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Qualifier("itemServiceImpl")
    @Autowired
    private ItemService itemService;

    @Qualifier("mesinServiceImpl")
    @Autowired
    private MesinService mesinService;

    @Qualifier("pegawaiServiceImpl")
    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    ItemRestService itemRestService;
    
    // Fitur 4
    @GetMapping("/add")
    public String addItemFormPage(Model model) {
        model.addAttribute("item", new ItemModel());
        model.addAttribute("mesin", mesinService.getListMesin());
        return "form-add-item";
    }

    @PostMapping("/add")
    public String addItemSubmitPage(
            @ModelAttribute ItemModel item,
            Model model
    ){
        return "request-add-item-berhasil";
    }
  
    // Fitur 5
    @RequestMapping(value = "/item-detail", method = RequestMethod.GET)
    private String getItemDetail(Authentication auth, Model model) throws WebClientException {
        // PegawaiModel pegawai = pegawaiService.getPegawaiByUsername(auth.getName());
        // Long role = pegawai.getRole().getIdRole();

        BaseResponse baseResponse = itemRestService.getItemStatus(auth.getName());
        Object itemDetail = baseResponse.getResult();
        model.addAttribute("itemDetail", itemDetail);

        return "viewall-item";
    }

    // Fitur 6
    @GetMapping(value = "/item-detail/{uuid}")
    private String detailItem(
            @PathVariable("uuid") String uuid,
            Authentication auth,
            Model model) {
        // ItemModel item = itemService.findByUuid(auth.getName());
        BaseResponse baseResponse = itemRestService.getItemDetail(uuid);
        Object itemDetail = baseResponse.getResult();
        // System.out.println(baseResponse.getResult());
        model.addAttribute("itemDetail", itemDetail);
        model.addAttribute("isUser", true);

        return "detail-item";
    }

    // Fitur 7
    @GetMapping("/update-stok/{uuid}")
    public String updateStockFormPage(
            @PathVariable String uuid,
            Model model) {
        List<MesinModel> listMesin = mesinService.getListMesin();
        List<MesinModel> listMesinFiltered = new ArrayList<MesinModel>();
        String kategori = itemService.getKategoryItem(uuid);
        int idKategori = itemService.getIdKategori(kategori);
        for (MesinModel mesin : listMesin) {
            if (mesin.getIdKategori() == idKategori) {
                listMesinFiltered.add(mesin);
            }
        }
        model.addAttribute("listMesinfiltered", listMesinFiltered);
        return "form-update-stok-item";
    }

    // Fitur 7
    @PostMapping("/update-stok/{uuid}")
    public String updateStokSubmitPage(
            @PathVariable String uuid,
            String userNamePegawai,
            Integer jumlahStok,
            long idMesin,
            Model model) {
        PegawaiModel pegawai = pegawaiService.getPegawaiByUsername(userNamePegawai);
        try {
            String statusUpdate = itemService.updatestok(uuid, jumlahStok, idMesin, pegawai, (long) -1);
            if (statusUpdate.equals("berhasil")) {
                return "update-stok-item-berhasil";
            }
            return "update-stok-item-gagal";
        } catch (Exception e) {
            return "update-stok-item-gagal";
        }
    }

    // Fitur 11
    @GetMapping("/update-stok/rui/{ruiId}")
    public String updateStockFormPageRUP(
            @PathVariable long ruiId,
            Model model) {
        RequestUpdateItemModel rui = itemService.getRequestUpdateItem(ruiId);

        List<MesinModel> listMesin = mesinService.getListMesin();
        List<MesinModel> listMesinFiltered = new ArrayList<MesinModel>();
        long idKategori = rui.getIdKategori();
        for (MesinModel mesin : listMesin) {
            if (mesin.getIdKategori() ==  idKategori) {
                listMesinFiltered.add(mesin);
            }
        }
        model.addAttribute("listMesinfiltered", listMesinFiltered);
        model.addAttribute("stokTambahan", rui.getTambahanStok());
        model.addAttribute("ruiId", ruiId);
        return "form-update-stok-item-rui";
    }

    // Fitur 11
    @PostMapping("/update-stok/rui/{ruiId}")
    public String updateStokRuiSubmitPage(
            @PathVariable long ruiId,
            String userNamePegawai,
            Integer jumlahStok,
            long idMesin,
            Model model) {
        RequestUpdateItemModel rui = itemService.getRequestUpdateItem(ruiId);
        String uuid = rui.getIdItem();
        PegawaiModel pegawai = pegawaiService.getPegawaiByUsername(userNamePegawai);
        try {
            String statusUpdate = itemService.updatestok(uuid, jumlahStok, idMesin, pegawai, ruiId);
            if (statusUpdate.equals("berhasil")) {
                return "update-stok-item-berhasil";
            }
            return "update-stok-item-gagal";
        } catch (Exception e) {
            return "update-stok-item-gagal";
        }
    }

    // Fitur 10
    @GetMapping("/request-update-item")
    public String viewAllRequestUpdateItem(
            Model model) {
        List<RequestUpdateItemModel> listRUI = itemService.getListRequestUpdateItem();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        String role = pegawaiService.getPegawaiByUsername(username).getRole().getRole();
        model.addAttribute("role", role);
        model.addAttribute("listRUI", listRUI);
        return "viewall-request-update-item";
    }
}
