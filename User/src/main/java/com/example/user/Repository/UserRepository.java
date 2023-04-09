package com.example.user.Repository;

import com.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    boolean existsUserByPassw(int passw);
    List<User> findUserByEmailOrFirstNameOrLastName(String dataEmail, String dataFirstName, String dataLastName);
    User findUserById(long id);
    List<User> findAll();
    boolean existsUserById(long id);
}
