package com.example.springbootsuperheroes.antiHero.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.springbootsuperheroes.antiHero.entity.AntiHeroEntity;

public interface AntiHeroRepository extends CrudRepository<AntiHeroEntity, UUID> {

}
