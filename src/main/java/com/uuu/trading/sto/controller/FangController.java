package com.uuu.trading.sto.controller;

import com.uuu.spring.fitness.MapValidationError;
import com.uuu.trading.sto.model.Fang;
import com.uuu.trading.sto.service.FangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/fang")
public class FangController {
    @Autowired
    private FangService fangService;

    // @RequestBody:請求參數 @Validated：驗證model參數與BindingResult合用
    @PostMapping("")
    public ResponseEntity<?> createNewFang(@Valid @RequestBody Fang fang,
                                           BindingResult bindingResult) {
        // 異常處理共用化
        ResponseEntity<Map<String, String>> errorMap = MapValidationError.mapValidation(bindingResult);
        if (errorMap != null) {
            return errorMap;
        }
        // 回應實體
        Fang w1 = fangService.savaOrUpdateFang(fang);
        return new ResponseEntity(w1, HttpStatus.CREATED);
    }

    @GetMapping("/dumpDB")
    public void dumpDB() {
        fangService.dumpDB();
    }

    // 計算成本
    @GetMapping("/calCost")
    private void calCost() {
        log.info("計算並更新成本");
        fangService.calCost();
    }

}
