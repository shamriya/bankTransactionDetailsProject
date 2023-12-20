package com.bank.details.repository;

import com.bank.details.model.AccountDetails;
import com.bank.details.model.Transaction;
import com.bank.details.model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

   /* @Query("SELECT  date_part('month', to_timestamp(transaction_date,'YYYY-MM-DD hh24:mi:ss'))::" +
            " numeric  AS txn_month,"+
            "date_part('week', to_timestamp(transaction_date,'YYYY-MM-DD hh24:mi:ss')):: numeric  AS txn_week,"+
    "t.*"+
    "FROM minna_tech.transaction t")
    List<TransactionDetails> getSubsequentTransaction();*/

    @Query("SELECT t FROM Transaction t  WHERE t.amount<0")
    List<Transaction> findNegativeTransaction();

   /* @Query("SELECT t.*  FROM Transaction  t")
    List<Transaction> getIntervalWiseTransactions();*/
}
