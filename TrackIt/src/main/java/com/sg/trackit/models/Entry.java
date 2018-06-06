/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author apprentice
 */
@Entity
public class Entry implements Serializable{
    
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "entryInfoId")
       private int id;
       
       @DateTimeFormat(pattern = "yyyy-MM-dd")
       @NotNull(message = "Entry Date is required.")
       private LocalDate entryDate;
       
       @NotNull
       private BigDecimal amount;
       
       @ManyToOne
       @JoinColumn(name = "categoryId")
       private Category category;
       
       @ManyToOne
       @JoinColumn(name = "entryTypeId")
       private EntryType entryType;
       
       private String frequency;

    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


           
}
