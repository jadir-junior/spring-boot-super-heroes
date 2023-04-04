package com.example.springbootsuperheroes.antiHero.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "anti_hero_entity")
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("AntiHero")
public class AntiHeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    private UUID id;

    @NotNull(message = "First Name is required")
    private String firstName;

    private String lastName;

    private String house;

    private String knownAs;

    private String createdAt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z").format(new Date());
}
