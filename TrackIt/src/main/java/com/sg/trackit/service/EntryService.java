/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.service;

import com.sg.trackit.models.Category;
import com.sg.trackit.models.Entry;
import com.sg.trackit.models.Paycheck;
import com.sg.trackit.models.RangeTotal;
import com.sg.trackit.models.SearchCriteria;
import com.sg.trackit.models.dayTotal;
import java.util.List;
import javax.xml.bind.ValidationException;

/**
 *
 * @author apprentice
 */

public interface EntryService {
    
    Result<List<Entry>> allEntries();
    
    void save(Entry entry);
        
    List<dayTotal> findDayTotalsByDates(SearchCriteria search, List<dayTotal> days);
    
    void calculateDayTotals(List<dayTotal> entries, Entry e);
    
    Entry defaultCategory(Entry entry, List<Category> categories);
    
    List<RangeTotal> rangeTotals(List<dayTotal> totals) throws ValidationException;
    
    public List<Entry> findEntriesByDates(SearchCriteria search, List<Entry> entries);
    
    public List<Entry> makePaycheckEntries(Paycheck paycheck, List<dayTotal> dayTotals, List<Category> categories) throws ValidationException;
    
    public List<Entry> makeEntries(Entry entry, List<dayTotal> dayTotals, List<Category> categories) throws ValidationException;
    
    public List<Entry> findSingleEntriesByDates(SearchCriteria search, List<Entry> entries);
        
}
