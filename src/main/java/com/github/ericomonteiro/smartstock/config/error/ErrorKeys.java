package com.github.ericomonteiro.smartstock.config.error;

public class ErrorKeys {

    public static class General {
        public static final String ACCESS_DENIED = "general-401";
        public static final String NOT_FOUND = "general-404";
        public static final String INTERNAL_SERVER_ERROR = "general-500";
    }

    public static class Product {
        public static final String NAME_NOT_BLANK = "product-1";
        public static final String DETAIL_NOT_BLANK = "product-2";
        public static final String PRICE_MUST_GREATER_ZERO = "product-3";
        public static final String PRICE_NOT_NULL = "product-4";
    }

}
