package com.targettech.billionareproject.demotargettech.billionareproject.service;
import com.targettech.billionareproject.demotargettech.billionareproject.entities.Billionaire;
import com.targettech.billionareproject.demotargettech.billionareproject.entities.BillionaireNetWorthComparator;
import com.targettech.billionareproject.demotargettech.billionareproject.repositories.BillionaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillionaireServices {

    private final BillionaireRepository billionaireRepository;

    @Autowired
    public BillionaireServices(BillionaireRepository billionaireRepository){
        this.billionaireRepository = billionaireRepository;
    }

    public List<Billionaire> sortedBillionaireList(){
        List<Billionaire> billionairesList = new ArrayList<>();
        Iterable<Billionaire> billionaires = billionaireRepository.findAll();
        for(Billionaire b: billionaires) {
            billionairesList.add(b);
        }
        billionairesList.sort(new BillionaireNetWorthComparator());

        return billionairesList;
    }

    public Billionaire saveBillionaire(Billionaire billionaire){
        return billionaireRepository.save(billionaire);
    }

    public Billionaire findBillionaireById(long id){
        return billionaireRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid billionaire Id:" + id));
    }

    public void deleteBillionaire(Billionaire billionaire){
         billionaireRepository.delete(billionaire);;
    }

}
