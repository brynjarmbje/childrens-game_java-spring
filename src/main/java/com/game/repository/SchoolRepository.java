package com.game.repository;

import com.game.entity.Child;
import com.game.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
//    List<Child> findBySchoolId(School school);
}
