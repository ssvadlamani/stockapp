package com.payconiq.stockapp.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.payconiq.stockapp.exception.StockNotFoundException;
import com.payconiq.stockapp.model.Stock;
import com.payconiq.stockapp.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

	@Autowired
	StockRepository stockRepository;

	@Override
	public List<Stock> getAllStock(int pageNo, int pageSize, String sortBy) {
		LOGGER.info("CALLING REPOSITORY TO FETCH ALL STOCKS!");
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Stock> stocks = stockRepository.findAll(paging);
		LOGGER.info("RESPONSE FROM REPOSITORY : " + stocks.getSize());
		if (stocks.getSize() <= 0) {
			throw new StockNotFoundException();
		}
		return stocks.toList();

	}

	@Override
	public Stock getStock(Long id) {
		LOGGER.info("CALLING REPOSITORY TO FETCH STOCK BY ID : " + id);
		Optional<Stock> stock;
		try {
			stock = stockRepository.findById(id);
			if (stock.get() == null) {
				LOGGER.error("STOCK NOT FOUND FOR ID : " + id);
				throw new StockNotFoundException();
			}
		} catch (RuntimeException e) {
			LOGGER.error("GOT EXCEPTION WHILE FETCHING STOCK : " + id);
			throw new IllegalArgumentException();
		}
		return stock.get();
	}

	@Override
	public Stock addStock(Stock stock) {
		LOGGER.info("SAVE STOCK OBJECT");
		Stock pStock = null;
		try {
			pStock = stockRepository.save(stock);
		} catch (Exception e) {
			LOGGER.error("GOT EXCEPTION WHILE SAVING STOCK : ");
			throw new RuntimeException();
		}
		return pStock;
	}

	@Override
	public Stock updateStock(Long id, Map<Object, Object> fields) {
		LOGGER.info("UPDATE STOCK : " + id);
		Stock stock = getStock(id);
		fields.forEach((key, value) -> {
			System.out.println(key + "------------" + value);
			Field field = ReflectionUtils.findField(Stock.class, (String) key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, stock, value);
		});

		return addStock(stock);
	}

	@Override
	public String deleteStock(Long id) {
		LOGGER.info("DELETE STOCK BY ID : " + id);
		String status = "";
		Optional<Stock> stock = stockRepository.findById(id);
		try {
			if (stock.isPresent()) {
				stockRepository.delete(stock.get());
				status = "Stock deleted sucessfully!";
			} else {
				LOGGER.error("GOT EXCEPTION WHILE FETCHING STOCK : ");
				throw new StockNotFoundException();
			}
		} catch (Exception e) {
			LOGGER.error("GOT EXCEPTION WHILE DELETING STOCK : ");
			throw new RuntimeException();
		}
		return status;
	}

}
