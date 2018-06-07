/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.controller;

import com.sg.trackit.models.Category;
import com.sg.trackit.models.Entry;
import com.sg.trackit.models.Paycheck;
import com.sg.trackit.models.RangeTotal;
import com.sg.trackit.models.SearchCriteria;
import com.sg.trackit.models.dayTotal;
import com.sg.trackit.service.CategoryService;
import com.sg.trackit.service.DayTotalService;
import com.sg.trackit.service.EntryService;
import com.sg.trackit.service.PaycheckService;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author apprentice
 */
@Controller
public class TrackItController {

    List<Entry> entriesJustAdded = new ArrayList<>();
    
    List<Entry> singleEntriesInSystem = new ArrayList<>();
    List<dayTotal> entriesFound = new ArrayList<>();
    List<dayTotal> dayTotals = new ArrayList<>();
    List<RangeTotal> rangeTotals;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EntryService entryService;
    @Autowired
    private DayTotalService dayTotalService;
    @Autowired
    private PaycheckService payService;

    @GetMapping("/findEntry")
    public String findEntries(Model model) {
        model.addAttribute("search", new SearchCriteria());
        model.addAttribute("entriesFound", entriesFound);
        model.addAttribute("singleEntriesInSystem", singleEntriesInSystem);
        model.addAttribute("rangeTotals", rangeTotals);
        return "/findEntry";
    }

    @PostMapping("/findEntry")
    public String findEntries(@Valid SearchCriteria search) throws javax.xml.bind.ValidationException {
        entriesFound = entryService.findDayTotalsByDates(search, dayTotalService.allDayTotals().getPayload());
        singleEntriesInSystem = entryService.findSingleEntriesByDates(search, entryService.allEntries().getPayload());
        rangeTotals = entryService.rangeTotals(entriesFound);
        return "redirect:/findEntry";
    }

    @GetMapping("/addEntry")
    public String index(Model model) {
        entriesFound.clear();
        model.addAttribute("entry", new Entry());
        model.addAttribute("categories", categoryService.activeCategories(categoryService.allCategories().getPayload()));
        model.addAttribute("entriesAdded", entriesJustAdded);
        return "/addEntry";
    }

    @PostMapping("/addEntry")
    public String add(@Valid Entry entry) throws javax.xml.bind.ValidationException{
        entriesFound.clear();
        entriesJustAdded.clear();
        try{
        entriesJustAdded = entryService.makeEntries(entry, dayTotalService.allDayTotals().getPayload(),
        categoryService.allCategories().getPayload());        
        entriesFound = dayTotalService.allDayTotals().getPayload();
        }
        catch(ValidationException e){
            e.getMessage();
            return "/addEntry";
        }
        return "redirect:/addEntry";
    }

    @GetMapping("/index")
    public String quickAdd(Model model) {
        entriesJustAdded.clear();
        model.addAttribute("entry", new Entry());
        model.addAttribute("dayTotal", dayTotalService.allDayTotals().getPayload());
        model.addAttribute("categories", categoryService.quickAddCategory());
        return "/index";
    }

    @PostMapping("/index")
    public String quickAdd(@Valid Entry entry) {
        entry = entryService.defaultCategory(entry, categoryService.allCategories().getPayload());
        entriesJustAdded.add(entry);
        entryService.save(entry);
        entryService.calculateDayTotals(dayTotalService.allDayTotals().getPayload(), entry);
        return "redirect:/index";

    }

    @GetMapping("/addCategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("quickAddList", categoryService.quickAddCategory());
        model.addAttribute("categories", categoryService.activeCategories(categoryService.allCategories().getPayload()));
        return "/addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@Valid Category category) {
        categoryService.save(category);
        return "redirect:/addCategory";
    }

    @GetMapping("/paycheck")
    public String addPay(Model model) {
        model.addAttribute("paycheck", new Paycheck());
        model.addAttribute("paychecks", payService.allPaychecks().getPayload());
        return "/paycheck";
    }

    @PostMapping("/paycheck")
    public String addPay(@Valid Paycheck paycheck) {
        payService.save(paycheck);
        try{
        entriesJustAdded = entryService.makePaycheckEntries(paycheck,
        dayTotalService.allDayTotals().getPayload(),
        categoryService.allCategories().getPayload());        
        }
        catch(javax.xml.bind.ValidationException e){
            e.getMessage();
            return "/paycheck";
        }
        return "redirect:/paycheck";
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(Model model, Integer categoryId) {
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryService.activeCategories(categoryService.allCategories().getPayload()));
        return "/deleteCategory";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(Integer categoryId) {
        categoryService.disableById(categoryService.allCategories().getPayload(), categoryId);
        return "redirect:/deleteCategory";
    }
}
