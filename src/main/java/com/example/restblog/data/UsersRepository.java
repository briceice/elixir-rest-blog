package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("from User a where a.username like %:term%")
    List<User> searchByUsername(@Param("term") String term);

    @Query("from User a where a.email like %:term%")
    List<User> searchByEmail(@Param("term") String term);

    User findUserByEmail(String email);

    User findUserByUsername(String userName);
}
