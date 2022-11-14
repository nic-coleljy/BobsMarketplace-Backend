package com.bobsmarketplace.prototype.repository;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "select * from bobsmarketplace.transaction where id_of_buyer = :id UNION (select tid, comment, commission, date_time, min_price, quantity, status, total_cost, id_of_buyer, cid_of_commodity, delivery_charge, sub_total, financing, address, is_express, zip from bobsmarketplace.transaction as t, (select cid from bobsmarketplace.commodity where id_of_seller = :id) as c where t.cid_of_commodity = c.cid);", nativeQuery = true)
    public List<Transaction> findTransactionByUserId(@Param("id") Long id);
}
