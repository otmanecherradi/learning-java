package me.otmane.demo.springboot.services;

import me.otmane.demo.springboot.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements IService<User, Long> {
    private final List<User> userList = new ArrayList<>();

    public UsersService() {
        User user = new User();
        user.setPk(userList.size() + 1L);
        user.setUsername("user" + (userList.size() + 1));
        user.setPassword("pass" + (userList.size() + 1));
        userList.add(user);
    }

    @Override
    public User create(User user) {
        user.setPk(userList.size() + 1L);
        userList.add(user);
        return user;
    }

    @Override
    public List<User> list() {
        return userList;
    }

    @Override
    public Optional<User> byPk(Long pk) {
        return userList.stream().filter(u -> u.getPk().equals(pk)).findFirst();
    }

    @Override
    public void delete(Long pk) {
        User u = new User();
        u.setPk(pk);
        userList.remove(u);
    }
}
