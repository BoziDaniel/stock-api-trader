package com.codecool.stockApp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// TODO
class TraderTest {

    public static final String URL = "https://financialmodelingprep.com/api/v3/stock/real-time-price/aapl";
    Logger logger = new Logger();

    RemoteURLReader remoteURLReader = Mockito.mock(RemoteURLReader.class);
    StockAPIService stockAPIService = new StockAPIService(remoteURLReader);
    Trader trader = new Trader(stockAPIService, logger);

//works but I feel it a bit workaround, I mocked the url reader not the stock api class, if I do that it returns 0 instead of 200.
    @Test
        // Bid was lower than price, buy should return false.
    void testBidLowerThanPrice() throws IOException {
        when(remoteURLReader.readFromUrl(URL)).thenReturn("{'price': 200.00}");
//        when(stockAPIService.getPrice(URL)).thenReturn(200.0);
        assertFalse(trader.buy("aapl", 100.00));
    }

    @Test
        // bid was equal or higher than price, buy() should return true.
    void testBidHigherThanPrice() throws IOException {
        when(remoteURLReader.readFromUrl(URL)).thenReturn("{'price': 200.00}");
        assertTrue(trader.buy("aapl", 300.00));
        }
}