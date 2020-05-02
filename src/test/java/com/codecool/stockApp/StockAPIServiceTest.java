package com.codecool.stockApp;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO
class StockAPIServiceTest {
    public static final String URL = "https://financialmodelingprep.com/api/v3/stock/real-time-price/aapl";
    RemoteURLReader remoteURLReader = Mockito.mock(RemoteURLReader.class);
    StockAPIService stockAPIService = new StockAPIService(remoteURLReader);

    @Test
        // everything works
    void testGetPriceNormalValues() throws IOException {
        when(remoteURLReader.readFromUrl(URL)).thenReturn("{'price': 289.14}");
        assertEquals(289.14, stockAPIService.getPrice("aapl"));
    }

    @Test
        // readFromURL threw an exception
    void testGetPriceServerDown() throws IOException {
        when(remoteURLReader.readFromUrl(URL)).thenThrow(new IOException());
        assertThrows(IOException.class, () -> stockAPIService.getPrice("aapl"));

    }

    @Test
        // readFromURL returned wrong JSON
    void testGetPriceMalformedResponse() throws IOException {
        when(remoteURLReader.readFromUrl(URL)).thenReturn("{}");
        assertThrows(JSONException.class, () -> stockAPIService.getPrice("aapl"));
    }

}