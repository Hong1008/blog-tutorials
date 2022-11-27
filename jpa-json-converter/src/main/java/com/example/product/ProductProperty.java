package com.example.product;

import com.example.converter.JsonConverter;
import java.time.LocalTime;

public class ProductProperty {

    private LocalTime sellingStartTime;
    private LocalTime sellingEndTime;

    private Integer stock;

    public LocalTime getSellingStartTime() {
        return sellingStartTime;
    }

    public void setSellingStartTime(LocalTime sellingStartTime) {
        this.sellingStartTime = sellingStartTime;
    }

    public LocalTime getSellingEndTime() {
        return sellingEndTime;
    }

    public void setSellingEndTime(LocalTime sellingEndTime) {
        this.sellingEndTime = sellingEndTime;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public static class ProductPropertyConverter extends JsonConverter<ProductProperty> {
    }
}