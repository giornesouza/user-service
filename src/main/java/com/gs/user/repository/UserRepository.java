package com.gs.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gs.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public Optional<User> findById(Long id);
    
    public Long countByEmail(String email);
    
    public Optional<User> findByEmail(String email);

}