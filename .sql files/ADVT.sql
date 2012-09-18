CREATE TABLE CSETEAM61.ADVERTISEMENTS(
ADVTID VARCHAR(10) NOT NULL,
ADVTTYPE VARCHAR(20) NOT NULL,
DATENTIME DATE,
COMPANY VARCHAR(20) NOT NULL,
ITEMNAME VARCHAR(20) NOT NULL,
UNITPRICE DOUBLE,
NOOFAVAILUNIT INTEGER,
PRIMARY KEY (ADVTID),
UNIQUE(ADVTID, COMPANY, ITEMNAME)
);