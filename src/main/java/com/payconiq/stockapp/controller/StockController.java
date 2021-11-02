package com.payconiq.stockapp.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payconiq.stockapp.model.Stock;
import com.payconiq.stockapp.response.JsonResponse;
import com.payconiq.stockapp.service.StockService;
import com.payconiq.stockapp.util.Utils;

/**
 * 
 * @author sivasankar.v
 *
 */
@RestController
@RequestMapping("/api")
public class StockController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockService stockService;

	@RequestMapping(value = "/stocks", method = RequestMethod.GET)
	ResponseEntity<JsonResponse> getAllStocks(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "12") int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy) {
		LOGGER.info("BEGIN GET ALL STOCKS!");
		List<Stock> stocks = stockService.getAllStock(pageNo, pageSize, sortBy);
		LOGGER.info("END GET ALL STOCKS!");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(Utils.buildResponse(stocks, null, null));

	}

	@RequestMapping(value = "/stocks/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<Stock>> getStock(@PathVariable("id") Long id) {
		LOGGER.info("BEGIN GET STOCK BY ID : "+id);
		Stock stock = stockService.getStock(id);
		LOGGER.info("END GET STOCK BY ID : "+id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(Utils.buildResponse(stock, null, null));
	}

	@RequestMapping(value = "/stocks", method = RequestMethod.POST)
	public ResponseEntity<JsonResponse> addStock(@RequestBody Stock stock) {
		LOGGER.info("BEGIN POST STOCK ");
		Stock savedStock= stockService.addStock(stock);
		LOGGER.info("END POST STOCK ");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(Utils.buildResponse(savedStock, null, null));
	}

	@RequestMapping(value = "/stocks", method = RequestMethod.DELETE)
	public ResponseEntity<JsonResponse> deleteStock(@RequestParam(value = "id") Long id) {
		LOGGER.info("BEGIN DELETE STOCK BY ID : "+id);
		String status = stockService.deleteStock(id);
		LOGGER.info("END DELETE STOCK BY ID : "+id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(Utils.buildResponse(status, null, null));
	}

	@RequestMapping(value = "/stocks/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<JsonResponse> updateStock(@PathVariable("id") Long id,
			@RequestBody Map<Object, Object> fields) {
		LOGGER.info("BEGIN UPDATE STOCK ");
		Stock updatedStock = stockService.updateStock(id, fields);
		LOGGER.info("END UPDATE STOCK ");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(Utils.buildResponse(updatedStock, null, null));
	}
	
}
