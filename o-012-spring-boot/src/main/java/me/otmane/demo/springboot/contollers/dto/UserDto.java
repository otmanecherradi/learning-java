package me.otmane.demo.springboot.contollers.dto;

import me.otmane.demo.springboot.entities.Nationality;

public record UserDto(
        String username,
        String password) {
}
