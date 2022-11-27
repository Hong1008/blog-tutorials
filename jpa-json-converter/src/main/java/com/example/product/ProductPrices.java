package com.example.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.converter.JsonArrayConverter;

public class ProductPrices {

    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public static class ProductPricesConverter extends JsonArrayConverter<ProductPrices> {
        public ProductPricesConverter() {
            super(new TypeReference<>() {});
        }
    }
}