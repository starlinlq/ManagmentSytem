package com.teamProject.ManagmentSytem.services;

import com.teamProject.ManagmentSytem.dto.UserDto;
import com.teamProject.ManagmentSytem.entities.User;
import com.teamProject.ManagmentSytem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean existsById(long id) {return userRepository.existsById(id);}

    public void deleteById(long id) { userRepository.deleteById(id);}

    // Create
    @Transactional
    public User save(User user){
        user.setRoles(List.of("ROLE_USER"));
        return userRepository.save(user);

    }

    // Read All users
    @Transactional
    public List<UserDto> readAllUsers(int pageNo, int pageSize, String[] arr){
        List<Order> orders = new ArrayList<>();
        for(String sortBy: arr){
            orders.add(new Order(Sort.Direction.ASC, sortBy));
        }
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        Page<User> users = userRepository.findAll(paging);

        if(users.hasContent()){
            return users.getContent().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
        }

        return  new ArrayList<>();
    }

   public List<UserDto> findByUsernameContaining(String username, String[] filters,  int pageNo, int pageSize){
        List<Order> orders = new ArrayList<>();

        for(String filter: filters){
            orders.add(new Order(Sort.Direction.ASC, filter));
        }

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        Page<User> users = userRepository.findByUsernameContaining(username, paging);

        if(users.hasContent()){
            return users.getContent().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
        } else
            return new ArrayList<>();
   }


    public Optional<User> readOneUserById(String userName){
        return userRepository.findByUsername(userName);
    }

    @Transactional
    public void removeUser(String userName){
        userRepository.deleteByUsername(userName);
    }

    public UserDto toUserDto(User user){return UserMapper.toUserDto(user);}

    public static class UserMapper {
        static UserDto toUserDto(User user){
            return UserDto.builder()
                    .roles(user.getRoles())
                    .hiredDate(user.getHiredDate())
                    .firstName(user.getProfile().getFirstName())
                    .lastName(user.getProfile().getLastName())
                    .email(user.getEmail())
                    .profileId(user.getProfile().getId())
                    .id(user.getId())
                    .build();
        }
    }
}
