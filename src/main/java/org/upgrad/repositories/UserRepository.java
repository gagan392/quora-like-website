package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query(nativeQuery = true, value = "select * from users where upper(userName) = upper(?1)")
    User findUserByUsername(String username);

    @Query(nativeQuery = true, value = "select * from users where upper(email) = upper(?1)")
    User findUserByEmail(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into users (username, password, email) values (?1, ?2, ?3)")
    void addUser(String username, String password, String email);
}
