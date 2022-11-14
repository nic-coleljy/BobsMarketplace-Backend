package com.bobsmarketplace.prototype.service;

import com.bobsmarketplace.prototype.entity.Transaction;
import java.util.List;

public interface TransactionService {
    //CREATE
    Transaction save(Transaction newTransaction);

    //READ
    List<Transaction> getAllTransaction();

    List<Transaction> getTransactionByUserId(Long id);

    Transaction getTransactionById(Long tid);

    //UPDATE
    Transaction updateTransaction(Long tid, Transaction newTransaction);

    //DELETE
    void deleteTransactionById(Long tid);
}
