package com.example.spring.app.service;

import com.example.spring.app.dto.AccountDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AdminMangeAccountService {

    List<AccountDto> findAllAccount(PageRequest pageRequest) throws Exception;

    void deleteAccountByAdmin(final String targetUserId) throws Exception;

}
