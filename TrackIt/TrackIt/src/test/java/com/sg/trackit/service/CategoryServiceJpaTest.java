/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.service;

import com.sg.trackit.data.CategoryRepository;
import com.sg.trackit.models.Category;
import java.util.List;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class CategoryServiceJpaTest {
    
    CategoryRepository categoryRepo;
    
    CategoryService categoryService;
    
    public CategoryServiceJpaTest() {
        
        categoryService = new CategoryServiceJpa();
    }

    @Test
    public void testAllCategories() {
         Result<List<Category>> result = categoryService.allCategories();
        
        
        
        assertTrue(result.isSuccess());
        
        
    }
    
    
}
