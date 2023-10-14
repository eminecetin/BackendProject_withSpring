package com.archis.starter.service.impl;

import com.archis.starter.dto.ProductDto;
import com.archis.starter.dto.UserDto;
import com.archis.starter.entity.User;
import com.archis.starter.repository.UserRepository;
import com.archis.starter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository=userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDto,User.class)),
                UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream().map(user->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            throw new Exception("ıd'li user bulunamadı..");
        }
        return modelMapper.map(user.get(),UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) throws Exception {
       Optional<User> resultUser=userRepository.findById(id);
       if(resultUser.isPresent()){
           throw new Exception("id'li user bulunamadı..");
       }
       resultUser.get().setUserName(userDto.getUserName());
       resultUser.get().setEmail(userDto.getEmail());

       return modelMapper.map(userRepository.save(resultUser.get()),UserDto.class);
    }

    @Override
    public String deleteUser(Long id) throws Exception {
       Optional<User> user=userRepository.findById(id);
       if(user.isPresent()){
           throw new Exception("id'li kullanıcı bulunamadı!!");
       }
       userRepository.deleteById(id);
       return "Silindi!";
    }


    private Map<String, List<ProductDto>> favorites = new HashMap<>();

    @Override
    public void addToFavorites(UserDto userDto, ProductDto productDto) {
        if (!favorites.containsKey(userDto.getUserName())) {
            favorites.put(userDto.getUserName(), new ArrayList<>());
        }

        List<ProductDto> userFavorites = favorites.get(userDto.getUserName());
        userFavorites.add(productDto);

        // Favorilere ekleme işlemi başarıyla tamamlandı
    }

    @Override
    public List<ProductDto> listFavorites(String userName) {
        if (favorites.containsKey(userName)) {
            return favorites.get(userName);
        } else {
            return Collections.emptyList(); // Eğer kullanıcının favorileri boşsa, boş bir liste döndürün.
        }
    }


    private Map<String, List<ProductDto>> purchasedProducts = new HashMap<>();

    @Override
    public void buyProducts(String userName, List<ProductDto> productDos) {
        if (!purchasedProducts.containsKey(userName)) {
            purchasedProducts.put(userName, new ArrayList<>());
        }

        List<ProductDto> userProducts = purchasedProducts.get(userName);
        userProducts.addAll(productDos);
    }



    @Override
    public List<ProductDto> getPurchasedProducts(String userName) {
        if (purchasedProducts.containsKey(userName)) {
            return purchasedProducts.get(userName);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public int countFavoriteProducts(String userName) {
        if (favorites.containsKey(userName)) {
            List<ProductDto> userFavorites = favorites.get(userName);
            return userFavorites.size();
        } else {
            return 0;
        }
    }
}
