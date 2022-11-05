package com.example.spring.repositories;

import com.example.spring.repositories.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    Optional<AccountEntity> findByNickname(String nickname);

    Optional<AccountEntity> findByUserIdAndState(String userId, Integer state);
}
