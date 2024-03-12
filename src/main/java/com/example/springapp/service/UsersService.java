package com.example.springapp.service;

import com.example.springapp.models.PasswordUpdate;
import com.example.springapp.models.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springapp.repositories.UsersRepo;

@Service
public class UsersService {

    @Autowired
    UsersRepo usersRepo;

    public String getType(String id) {
        Users user = usersRepo.findById(id).orElse(null);

        if (user == null)
            return "User not found";

        return user.getType();
    }

    public String saveUser(Users user) {
        String id = user.getId();
        usersRepo.save(user);
        return "User saved with id " + id;
    }

    public String deleteUser(String id) {
        try {
            usersRepo.deleteById(id);
        } catch (Exception e) {
            return "User not found";
        }
        return "User deleted";
    }

    public String updatePassword(PasswordUpdate pw) {
        Users user = usersRepo.findById(pw.getId()).orElse(null);

        if (user == null)
            return "User not found";

        if (user.getPassword().equals(pw.getOldpassword())) {
            user.setPassword(pw.getNewpassword());
            usersRepo.saveAndFlush(user);
        } else {
            return "Old password incorrect";
        }

        return "Password updated";
    }

    public List<Users> getAllUsers(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return usersRepo.findAll(paging).getContent();
    }

    public List<Users> getOrderByStatus(String type, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return usersRepo.findByType(type, paging).getContent();
    }

    public Users getUserById(String id) {
        Users user = usersRepo.findById(id).orElse(null);
        if (user == null) {
            user = new Users();
            user.setId("-1");
            user.setType("User not found");
        }
        return user;
    }
}
