package com.B1team.b01.service;

import com.B1team.b01.entity.Product;
import com.B1team.b01.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
}
