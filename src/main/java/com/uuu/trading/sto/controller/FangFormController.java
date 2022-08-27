package com.uuu.trading.sto.controller;

import com.uuu.trading.sto.model.Fang;
import com.uuu.trading.sto.service.FangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FangFormController {

    @Autowired
    private FangService fangService;

    // 查詢表單init
    @GetMapping("/fang_init")
    public String showForm(Model model) {
        Fang fang = new Fang();
        model.addAttribute("fang", fang);
        //List<String> listSymbol = Arrays.asList("TSLA", "AAPL", "BRK.B");
        //model.addAttribute("listSymbol", listSymbol);
        return "fang_form";
    }

    // 查詢表單
    @PostMapping("/fang_query")
    public String submitForm(@ModelAttribute("fang") Fang fang) {
        fangService.savaOrUpdateFang(fang);
        fangService.calCost();
        fangService.findFangById(fang);
        System.out.println(fang);
        return "fang_success";
    }
}
