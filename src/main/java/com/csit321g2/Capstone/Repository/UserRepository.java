package com.csit321g2.Capstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csit321g2.Capstone.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Query("SELECT new com.csit321g2.Capstone.Entity.UserEntity(u.uid, u.fname, u.lname, u.username, u.type, u.isDeleted) FROM UserEntity u WHERE u.uid = :uid")
    UserEntity findByIdWithoutPassword(@Param("uid") Long uid);

    @Query("SELECT new com.csit321g2.Capstone.Entity.UserEntity(u.uid, u.fname, u.lname, u.username, u.type, u.isDeleted) FROM UserEntity u WHERE u.username = :username")
    UserEntity findByUsernameWithoutPassword(@Param("username") String username);

    @Query("SELECT new com.csit321g2.Capstone.Entity.UserEntity(u.uid, u.fname, u.lname, u.username, u.type, u.isDeleted) FROM UserEntity u")
    List<UserEntity> findAllUsersWithoutPassword();
}
