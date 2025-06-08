package com.jesusfc.demo.database.repository;

import com.jesusfc.demo.database.entity.CollectionFilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jesusfc
 * Created on may 2023
 */
@Repository
public interface CollectionFilmRepository extends JpaRepository<CollectionFilmEntity, Long> {
}
