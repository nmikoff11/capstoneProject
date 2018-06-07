/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.service;

import com.sg.trackit.data.EntryRepository;
import com.sg.trackit.models.Category;
import com.sg.trackit.models.Entry;
import com.sg.trackit.models.EntryType;
import com.sg.trackit.models.Paycheck;
import com.sg.trackit.models.RangeTotal;
import com.sg.trackit.models.SearchCriteria;
import com.sg.trackit.models.dayTotal;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author apprentice
 */
@Service
public class EntryServiceJpa implements EntryService {

    @Autowired
    private EntryRepository entryRepo;
    
    @Autowired
    private DayTotalService dayTotalService;

    @Override
    public void save(Entry entry) {
        entry = entryRepo.save(entry);
    }

    @Override
    public Result<List<Entry>> allEntries() {
        Result<List<Entry>> result = new Result<>();
        result.setPayload(entryRepo.findAll());
        return result;
    }
    @Override
    public Entry defaultCategory(Entry entry, List<Category> categories){
        
        for(Category c : categories){
            if(c.getId() == entry.getCategory().getId() && c.getIsDefault() == 1){
                entry.setAmount(c.getStandardValue());
                entry.setFrequency("single");
                break;
            }
        }
        
        return entry;
    }
    @Override
    public List<dayTotal> findDayTotalsByDates(SearchCriteria search, List<dayTotal> days) {        
        List<dayTotal> foundEntries = new ArrayList<>();
        for (dayTotal d : days) {
            
            if (d.getEntryDate().isAfter(search.getStartDate()) && d.getEntryDate().isBefore(search.getEndDate())
                 ) {
                foundEntries.add(d);
            }
            if (d.getEntryDate().isEqual(search.getStartDate()) && d.getEntryDate().isBefore(search.getEndDate())
                    ){
                foundEntries.add(d);
            }
            if (d.getEntryDate().isAfter(search.getStartDate()) && d.getEntryDate().isEqual(search.getEndDate())
                    ){
                foundEntries.add(d);
            }
            if (d.getEntryDate().isEqual(search.getStartDate()) && d.getEntryDate().isEqual(search.getEndDate())
                    ){
                foundEntries.add(d);
            }
        }
        return foundEntries;
    }
   @Override
    public List<Entry> findEntriesByDates(SearchCriteria search, List<Entry> entries) {        
        List<Entry> foundEntries = new ArrayList<>();
        for (Entry d : entries) {
            
            if (d.getEntryDate().isAfter(search.getStartDate()) && d.getEntryDate().isBefore(search.getEndDate())
                 ) {
                foundEntries.add(d);
            }
            if (d.getEntryDate().isEqual(search.getStartDate()) && d.getEntryDate().isBefore(search.getEndDate())
                    ){
                foundEntries.add(d);
            }
            if (d.getEntryDate().isAfter(search.getStartDate()) && d.getEntryDate().isEqual(search.getEndDate())
                    ){
                foundEntries.add(d);
            }
            if (d.getEntryDate().isEqual(search.getStartDate()) && d.getEntryDate().isEqual(search.getEndDate())
                    ){
                foundEntries.add(d);
            }
        }
        return foundEntries;
    }

    @Override
    public void calculateDayTotals(List<dayTotal> entries, Entry e){
        dayTotal day = new dayTotal();                
        boolean isDayAlready = false;
        for (dayTotal d : entries) {            
            day = d;
            int sameDay = day.getEntryDate().compareTo(e.getEntryDate());
            if (sameDay == 0) {
                if (e.getEntryType().getEntryTypeId() == 1) {
                    BigDecimal amount = day.getDayTotal().subtract(e.getAmount());
                    day.setDayTotal(amount);
                    BigDecimal expenseTotal = day.getExpenseTotal().add(e.getAmount());          
                    day.setExpenseTotal(expenseTotal);
                    dayTotalService.save(day);
                    isDayAlready = true;
                }
                if (e.getEntryType().getEntryTypeId() == 2) {
                    BigDecimal amount = day.getDayTotal().add(e.getAmount());
                    day.setDayTotal(amount);
                    BigDecimal incomeTotal = day.getIncomeTotal().add(e.getAmount());
                    day.setIncomeTotal(incomeTotal);
                    dayTotalService.save(day);
                    isDayAlready = true;
                }
           }    
        }
            while(!isDayAlready){
                dayTotal newDay = new dayTotal();
                newDay.setEntryDate(e.getEntryDate());
                
            if(e.getEntryType().getEntryTypeId() == 1){
                newDay.setExpenseTotal(e.getAmount());
                newDay.setDayTotal(e.getAmount().negate());
                isDayAlready = true;
            }
            if(e.getEntryType().getEntryTypeId() == 2){
                newDay.setIncomeTotal(e.getAmount());
                newDay.setDayTotal(e.getAmount());
                isDayAlready = true;
                }
             dayTotalService.save(newDay);
            }       
    }    
    @Override
    public List<RangeTotal> rangeTotals(List<dayTotal> totals) throws ValidationException{
        List<RangeTotal> totalList = new ArrayList<>();
        
        RangeTotal r = new RangeTotal();
        BigDecimal income =  new BigDecimal("0");
        BigDecimal expense =  new BigDecimal("0");
        BigDecimal total =  new BigDecimal("0");
        
        for(dayTotal d : totals){
           total = d.getDayTotal().add(total);
           expense = d.getExpenseTotal().add(expense);
           income = d.getIncomeTotal().add(income);
        }
        r.setExpenseTotal(expense);
        r.setIncomeTotal(income);
        r.setTotalBalance(total);
        totalList.add(r);
        return totalList;
    }
    
