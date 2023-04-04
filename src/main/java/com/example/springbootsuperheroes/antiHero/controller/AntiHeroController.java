package com.example.springbootsuperheroes.antiHero.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springbootsuperheroes.antiHero.dto.AntiHeroDto;
import com.example.springbootsuperheroes.antiHero.entity.AntiHeroEntity;
import com.example.springbootsuperheroes.antiHero.service.AntiHeroService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/anti-heroes")
public class AntiHeroController {
    private final AntiHeroService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<AntiHeroDto> getAntiHeroes() {
        var antiHeroList = StreamSupport.stream(service.findAllAntiHeroes().spliterator(), false)
                .collect(Collectors.toList());

        return antiHeroList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AntiHeroDto getAntiHeroById(@PathVariable("id") UUID id) {
        return this.convertToDto(service.findAntiHeroById(id));
    }

    @PostMapping
    public AntiHeroDto postAntiHero(@Valid @RequestBody AntiHeroDto antiHeroDto) {
        var entity = convertToEntity(antiHeroDto);
        var antiHero = service.addAntiHero(entity);

        return convertToDto(antiHero);
    }

    @PutMapping("/{id}")
    public void putAntiHero(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AntiHeroDto antiHeroDto) {
        if (!id.equals(antiHeroDto.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match.");

        var antiHeroEntity = convertToEntity(antiHeroDto);
        service.updateAntiHero(id, antiHeroEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteAntiHeroById(@PathVariable("id") UUID id) {
        service.removeAntiHeroById(id);
    }

    private AntiHeroDto convertToDto(AntiHeroEntity entity) {
        return mapper.map(entity, AntiHeroDto.class);
    }

    private AntiHeroEntity convertToEntity(AntiHeroDto dto) {
        return mapper.map(dto, AntiHeroEntity.class);
    }
}
