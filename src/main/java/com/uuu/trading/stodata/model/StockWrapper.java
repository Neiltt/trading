package com.uuu.trading.stodata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;

@Getter
@With // StockWrapper若相同返回this,不同則new新對象
@AllArgsConstructor // StockWrapper all參數返回this
public class StockWrapper {
    private final Stock stock;
    private final LocalDateTime lastAccessed;

    public StockWrapper(final Stock stock) {
        this.stock = stock;
        lastAccessed = LocalDateTime.now();
    }
}
