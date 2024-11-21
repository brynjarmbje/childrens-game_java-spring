package com.game.repository;

import com.game.entity.Child;
import com.game.entity.School;
import com.game.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    // JPA should implement the methods

    List<Child> findAll();

    List<Child> findByName(String name);

    List<Child> findBySchool(School school);

    @Query("SELECT c FROM Child c " +
            "WHERE c.school.id = (SELECT a.school.id FROM Admin a WHERE a.id = :adminId) " +
            "AND c.id NOT IN (SELECT mc.id FROM Admin a JOIN a.children mc WHERE a.id = :adminId)")
    List<Child> findUnmanagedChildrenByAdminIdAndSchoolId(@Param("adminId") Long adminId);

	//Session saveSession(Session session);
}
