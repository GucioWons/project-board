package com.example.user.Repository;

import com.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByPassw(int passw);
    List<User> findByEmailOrFirstNameOrLastName(String dataEmail, String dataFirstName, String dataLastName);
    User findById(long id);
    List<User> findAll();
    boolean existsById(long id);
}
