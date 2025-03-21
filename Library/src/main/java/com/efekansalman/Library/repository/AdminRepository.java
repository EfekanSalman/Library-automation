package com.efekansalman.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
