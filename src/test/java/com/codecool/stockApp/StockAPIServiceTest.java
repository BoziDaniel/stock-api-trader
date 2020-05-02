package com.codecool.stockApp;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO
class StockAPIServiceTest {
    public static final String url = "https://financialmodelingprep.com/api/v3/stock/real-time-price/aapl";
    @Test // everything works
    void testGetPriceNormalValues() throws IOException {

        RemoteURLReader remoteURLReader = Mockito.mock(RemoteURLReader.class);
        StockAPIService stockAPIService = new StockAPIService(remoteURLReader);
        when(remoteURLReader.readFromUrl(url)).thenReturn("{'price': 289.14}");
        assertEquals(289.14, stockAPIService.getPrice("aapl"));
    }

    @Test // readFromURL threw an exception
    void testGetPriceServerDown() {

    }

    @Test // readFromURL returned wrong JSON
    void testGetPriceMalformedResponse() {

    }

}