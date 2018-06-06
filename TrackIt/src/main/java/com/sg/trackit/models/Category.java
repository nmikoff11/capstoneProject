/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Null;
import org.springframework.lang.Nullable;

/**
 *
 * @author apprentice
 */
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    public int id;

    //@Size(min = 1, message = "Please enter valid category")
    //@Size(max = 150, message ="please enter shorter name for category")
    private String categoryName;
    
    @Nullable
    private int isDefault;
    
    @Nullable
    private BigDecimal standardValue;
    
    private String frequency;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    
    
    private int isAlwaysExpense = 0;
    

    public int getIsAlwaysExpense() {
        return isAlwaysExpense;
    }

    public void setIsAlwaysExpense(int isAlwaysExpense) {
        this.isAlwaysExpense = isAlwaysExpense;
    }

    public BigDecimal getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(BigDecimal standardValue) {
        this.standardValue = standardValue;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
