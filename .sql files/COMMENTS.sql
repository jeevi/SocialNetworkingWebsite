CREATE TABLE CSETEAM61.COMMENTS(
COMMENTID VARCHAR(10) NOT NULL,
DATENTIME DATE,
CONTENT VARCHAR(255),
AUTHOR VARCHAR(20),
POSTID VARCHAR(20),
PRIMARY KEY(COMMENTID),
FOREIGN KEY(POSTID)REFERENCES CSETEAM61.POSTS(POSTID) ON DELETE SET NULL ON UPDATE NO ACTION,
FOREIGN KEY(AUTHOR)REFERENCES CSETEAM61.USERS(USERID) ON DELETE SET NULL ON UPDATE NO ACTION
);