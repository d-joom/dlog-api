package com.dlog.api.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dlog.api.user.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	Optional<User> findByUuid(String uuid);
	
	Optional<User> findByUserIdAndIsDeletedFalse(String userId);

}
