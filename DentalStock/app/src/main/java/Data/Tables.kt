package Data

class Tables {

        object ProductTable {

            // Table Products's columns

            const val TABLE_NAME = "products"
            const val COLUMN_ID = "_id"
            const val COLUMN_PRODUCT_NAME = "product_name"
            const val COLUMN_STOCK = "stock"
            const val COLUMN_TYPE = "type"

            // Query to create the Table
            internal const val SQL_CREATE_PODUCTS =
                    "CREATE TABLE $TABLE_NAME (" +
                            "$COLUMN_ID TINYINT PRIMARY KEY," +
                            "$COLUMN_PRODUCT_NAME TEXT," +
                            "$COLUMN_STOCK SMALLINT,"+
                            "$COLUMN_TYPE TEXT)"

            // Query to delete the Table
            internal const val SQL_DELETE_PRODUCTS = "DROP TABLE IF EXISTS $TABLE_NAME"

        }

}