package com.edu.miu.controller;

import com.edu.miu.controller.annotation.ExecutionTime;
import com.edu.miu.dto.ProductDTO;
import com.edu.miu.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class Controller {
    ProductService service;
    @ExecutionTime
    @GetMapping
    public List<ProductDTO> getAllproducts(){
    return service.findAll();
    }
    @PostMapping
    public void saveProduct(@RequestBody ProductDTO dto){
        service.saveProduct(dto);
    }
}
