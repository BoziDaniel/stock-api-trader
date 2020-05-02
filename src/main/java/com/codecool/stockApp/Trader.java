package com.codecool.stockApp;

import java.io.IOException;

/**
 * Business logic for stock trading
 **/
public class Trader {


	private Logger logger;
	private StockAPIService stockService;

	public Trader(StockAPIService stockAPIService, Logger logger) {
        this.stockService = stockAPIService;
		this.logger = logger;
    }

	/** Checks the price of a stock, and buys it if the price is not greater than the bid amount.
	 * 	@return whether any stock was bought */
	public boolean buy(String symbol, double bid) throws IOException {
		double price = stockService.getPrice(symbol);

        boolean result;
		if (price <= bid) {
			result = true;
			stockService.buy(symbol);
			logger.log("Purchased " + symbol + " stock at $" + bid + ", since its higher that the current price ($" + price + ")");
		}
		else {
            logger.log("Bid for " + symbol + " was $" + bid + " but the stock price is $" + price + ", no purchase was made.");
			result = false;
		}
		return result;
	}

}