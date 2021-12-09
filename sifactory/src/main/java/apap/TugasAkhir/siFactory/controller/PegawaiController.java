package apap.TugasAkhir.siFactory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.TugasAkhir.siFactory.model.PegawaiModel;
import apap.TugasAkhir.siFactory.model.RoleModel;
import apap.TugasAkhir.siFactory.service.PegawaiService;
import apap.TugasAkhir.siFactory.service.RoleService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/pegawai")
public class PegawaiController {
    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    public String addPegawaiFormPage(Model model){
        PegawaiModel pegawai = new PegawaiModel();
//        pegawai.setUsername("aran");
        List<RoleModel> listRole = roleService.getListRole();
        model.addAttribute("pegawai", pegawai);
        model.addAttribute("listRole", listRole);
        return "form-add-pegawai";
    }

    @PostMapping(value = "/add", params = {"save"})
    public String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model){
        Boolean avail = pegawaiService.checkUsername(pegawai.getUsername());
        if(avail){
            String nama = SecurityContextHolder.getContext().getAuthentication().getName();
            pegawai.setCounter(0);
            pegawaiService.addCounterPegawai(nama);
            pegawaiService.addPegawai(pegawai);
            model.addAttribute("pegawai", pegawai);
        }
        else{
            model.addAttribute("message", "Username tidak tersedia. Harap gunakan username lain");
            return "form-add-pegawai";
        }
//        model.addAttribute("pegawai", pegawai);
        return "redirect:/";
    }

    @GetMapping("/view-all")
    public String listPegawai(Model model){
        List<PegawaiModel> listPegawai = pegawaiService.getListPegawai();
        model.addAttribute("listPegawai", listPegawai);
                return "viewall-pegawai";
    }
}



