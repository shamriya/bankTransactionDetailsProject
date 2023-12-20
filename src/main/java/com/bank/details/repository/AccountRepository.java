package com.bank.details.repository;

import com.bank.details.model.AccountDetails;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountDetails, Long> {

    @Query("SELECT t FROM AccountDetails t WHERE account_id =?1")
    AccountDetails findByAccountNumber(UUID accountId);

    @Query("SELECT t FROM AccountDetails t WHERE account_id in :accountIds")
    List<AccountDetails> findAllByUUIDId(@Param("accountIds")List<UUID> accountIds);

}
