package com.example.hopeIDontFlop.config;
import java.util.Arrays;

import com.example.hopeIDontFlop.model.Category;
import com.example.hopeIDontFlop.model.Product;
import com.example.hopeIDontFlop.repository.CategoryRepository;
import com.example.hopeIDontFlop.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
//@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {
    // spring mangage component so we going to have dummy data

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public DataSeeder(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public void run(String... args) throws Exception {
        // clear all existing data
        productRepo.deleteAll();
        categoryRepo.deleteAll();

        // create category
        Category electronic = new Category();
        electronic.setName("Food & Beverage");

        // create clothing
        Category clothes = new Category();
        clothes.setName("Clothing");

        // create Kittchen
        Category home = new Category();
        home.setName("Gifts");

        categoryRepo.saveAll(Arrays.asList(electronic, clothes, home));


        // create products
        Product sugar = new Product();
        sugar.setName("Smart Phone");
        sugar.setDescription("Latest model smartphone with amazing features");
        sugar.setImgUrl("img/claypot.png");
        sugar.setPrice(699.99);
        sugar.setCategory(electronic);


        // creating laptop
        Product laptop = new Product();
        laptop.setName("laptop");
        laptop.setDescription("High performance laptop for work and video game");
        laptop.setImgUrl("https://placehold.co/600x400");
        laptop.setPrice(999.99);
        laptop.setCategory(electronic);

        // creating jakcet
        Product jacket = new Product();
        jacket.setName("jacket");
        jacket.setDescription("winter jacket with fashion style");
        jacket.setImgUrl("https://placehold.co/600x400");
        jacket.setPrice(129.99);
        jacket.setCategory(clothes);

        // creating blender
        Product blender = new Product();
        blender.setName("blender");
        blender.setDescription("High-speed blender for smoothies and more");
        blender.setImgUrl("https://placehold.co/600x400");
        blender.setPrice(79.99);
        blender.setCategory(clothes);


        productRepo.saveAll(Arrays.asList(sugar,laptop,jacket,blender));


    }

}

