/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author apprentice
 */
@Entity
public class Paycheck {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paycheckId")
    private int id;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Entry Date is required.")
    private LocalDate paycheckDate;
     
    @NotNull
    private BigDecimal incomeTotal;
    
    @NotNull
    private String frequency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPaycheckDate() {
        return paycheckDate;
    }

    public void setPaycheckDate(LocalDate paycheckDate) {
        this.paycheckDate = paycheckDate;
    }

    public BigDecimal getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(BigDecimal incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
     
}
