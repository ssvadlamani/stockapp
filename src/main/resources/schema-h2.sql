CREATE TABLE STOCK
        (
        id NUMBER(10) NOT NULL,
        name VARCHAR2(50) NOT NULL,
        current_price decimal(10,2) NOT NULL,
        last_update TIMESTAMP DEFAULT NULL,
        PRIMARY KEY(id)
        );