package com.payconiq.stockapp.stockapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.payconiq.stockapp.model.Stock;
import com.payconiq.stockapp.response.JsonResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class StockControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test_getAllstocksIntegration() {
		ResponseEntity<JsonResponse> response = this.restTemplate
				.getForEntity("http://localhost:" + port + "/api/stocks", JsonResponse.class);
		List<Stock> stocks = (List<Stock>) response.getBody().getData();

		assertEquals(12, stocks.size());
		assertNotNull(response.getBody().getData());

	}

	@Test
	public void test_getstockByIdIntegration() {
		long stockId = 11;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		ResponseEntity<JsonResponse> response = this.restTemplate
				.getForEntity("http://localhost:" + port + "/api/stocks/" + stockId, JsonResponse.class);
		JsonResponse<Stock> stock = (JsonResponse<Stock>) response.getBody();

		assertNotNull(stock.getData());

	}

	@Test
	public void test_deleteStockByIdIntegration() {
		long stockId = 10;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		this.restTemplate.delete("http://localhost:" + port + "/api/stocks/" + stockId);
		ResponseEntity<Void> response = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/stocks?id=" + stockId, HttpMethod.DELETE, null, Void.class,
				new Long(1));

		int status = response.getStatusCodeValue();
		assertEquals(202, status);

	}

	@Test
	public void test_getstockByIdExceptionIntegration() {
		long stockId = 30;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		ResponseEntity<JsonResponse> response = this.restTemplate
				.getForEntity("http://localhost:" + port + "/api/stocks/" + stockId, JsonResponse.class);
		JsonResponse<Stock> stock = (JsonResponse<Stock>) response.getBody();

		assertNotNull(stock.getError());

	}

	@Test
	public void test_postStockIntegration() {
		Stock stock = new Stock(21l, "Maruthi", 8000.09, new Timestamp(System.currentTimeMillis()));
		long stockId = 19;
	// execute
	ResponseEntity<JsonResponse> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/api/stocks/", 
			stock, 
															JsonResponse.class);

	int status = responseEntity.getStatusCodeValue();
	assertEquals(200, HttpStatus.CREATED.value(), status);
	assertNotNull(responseEntity.getBody().getData());
	}

}
