/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.service;

import com.sg.trackit.data.PaycheckRepository;
import com.sg.trackit.models.Paycheck;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author apprentice
 */
@Service
public class PaycheckServiceJpa implements PaycheckService{

    @Autowired
    PaycheckRepository payRepo;
    
    @Override
    public Result<List<Paycheck>> allPaychecks() {
        Result<List<Paycheck>> result = new Result<>();
        result.setPayload(payRepo.findAll());
        return result;
    }

    @Override
    public void save(Paycheck paycheck) {
        paycheck = payRepo.save(paycheck);
    }
    
}
