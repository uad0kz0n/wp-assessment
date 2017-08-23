drop table T_OFFER if exists;

create table T_OFFER 
(
PRODUCT_ID bigint identity primary key, 
PRODUCT_NAME varchar(100),
PRODUCT_DESCRIPTION varchar(1000),
PRICE decimal(19,2),
CURRENCY varchar(3)
);
                        
ALTER TABLE T_OFFER ALTER COLUMN PRICE SET DEFAULT 0.00;
