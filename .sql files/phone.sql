CREATE TABLE CSETEAM61.PHONENUMBER(
USERID VARCHAR(20),
PHONETYPE VARCHAR(10),
PHONENO VARCHAR(15),
FOREIGN KEY(USERID) REFERENCES CSETEAM61.USERS(USERID)
);