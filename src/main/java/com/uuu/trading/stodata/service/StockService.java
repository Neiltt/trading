package com.uuu.trading.stodata.service;

import com.uuu.trading.stodata.model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StockService {

    private final RefreshService refreshService;

    public StockWrapper findStock(final String ticker) {
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (IOException e) {
            System.out.println("YahooFinance get stock error");
        }
        return null;
    }

    public BigDecimal findPrice(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(true).getPrice();
    }

    public List<StockWrapper> findStocks(final List<String> tickers) {
        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public BigDecimal findLastChangePercent(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeInPercent();
    }

    public BigDecimal findChangeFrom200MeanPercent(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeFromAvg200InPercent();
    }
}
