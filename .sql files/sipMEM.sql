CREATE TABLE CSETEAM61.SIPMEMBER(
SIPID VARCHAR(20),
USERID VARCHAR(20),
FOREIGN KEY(SIPID)REFERENCES CSETEAM61.SIPAGES(SIPID) ON DELETE SET NULL ON UPDATE NO ACTION,
FOREIGN KEY(USERID)REFERENCES CSETEAM61.USERS(USERID) ON DELETE SET NULL ON UPDATE NO ACTION
);