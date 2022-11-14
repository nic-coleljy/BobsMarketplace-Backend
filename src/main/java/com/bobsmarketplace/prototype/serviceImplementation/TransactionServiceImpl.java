package com.bobsmarketplace.prototype.serviceImplementation;

import com.bobsmarketplace.prototype.entity.Transaction;
import com.bobsmarketplace.prototype.repository.TransactionRepository;
import com.bobsmarketplace.prototype.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    //Declaration of variables
    private TransactionRepository transactionRepository;

    /**
     * Instantiating the variables for this class.
     *
     * @param transactionRepository
     */
    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    /**
     * Persist a new transaction
     *
     * @param newTransaction
     * @return Transaction that is saved into the database.
     */
    @Override
    public Transaction save(Transaction newTransaction) {
        return transactionRepository.save(newTransaction);
    }

    /**
     * Returns all the transactions that is in the database
     *
     * @return List of transactions
     */
    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    /**
     * Returns all the transactions that include user's id is in the database
     *
     * @return List of transactions that include user's id
     */
    @Override
    public List<Transaction> getTransactionByUserId(Long id) {
        return transactionRepository.findTransactionByUserId(id);
    }

    /**
     * Finds a transaction based on its ID and returns the object if found in the database.
     *
     * @param tid
     * @return A transaction if it exists or else it will return null.
     */
    @Override
    public Transaction getTransactionById(Long tid) {
        return transactionRepository.findById(tid).map(transaction -> {
            return transaction;
        }).orElse(null);
    }

    /**
     * Finds a transaction based on the tid and updates its contents with the data inside newTransaction
     * @param tid
     * @param newTransaction
     * @return The updated transaction or else return null
     */
    @Override
    public Transaction updateTransaction(Long tid, Transaction newTransaction) {
        return transactionRepository.findById(tid).map(transaction -> {
            transaction.setTid(newTransaction.getTid());
            transaction.setQuantity(newTransaction.getQuantity());
            transaction.setDateTime(newTransaction.getDateTime());
            transaction.setCommission(newTransaction.getCommission());
            transaction.setStatus(newTransaction.getStatus());
            transaction.setMinPrice(newTransaction.getMinPrice());
            transaction.setTotalCost(newTransaction.getTotalCost());
            transaction.setComment(newTransaction.getComment());
            transaction.setDeliveryCharge(newTransaction.getDeliveryCharge());
            transaction.setSubTotal(newTransaction.getSubTotal());
            transaction.setFinancing(newTransaction.getFinancing());
            transaction.setAddress(newTransaction.getAddress());
            transaction.setZip(newTransaction.getZip());
            transaction.setIsExpress(newTransaction.getIsExpress());
            return transactionRepository.save(transaction);
        }).orElse(null);    }

    /**
     * Remove a transaction with the given tid
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a commodity will also remove all its associated information
     */
    @Override
    public void deleteTransactionById(Long tid) {
        transactionRepository.deleteById(tid);
    }
}
