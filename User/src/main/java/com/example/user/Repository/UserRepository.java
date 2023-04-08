package com.example.user.Repository;

import com.example.user.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByPassw(String passw);
    @Query("update User u set u.email=:email, u.passw=:passw where u.id=:id")
    User updateEmailAndPassw(@Param("email") String email, @Param("passw") String passw, @Param("id")long id);

    List<User> findByEmailOrFirstNameOrLastName(String dataEmail, String dataFirstName, String dataLastName);
    User findUserById(long id);
}
