package com.example.product;

import javax.persistence.*;
import java.util.*;

@Entity
public class Product {

    @Id @GeneratedValue
    private Long productId;

    @Convert(converter = ProductProperty.ProductPropertyConverter.class)
    private ProductProperty productProperty;

    @Convert(converter = ProductPrices.ProductPricesConverter.class)
    private List<ProductPrices> productPrices;
}