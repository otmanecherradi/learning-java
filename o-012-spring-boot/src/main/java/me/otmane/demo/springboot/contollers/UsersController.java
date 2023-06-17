package me.otmane.demo.springboot.contollers;

import me.otmane.demo.springboot.contollers.dto.UserDto;
import me.otmane.demo.springboot.entities.User;
import me.otmane.demo.springboot.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    IService<User, Long> usersService;

    @GetMapping(value = "/")
    public ResponseEntity<Response<List<User>>> list() {
        return ResponseEntity.ok(new Response<>(usersService.list()));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> create(@RequestBody UserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        usersService.create(user);
        return ResponseEntity.created(null).body(null);
    }

    @GetMapping(value = "/{pk}")
    public ResponseEntity<User> byPk(@PathVariable Long pk) {
        Optional<User> maybeUser = usersService.byPk(pk);

        if (maybeUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        return ResponseEntity.ok(maybeUser.get());
    }

    @DeleteMapping(value = "/{pk}")
    public ResponseEntity delete(@PathVariable Long pk) {
        usersService.delete(pk);
        return ResponseEntity.accepted().build();
    }


    private static record Response<Type>(Type data) {
    }
}
