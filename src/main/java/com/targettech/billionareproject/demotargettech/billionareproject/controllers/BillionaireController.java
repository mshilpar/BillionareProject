package com.targettech.billionareproject.demotargettech.billionareproject.controllers;
import javax.validation.Valid;

import com.targettech.billionareproject.demotargettech.billionareproject.entities.Billionaire;
import com.targettech.billionareproject.demotargettech.billionareproject.entities.BillionaireNetWorthComparator;
import com.targettech.billionareproject.demotargettech.billionareproject.repositories.BillionaireRepository;
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

    private final BillionaireRepository billionaireRepository;

    @Autowired
    public BillionaireController(BillionaireRepository billionaireRepository) {
        this.billionaireRepository = billionaireRepository;
    }

    @GetMapping("/")
    public String showBillionaires(Model model) {
        model.addAttribute("billionaire",billionaireRepository.findAll());
      return "index";
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

       billionaireRepository.save(billionaire);

        List<Billionaire> billionairesList = new ArrayList<>();
        Iterable<Billionaire> billionaires = billionaireRepository.findAll();
        for(Billionaire b: billionaires) {
            billionairesList.add(b);
        }
        billionairesList.sort(new BillionaireNetWorthComparator());
        model.addAttribute("billionaires",billionairesList);

        System.out.println("billionaires111 ");
        return "index";
    }

    @GetMapping("/updateBillionaire/{id}")
    public String showUpdateBillionaireForm(@PathVariable("id") long id, Model model) {
        Billionaire billionaire = billionaireRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid billionaire Id:" + id));
        model.addAttribute("billionaire", billionaire);
        return "update-billionaire";
    }

    @PostMapping("/updateBillionaire/{id}")
    public String updateBillionaire(@PathVariable("id") long id, @Valid Billionaire billionaire, BindingResult result, Model model) {
        if (result.hasErrors()) {
            billionaire.setId(id);
            return "update-billionaire";
        }

        billionaireRepository.save(billionaire);
        System.out.println("Bill2");
        model.addAttribute("billionaire", billionaireRepository.findAll());
        return "index";

    }

    @GetMapping("/deleteBillionaire/{id}")
    public String deleteBillionaire(@PathVariable("id") long id, Model model) {
        Billionaire billionaire = billionaireRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid billionaire Id:" + id));
        billionaireRepository.delete(billionaire);
        model.addAttribute("billionaire", billionaireRepository.findAll());
        return "index";
    }
}
