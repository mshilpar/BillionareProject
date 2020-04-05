package com.targettech.billionareproject.demotargettech.billionareproject.entities;

import com.targettech.billionareproject.demotargettech.billionareproject.entities.Billionaire;

import java.util.Comparator;

public class BillionaireNetWorthComparator  implements Comparator<Billionaire> {
    @Override
    public int compare(Billionaire o1, Billionaire o2) {
        return o2.getNetWorth().compareTo(o1.getNetWorth());
    }
}
