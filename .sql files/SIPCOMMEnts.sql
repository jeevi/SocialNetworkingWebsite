CREATE TABLE CSETEAM61.SIPCOMMENTS(
COMMENTID VARCHAR(10) NOT NULL,
DATENTIME DATE,
CONTENT VARCHAR(255),
AUTHOR VARCHAR(20),
SIPOSTID VARCHAR(20),
PRIMARY KEY(COMMENTID),
FOREIGN KEY(SIPOSTID)REFERENCES CSETEAM61.SIPOSTS(SIPOSTID) ON DELETE SET NULL ON UPDATE NO ACTION,
FOREIGN KEY(AUTHOR)REFERENCES CSETEAM61.USERS(USERID) ON DELETE SET NULL ON UPDATE NO ACTION
);