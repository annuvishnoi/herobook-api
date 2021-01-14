package com.galvanize.herobook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galvanize.herobook.model.Hero;

public interface HeroRepository extends JpaRepository<Hero, String> {

}
