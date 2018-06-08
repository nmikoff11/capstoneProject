/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.service;

import com.sg.trackit.data.DayTotalRepository;
import com.sg.trackit.models.dayTotal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author apprentice
 */
@Service
public class DayTotalServiceJpa implements DayTotalService{
    
    @Autowired
    DayTotalRepository dayTotalRepo;

    @Override
    public Result<List<dayTotal>> allDayTotals() {
        Result<List<dayTotal>> result = new Result<>();
        result.setPayload(dayTotalRepo.findAll());
        return result;
    }

    @Override
    public void save(dayTotal total) {
         total = dayTotalRepo.save(total);         
    }
    
}
