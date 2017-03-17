package com.example.lzy3qy.shopping.data;

import android.provider.BaseColumns;

/**
 * Created by LZY3QY on 3/17/17.
 */

public class ProductListContract {

    public static final class ProdlistEntry implements BaseColumns {
        public static final String TABLE_NAME = "prodList";
        public static final String COLUMN_PRODUCT_NAME = "productName";
        public static final String COLUMN_PRODUCT_NUMBER = "productNumber";
        public static final String COLUMN_URL = "URL";
    }

}