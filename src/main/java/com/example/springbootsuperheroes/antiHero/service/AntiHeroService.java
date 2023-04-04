package com.example.springbootsuperheroes.antiHero.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.springbootsuperheroes.antiHero.entity.AntiHeroEntity;
import com.example.springbootsuperheroes.antiHero.repository.AntiHeroRepository;
import com.example.springbootsuperheroes.exception.NotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AntiHeroService {

    private final AntiHeroRepository antiHeroRepository;

    public Iterable<AntiHeroEntity> findAllAntiHeroes() {
        return this.antiHeroRepository.findAll();
    }

    public AntiHeroEntity findAntiHeroById(UUID id) {
        return this.findOrThrow(id);
    }

    public void removeAntiHeroById(UUID id) {
        this.findOrThrow(id);

        this.antiHeroRepository.deleteById(id);
    }

    public AntiHeroEntity addAntiHero(AntiHeroEntity antiHero) {
        return this.antiHeroRepository.save(antiHero);
    }

    public void updateAntiHero(UUID id, AntiHeroEntity antiHero) {
        this.findOrThrow(id);

        this.antiHeroRepository.save(antiHero);
    }

    private AntiHeroEntity findOrThrow(final UUID id) {
        return this.antiHeroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Anti-hero by id " + id + " was not found"));
    }
}
