package com.archis.starter.controller;

import com.archis.starter.dto.ProductDto;
import com.archis.starter.dto.UserDto;
import com.archis.starter.entity.User;
import com.archis.starter.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(userService.getUser(id));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id,userDto));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) throws Exception {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PostMapping("/{userName}/favori/add")
    public ResponseEntity<String> addToFavorites(@RequestBody UserDto userDto, @RequestBody ProductDto productDto) {
        // addToFavorites işlemini çağır
        userService.addToFavorites(userDto, productDto);

        return ResponseEntity.ok("Ürün favorilere eklendi.");
    }



    @GetMapping("/{userName}/favori/list")
    public ResponseEntity<List<ProductDto>> listFavorites(@PathVariable("userName") String userName) {
        // Favori ürünleri listele

        if (userService.listFavorites(userName).isEmpty()) {
            return ResponseEntity.noContent().build(); // Kullanıcının favorileri boşsa 204 No Content döndür
        }

        return ResponseEntity.ok(userService.listFavorites(userName));
    }

    @PostMapping("/{userName}/product/buy")
    public ResponseEntity<String> buyProducts(@PathVariable("userName") String userName, @RequestBody List<ProductDto> productDos) {
        // Ürünleri satın almayı çağır
        userService.buyProducts(userName, productDos);

        return ResponseEntity.ok("Ürünler satın alındı.");
    }

    @GetMapping("/{userName}/product/buyed")
    public ResponseEntity<List<ProductDto>> getPurchasedProducts(@PathVariable("userName") String userName) {
        // Satın alınan ürünleri listele

        if (userService.getPurchasedProducts(userName).isEmpty()) {
            return ResponseEntity.noContent().build(); // Kullanıcının satın aldığı ürün yoksa 204 No Content döndür
        }

        return ResponseEntity.ok(userService.getPurchasedProducts(userName));
    }

    @GetMapping("/{userName}/favori/count")
    public ResponseEntity<Integer> countFavoriteProducts(@PathVariable("userName") String userName) {
        // Favori ürün sayısını al
        int favoriProductCount = userService.countFavoriteProducts(userName);

        return ResponseEntity.ok(favoriProductCount);
    }





}
