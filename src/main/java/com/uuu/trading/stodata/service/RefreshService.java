package com.uuu.trading.stodata.service;

import com.uuu.trading.stodata.model.StockWrapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class RefreshService {

    private final Map<StockWrapper, Boolean> stocksToRefresh;
    // ScheduledExecutorService介面是基於執行緒池設計的定時任務類,每個排程任務都會分配到執行緒池中的一個執行緒去執行,也就是說,任務是併發執行,互不影響。
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final Duration refreshPeriod = Duration.ofSeconds(15);

    public RefreshService() {
        stocksToRefresh = new HashMap<>();
        setRefreshEvery15Minutes();
    }

    public boolean shouldRefresh(final StockWrapper stock) {
        if (!stocksToRefresh.containsKey(stock)) {
            stocksToRefresh.put(stock, false);
            return true;
        }
        return stocksToRefresh.get(stock);
    }

    private void setRefreshEvery15Minutes() {
        scheduler.scheduleAtFixedRate(() ->
                stocksToRefresh.forEach((stock, value) -> {
                    if (stock.getLastAccessed().isBefore(LocalDateTime.now().minus(refreshPeriod))) {
                        System.out.println("Setting should refresh" + stock.getStock().getSymbol());
                        stocksToRefresh.remove(stock);
                        stocksToRefresh.put(stock.withLastAccessed(LocalDateTime.now()), true);
                    }
                }), 0, 15, SECONDS);

    }
}
