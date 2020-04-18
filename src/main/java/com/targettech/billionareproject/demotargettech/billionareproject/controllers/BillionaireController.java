package com.targettech.billionareproject.demotargettech.billionareproject.controllers;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.targettech.billionareproject.demotargettech.billionareproject.entities.Billionaire;
import com.targettech.billionareproject.demotargettech.billionareproject.service.BillionaireServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class BillionaireController {

    private final BillionaireServices billionaireServices;

    @Autowired
    public BillionaireController(BillionaireServices billionaireServices) {
        this.billionaireServices = billionaireServices;
    }

    @GetMapping("/")
    public String showBillionaires(Model model) {
        return loadBillionaire(model);
    }

    @GetMapping("/addBillionaire")
    public String showAddBillionaireForm(Billionaire billionaire) {
        return "add-billionaire";
    }

    @PostMapping("/addBillionaire")
    public String addBillionaire(@Valid Billionaire billionaire, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-billionaire";
        }

       billionaireServices.saveBillionaire(billionaire);

        model.addAttribute("billionaires", billionaireServices.sortedBillionaireList());
        return "index";
    }

    @GetMapping("/updateBillionaire/{id}")
    public String showUpdateBillionaireForm(@PathVariable("id") long id, Model model) {

            Billionaire billionaire = billionaireServices.findBillionaireById(id);
            model.addAttribute("billionaire", billionaire);
            return "update-billionaire";

    }

    @PostMapping("/updateBillionaire/{id}")
    public String updateBillionaire(@PathVariable("id") long id, @Valid Billionaire billionaire, BindingResult result, Model model) {
        if (result.hasErrors()) {
            billionaire.setId(id);
            return "update-billionaire";
        }

        billionaireServices.saveBillionaire(billionaire);
        System.out.println("Bill2");
        return loadBillionaire(model);

    }

    @GetMapping("/deleteBillionaire/{id}")
    public String deleteBillionaire(@PathVariable("id") long id, Model model) {
        Billionaire billionaire = billionaireServices.findBillionaireById(id);
        billionaireServices.deleteBillionaire(billionaire);
        return loadBillionaire(model);
    }

    public String loadBillionaire(Model model){
        model.addAttribute("billionaire", billionaireServices.sortedBillionaireList());
        return "index";
    }

}
