package com.api.repository;

import com.api.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  AppUser getAppUserByEmail(String email);
}
