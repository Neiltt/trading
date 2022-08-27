package com.uuu.trading.sto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

// @Data：get set @AllArgsConstructor:預設建構值
@Data
@AllArgsConstructor
public class FangIdExceptionResponse {
    // 回傳錯誤字串
    private String workoutIdentifier;
}
