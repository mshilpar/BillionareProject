package com.targettech.billionareproject.demotargettech.billionareproject.repositories;
import com.targettech.billionareproject.demotargettech.billionareproject.entities.Billionaire;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillionaireRepository extends CrudRepository<Billionaire, Long> {

    public List<Billionaire> findAllByOrderByNameAsc();
}