    @Override
    public List<Entry> makePaycheckEntries(Paycheck paycheck, List<dayTotal> dayTotals, List<Category> categories) throws ValidationException{
        int entries = 0;
        List<Entry> entriesAdded = new ArrayList<>();
        EntryType e = new EntryType(); 
        Category c = new Category();
        int expenseTypeValue = 2;
        
        for(Category a : categories){
            if(a.getCategoryName().equalsIgnoreCase("paycheck")){
                c.setId(a.getId());
            }
        }        
        e.setEntryTypeId(expenseTypeValue);
        
        LocalDate date = paycheck.getPaycheckDate();
        BigDecimal dailyPay = new BigDecimal(0);
        BigDecimal weekly = new BigDecimal(7);
        BigDecimal biweekly = new BigDecimal(14);
        BigDecimal yearly = new BigDecimal(365);
        
        
        String frequency = paycheck.getFrequency();
        if(frequency.equalsIgnoreCase("weekly")){
            dailyPay = paycheck.getIncomeTotal().divide(weekly, 2,RoundingMode.HALF_UP);
            entries = 7;
        }
        if(frequency.equalsIgnoreCase("biweekly")){
            dailyPay = paycheck.getIncomeTotal().divide(biweekly, 2, RoundingMode.HALF_UP);            
            entries = 14;
        }
        if(frequency.equalsIgnoreCase("yearly")){
            dailyPay = paycheck.getIncomeTotal().divide(yearly, 2, RoundingMode.HALF_UP); 
            entries = 365;
        }
        for(int i = 0; i < entries; i++){             
             Entry entry = new Entry();
             LocalDate d = date.plusDays(i);            
             entry.setEntryDate(d);
             entry.setAmount(dailyPay);
             entry.setEntryType(e);
             entry.setCategory(c);
             save(entry);
             entriesAdded.add(entry);
             calculateDayTotals(dayTotals, entry);
        }    
        return entriesAdded;
    }
    
    public List<Entry> makeEntries(Entry entry, List<dayTotal> dayTotals, List<Category> categories){
        int entries = 0;
        List<Entry> entriesAdded = new ArrayList<>();
        LocalDate date = entry.getEntryDate();
        BigDecimal amount = new BigDecimal(0);
        BigDecimal weekly = new BigDecimal(7);
        BigDecimal monthly = new BigDecimal(12);
        BigDecimal yearly = new BigDecimal(365);
        
        Category c = new Category();
        
        
        for(Category a : categories){
            if(a.getId() == entry.getCategory().getId()){
                c = a;
            }
        } 
        
        int f = entry.getEntryType().getEntryTypeId();
        EntryType w = new EntryType();
        w.setEntryTypeId(f);
        
        String frequency = entry.getFrequency();
        if(frequency.equalsIgnoreCase("single")){
            amount = entry.getAmount();
            entries = 1;
        }
        if(frequency.equalsIgnoreCase("weekly")){
            amount = entry.getAmount().divide(weekly, 2,RoundingMode.HALF_UP);
            entries = 7;
        }
        if(frequency.equalsIgnoreCase("monthly")){
            amount = entry.getAmount().multiply(monthly);
            amount = amount.divide(yearly, 2, RoundingMode.HALF_UP);
            entries = 31;
        }
        if(frequency.equalsIgnoreCase("yearly")){
            amount = entry.getAmount().divide(yearly, 2, RoundingMode.HALF_UP); 
            entries = 365;
        }
        for(int i = 0; i < entries; i++){             
             Entry e = new Entry();
             LocalDate d = date.plusDays(i);
             e.setEntryDate(d);
             e.setAmount(amount);
             e.setFrequency(frequency);
             e.setCategory(c);
             e.setEntryType(w);
             save(e);
             entriesAdded.add(e);
             calculateDayTotals(dayTotals, e);
        }    
        return entriesAdded;
    }    
    @Override
    public List<Entry> findSingleEntriesByDates(SearchCriteria search, List<Entry> entries) {        
        List<Entry> foundEntries = new ArrayList<>();
        List<Entry> singleEntries = new ArrayList<>();
        for (Entry d : entries) {
            
            if (d.getEntryDate().isAfter(search.getStartDate()) && d.getEntryDate().isBefore(search.getEndDate())
                 ) {
                foundEntries.add(d);
            }
            if (d.getEntryDate().isEqual(search.getStartDate()) && d.getEntryDate().isBefore(search.getEndDate())
                    ){
                foundEntries.add(d);
            }
            if (d.getEntryDate().isAfter(search.getStartDate()) && d.getEntryDate().isEqual(search.getEndDate())
                    ){
                foundEntries.add(d);
            }
            if (d.getEntryDate().isEqual(search.getStartDate()) && d.getEntryDate().isEqual(search.getEndDate())
                    ){
                foundEntries.add(d);
            }
        }
        for(Entry e : foundEntries){
            if(e.getFrequency().equalsIgnoreCase("single")){
                singleEntries.add(e);
            }
        }
        return singleEntries;
    }
}
