package com.payconiq.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payconiq.stockapp.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
}