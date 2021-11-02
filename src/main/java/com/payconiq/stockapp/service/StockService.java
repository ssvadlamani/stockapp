package com.payconiq.stockapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.payconiq.stockapp.model.Stock;

public interface StockService {

	List<Stock> getAllStock(int pageNo, int pageSize, String sortBy);

	Stock getStock(Long id);

	Stock addStock(Stock stock);

	String deleteStock(Long id);

	Stock updateStock(Long id, Map<Object, Object> fields);

}
