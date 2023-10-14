package com.archis.starter.service;

import com.archis.starter.dto.ProductDto;
import com.archis.starter.dto.UserDto;
import com.archis.starter.entity.Product;
import com.archis.starter.entity.User;
import org.apache.catalina.UserDatabase;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();
    UserDto getUser(Long id) throws Exception;
    UserDto updateUser(Long id, UserDto userDto) throws Exception;
    String deleteUser(Long id) throws Exception;



    //Favoriye ürün ekleme
    //Satın alma
    //aldıklarını listeleme
    //favorileri listeleme
    void addToFavorites(UserDto userDto, ProductDto productDto);
    List<ProductDto> listFavorites(String UserDto);

    void buyProducts(String userName, List<ProductDto> productDos);

    List<ProductDto> getPurchasedProducts(String userName);

    int countFavoriteProducts(String userName);



}
