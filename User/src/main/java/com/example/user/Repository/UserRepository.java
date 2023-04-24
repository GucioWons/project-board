package com.example.user.Repository;

import com.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findById(Long id);
    List<User> findAll();
    boolean existsById(Long id);

    User findByEmail(String email);
    Set<User> findUserByEmailOrFirstNameOrLastName(String email, String firstName, String lastName);
}
