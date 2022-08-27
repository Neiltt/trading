package com.uuu.trading.sto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

// 數據模型層宣告、GETSET、無預設建構值
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor // 方法建構值，提供form使用準備
public class Fang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message="symbol is required")
    private String symbol;
    @NotBlank(message="qty is required")
    private String qty;
    @NotBlank(message="unitCost is required")
    private String unitCost;
    private String cost;
    private String marketValue;
    private String gainLoss;
    private String gainLossPercent;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateAcquired;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateUpdate;
    private String status;

    @PrePersist
    public void onCreate() {
        this.dateAcquired = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.dateUpdate = new Date();
    }

    // 配合頁面新增欄位
    //private String symbols;

}
