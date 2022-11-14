package com.bobsmarketplace.prototype.controller;

import com.bobsmarketplace.prototype.entity.Buyer;
import com.bobsmarketplace.prototype.entity.Commodity;
import com.bobsmarketplace.prototype.entity.Transaction;
import com.bobsmarketplace.prototype.exception.commodity.CommodityNotFoundException;
import com.bobsmarketplace.prototype.exception.commodityname.CommodityNameNotFoundException;
import com.bobsmarketplace.prototype.exception.transaction.TransactionNotFoundException;
import com.bobsmarketplace.prototype.serviceImplementation.BuyerServiceImpl;
import com.bobsmarketplace.prototype.serviceImplementation.CommodityServiceImpl;
import com.bobsmarketplace.prototype.serviceImplementation.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    //Declaration of variables
    private TransactionServiceImpl transactionServiceImpl;
    private CommodityServiceImpl commodityServiceImpl;
    private BuyerServiceImpl buyerServiceImpl;
    /**
     * Instantiating the variables for this class.
     *
     * @param transactionServiceImpl
     */
    @Autowired
    public TransactionController(TransactionServiceImpl transactionServiceImpl, CommodityServiceImpl commodityServiceImpl, BuyerServiceImpl buyerServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
        this.commodityServiceImpl = commodityServiceImpl;
        this.buyerServiceImpl = buyerServiceImpl;
    }

    /**
     * Add a new transaction with POST request to "/buyer"
     *
     * @param newTransaction
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/buyer")
    public Transaction addTransaction(@Valid @RequestBody Transaction newTransaction) {
        Commodity commodity = commodityServiceImpl.getCommodityById(newTransaction.getCommodityEntity().getCid());
        commodity.setQuantity(commodity.getQuantity() - newTransaction.getQuantity());
        newTransaction.setCommodityEntity(commodityServiceImpl.updateCommodity(commodity.getCid(), commodity));
        newTransaction.setDateTime(LocalDateTime.now());
        return transactionServiceImpl.save(newTransaction);
    }

    /**
     * List all transactions in the system
     *
     * @return list of all transactions
     */
    @GetMapping("/")
    public List<Transaction> getTransaction() {
        return transactionServiceImpl.getAllTransaction();
    }

    /**
     * Search for all transactions of a user, given user's id
     *
     * @param id
     * @return list of all transactions of a user
     */
    @GetMapping("/user/{id}")
    public List<Transaction> getTransactionByUserId(@PathVariable Long id) throws TransactionNotFoundException {
        return transactionServiceImpl.getTransactionByUserId(id);
    }

    /**
     * Search for transaction with the given tid
     * If there is no transaction with the given "tid", throw a TransactionNotFoundException
     *
     * @param tid
     * @return transaction with the given tid
     */
    @GetMapping("/{tid}")
    public Transaction getTransactionById(@PathVariable Long tid) throws TransactionNotFoundException {
        Transaction transaction = transactionServiceImpl.getTransactionById(tid);

        if (transaction == null) {
            throw new TransactionNotFoundException(tid);
        }
        return transactionServiceImpl.getTransactionById(tid);
    }

    /**
     * If there is no transaction with the given "tid", throw a TransactionNotFoundException
     *
     * @param tid a Long value
     * @param newTransaction a transaction object containing the new transaction info to be updated
     * @return the updated, or newly added transaction
     */
    @PutMapping("/admin/{tid}")
    public Transaction updateTransaction(@Valid @PathVariable Long tid, @RequestBody Transaction newTransaction) throws TransactionNotFoundException {
        Transaction transaction = transactionServiceImpl.updateTransaction(tid, newTransaction);

        if (transaction == null) {
            throw new TransactionNotFoundException(tid);
        }
        return transaction;
    }

    /**
     * Remove a transaction with the DELETE request to "/admin/{tid}"
     * If there is no transaction with the given "tid", throw a TransactionNotFoundException
     *
     * @param tid
     */
    @DeleteMapping("/admin/{tid}")
    public void deleteTransactionById(@PathVariable Long tid) throws
            TransactionNotFoundException {
        try {
            transactionServiceImpl.deleteTransactionById(tid);
        } catch (EmptyResultDataAccessException e) {
            throw new TransactionNotFoundException(tid);
        }
    }
}
