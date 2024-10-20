package com.game.repository;

import com.game.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

  List<Child> findByName(String name);
}