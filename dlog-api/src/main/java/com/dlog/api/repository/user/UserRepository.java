package com.dlog.api.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dlog.api.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	Optional<User> findByUuid(String uuid);
	
	Optional<User> findByUserIdAndIsDeletedFalse(String userId);

}
