package apap.TugasAkhir.siFactory.controller;

import apap.TugasAkhir.siFactory.model.*;
import apap.TugasAkhir.siFactory.rest.ItemDetail;
import apap.TugasAkhir.siFactory.service.ItemService;
import apap.TugasAkhir.siFactory.service.MesinService;
import apap.TugasAkhir.siFactory.service.PegawaiService;
import apap.TugasAkhir.siFactory.service.ProduksiService;
import org.springframework.web.bind.annotation.ModelAttribute;
import reactor.core.publisher.Mono;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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

    // Fitur 4
    @GetMapping("/item/add")
    public String addItemFormPage(Model model) {
        model.addAttribute("item", new ItemModel());
        model.addAttribute("mesin", mesinService.getListMesin());
        return "form-add-item";
    }

    @PostMapping("item/add")
    public String addItemSubmitPage(
            @ModelAttribute ItemModel item,
            Model model
    ){
        return "request-add-item-berhasil";
    }


    // Fitur 7
    @GetMapping("item/update-stok/{uuid}")
    public String updateStockFormPage(
    @PathVariable String uuid,
    Model model
    ){
        List<MesinModel> listMesin = mesinService.getListMesin();
        List<MesinModel> listMesinFiltered = new ArrayList<MesinModel>();
        String kategori = itemService.getKategoryItem(uuid);
        int idKategori = itemService.getIdKategori(kategori);
        for(MesinModel mesin:listMesin){
            if(mesin.getIdKategori() == idKategori){
                listMesinFiltered.add(mesin);
            }
        }
        model.addAttribute("listMesinfiltered", listMesinFiltered);
        return "form-update-stok-item";
    }

    // Fitur 7
    @PostMapping("item/update-stok/{uuid}")
    public String updateStokSubmitPage(
        @PathVariable String uuid,
        String userNamePegawai,
        Integer jumlahStok,
        long idMesin,
        Model model
    ){
        PegawaiModel pegawai = pegawaiService.getPegawaiByUsername(userNamePegawai);
        try{
            String statusUpdate = itemService.updatestok(uuid, jumlahStok, idMesin, pegawai, (long) -1);
            if(statusUpdate.equals("berhasil")){
                return "update-stok-item-berhasil";
            }
            return "update-stok-item-gagal";
        }catch(Exception e){
            return "update-stok-item-gagal";
        }
    }

    // Fitur 11
    @GetMapping("item/update-stok/rui/{ruiId}")
    public String updateStockFormPageRUP(
    @PathVariable long ruiId,
    Model model
    ){
        RequestUpdateItemModel rui = itemService.getRequestUpdateItem(ruiId);

        List<MesinModel> listMesin = mesinService.getListMesin();
        List<MesinModel> listMesinFiltered = new ArrayList<MesinModel>();
        int idKategori = rui.getIdKategori();
        for(MesinModel mesin:listMesin){
            if(mesin.getIdKategori() == ((long) idKategori)){
                listMesinFiltered.add(mesin);
            }
        }
        model.addAttribute("listMesinfiltered", listMesinFiltered);
        model.addAttribute("stokTambahan", rui.getTambahanStok());
        model.addAttribute("ruiId", ruiId);
        return "form-update-stok-item-rui";
    }

    // Fitur 11
    @PostMapping("item/update-stok/rui/{ruiId}")
    public String updateStokRuiSubmitPage(
        @PathVariable long ruiId,
        String userNamePegawai,
        Integer jumlahStok,
        long idMesin,
        Model model
    ){
        RequestUpdateItemModel rui = itemService.getRequestUpdateItem(ruiId);
        String uuid = rui.getIdItem();
        PegawaiModel pegawai = pegawaiService.getPegawaiByUsername(userNamePegawai);
        try{
            String statusUpdate = itemService.updatestok(uuid,  jumlahStok, idMesin, pegawai, ruiId);
            if(statusUpdate.equals("berhasil")){
                return "update-stok-item-berhasil";
            }
            return "update-stok-item-gagal";
        }catch(Exception e){
            return "update-stok-item-gagal";
        }
    }

    // Fitur 10
    @GetMapping("item/request-update-item")
    public String viewAllRequestUpdateItem(
    Model model
    ){
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
