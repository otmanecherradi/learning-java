package me.otmane.demo;

import lombok.extern.slf4j.Slf4j;
import me.otmane.demo.models.Nationality;
import me.otmane.demo.models.User;
import me.otmane.demo.repositories.NationalitiesRepository;
import me.otmane.demo.repositories.UsersRepository;

import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepository();
        NationalitiesRepository nationalitiesRepository = new NationalitiesRepository();

        List<User> userList = usersRepository.list();

        User user = new User();
        user.setUsername("user" + (userList.size() + 1));
        user.setPassword("pass" + (userList.size() + 1));

        log.info(user.toString());

        usersRepository.create(user);

        log.info(user.toString());

        for (User u : usersRepository.list())
            log.info(u.toString());

        Nationality moroccanNationality = nationalitiesRepository.byId(1L);

        if (moroccanNationality == null) {
            moroccanNationality = new Nationality();
            moroccanNationality.setName("Moroccan");

            nationalitiesRepository.create(moroccanNationality);
        }


        log.info(moroccanNationality.toString());
        for (User u : moroccanNationality.getUsers())
            log.info(u.toString());

        user.setNationality(moroccanNationality);

        usersRepository.update(user);

        for (Nationality n : nationalitiesRepository.list())
            log.info(n.toString());


    }
}