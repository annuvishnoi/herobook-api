package com.galvanize.herobook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galvanize.herobook.model.Villain;

public interface VillainRepository extends JpaRepository<Villain, String> {

}
