/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.service;

import com.sg.trackit.models.dayTotal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DayTotalService {
    
    Result<List<dayTotal>> allDayTotals();
    
    public void save(dayTotal total);
    
}
