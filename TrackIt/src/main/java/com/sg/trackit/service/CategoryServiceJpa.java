/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.service;

import com.sg.trackit.data.CategoryRepository;
import com.sg.trackit.models.Category;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author apprentice
 */
@Service
public class CategoryServiceJpa implements CategoryService{
    
    @Autowired
    private CategoryRepository categoryRepo;
    
    @Override
    public Result<List<Category>> allCategories(){
        Result<List<Category>> result = new Result<>();
        result.setPayload(categoryRepo.findAll());
        return result;
    }
    
    @Override
    public void save(Category category) {
         category = categoryRepo.save(category);         
    }
    
    @Override
    public List<Category> quickAddCategory(){
        List<Category> quickAddCategories = new ArrayList<>();
        List<Category> allCategories = categoryRepo.findAll();
        
        for(Category c : allCategories){
            if(c.getIsDefault() == 1){
                quickAddCategories.add(c);
            }
        }        
        return quickAddCategories;
    }
    @Override
    public Result deleteById(int categoryId){
        categoryRepo.deleteById(categoryId);
        return new Result<>();        
    }
}
