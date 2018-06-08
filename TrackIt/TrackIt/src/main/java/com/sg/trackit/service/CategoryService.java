/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.service;

import com.sg.trackit.models.Category;
import java.util.List;
import javafx.stage.Stage;

/**
 *
 * @author apprentice
 */
public interface CategoryService {
    
     Result<List<Category>> allCategories();
     List<Category> quickAddCategory();
     
     public void save(Category category);
 
     public List<Category> activeCategories(List<Category> categories);
     
     public void disableById(List<Category> categories, int categoryId);
         
     
}
