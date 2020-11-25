学习笔记

### 1、基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交DDL的SQL文件到Github

用户数据库(mc_customerdb)

- customer_inf
- customer_login
- customer_level_inf
- customer_login_log
- customer_point_log
- customer_balance_log

商品数据库(mc_productdb)

- product_info
- product_pic_info
- product_category
- product_supplier_info
- product_comment
- product_brand_info

订单数据库(mc_orderdb)

- order_master
- order_detail
- order_customer_addr
- order_cart
- shipping_info
- warehouse_info
- warehouse_product