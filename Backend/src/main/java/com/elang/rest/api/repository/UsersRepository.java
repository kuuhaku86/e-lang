package com.elang.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elang.rest.api.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	Users findByEmailAndPassword(String email, String password);
	Users findByEmail(String email);
	List<Users> findByVerified(String verified);
}
