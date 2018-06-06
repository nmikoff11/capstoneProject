/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.data;

import com.sg.trackit.models.dayTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author apprentice
 */
@Repository
public interface DayTotalRepository extends JpaRepository<dayTotal, Integer>{
    
}
