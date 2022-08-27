package com.uuu.trading.sto.service;

import com.uuu.trading.sto.exceptions.FangIdException;
import com.uuu.trading.sto.model.Fang;
import com.uuu.trading.sto.repository.FangRepository;
import com.uuu.trading.stodata.model.StockWrapper;
import com.uuu.trading.stodata.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public class FangService {
    @Autowired
    private FangRepository repository;
    @Autowired
    private StockService stockService;

    public Fang savaOrUpdateFang(Fang w) {
        try {
            w.setSymbol(w.getSymbol().toUpperCase(Locale.ROOT));
            w.setDateAcquired(new Date());
            return repository.save(w);
        } catch (Exception e) {
            throw new FangIdException("fangIdentifier" + w.getSymbol() + " already exists");
        }
    }

    public Fang findFangById(Fang f) {
        Optional<Fang> fangOptional = repository.findById(f.getId());
        f.setCost(fangOptional.get().getCost());
        f.setMarketValue(fangOptional.get().getMarketValue());
        f.setGainLoss(fangOptional.get().getGainLoss());
        f.setGainLossPercent(fangOptional.get().getGainLossPercent());
        f.setDateAcquired(fangOptional.get().getDateAcquired());
        f.setDateUpdate(fangOptional.get().getDateUpdate());
        //f = fangOptional.get();
        return f;
    }

    // dump data
    public void dumpDB() {
        log.info("傾印所有的資料, 資料共有{}筆", repository.count());
        for (Fang b : repository.findAll()) {
            log.info("id:{},Symbol:{}, UnitCost:{}", b.getId(), b.getSymbol(), b.getUnitCost());
        }
    }

    // 即時更新資料庫
    public void calCost() {
        try {
            log.info("重新計算成本, 資料共有{}筆", repository.count());
            for (Fang f : repository.findAll()) {
                BigDecimal qty = new BigDecimal(f.getQty());
                BigDecimal unitCost = new BigDecimal(f.getUnitCost());
                BigDecimal cost = unitCost.multiply(qty);
                // 取得股票現價
                BigDecimal unitMarketValue = getUnitMarketValue(f.getSymbol());
                BigDecimal marketValue = unitMarketValue.multiply(qty);
                // 計算現值損益
                BigDecimal gainLoss = marketValue.subtract(cost);
                BigDecimal gainLossPercent = gainLoss.divide(cost, 3, BigDecimal.ROUND_HALF_UP);
                // set value
                f.setCost(String.valueOf(cost));
                f.setMarketValue(String.valueOf(marketValue));
                f.setGainLoss(String.valueOf(gainLoss));
                f.setGainLossPercent(String.valueOf(gainLossPercent));
                f.setDateUpdate(new Date());
                repository.save(f);
            }
        } catch (Exception e) {
            throw new FangIdException("cal Cost Error");
        }
    }

    // 獲取現價
    public BigDecimal getUnitMarketValue(String ticker) throws IOException {
        log.info("取得股票:" + ticker);
        final StockWrapper stock = stockService.findStock(ticker);
        final BigDecimal price = stockService.findPrice(stock);
        log.info("取得股價:" + price);
        return price;
    }
}
