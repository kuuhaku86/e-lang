package com.elang.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elang.rest.api.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>, CrudRepository<Admin, Long> {
	Admin findByEmailAndPassword(String email, String password);
}
