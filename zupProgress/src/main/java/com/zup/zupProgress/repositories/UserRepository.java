package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel,String> {
    UserDetails findByLogin(String login);
}
