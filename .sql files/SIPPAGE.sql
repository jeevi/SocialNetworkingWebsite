CREATE TABLE CSETEAM61.SIPTOPAGE(
PAGEID VARCHAR(20) UNIQUE NOT NULL,
SIPID VARCHAR(20) ,
FOREIGN KEY(SIPID)REFERENCES CSETEAM61.SIPAGES(SIPID) ON DELETE SET NULL ON UPDATE NO ACTION
);