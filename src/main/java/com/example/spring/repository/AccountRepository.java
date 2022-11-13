package com.example.spring.repository;

import com.example.spring.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    Optional<AccountEntity> findByNickname(String nickname);

    Optional<AccountEntity> findByUserIdAndState(String userId, Integer state);



}
