package com.payconiq.stockapp.stockapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.payconiq.stockapp.model.Stock;
import com.payconiq.stockapp.service.StockService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTests {


	@Autowired
	private StockService stockService;

	@Test
	public void test_getStock() {
		List<Stock> stocks= stockService.getAllStock(1, 5, "name");
		assertEquals(5,stocks.size());
	}
	
	
}
