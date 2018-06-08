/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author apprentice
 */
 
@Entity
public class EntryType implements Serializable{
    
    @Id       
    public int entryTypeId;      
    private boolean isExpense;
    
    
    public int getEntryTypeId() {
        return entryTypeId;
    }

    public void setEntryTypeId(int entryTypeId) {
        this.entryTypeId = entryTypeId;
    }

    public boolean isIsExpense() {
        return isExpense;
    }

    public void setIsExpense(boolean isExpense) {
        this.isExpense = isExpense;
    }
       
       
       
       
       
    
}
    

