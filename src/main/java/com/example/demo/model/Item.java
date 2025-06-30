package com.example.demo.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;
    
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	
	
	
//	    @Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	    private Long id;
//
//	    private String name;
//	    private String description;
//	    private BigDecimal price;
//
//	    @ManyToMany
//	    @JoinTable(
//	        name = "item_categories",
//	        joinColumns = @JoinColumn(name = "item_id"),
//	        inverseJoinColumns = @JoinColumn(name = "category_id")
//	    )
//	    private Set<Category> categories = new HashSet<>();
	}
