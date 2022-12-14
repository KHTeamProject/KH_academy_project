
CREATE TABLE ANSWER
(
  ANSWER_ID VARCHAR(200) NOT NULL,
  ANSWER    VARCHAR(200) NULL    ,
  PRIMARY KEY (ANSWER_ID)
) COMMENT '답항';

CREATE TABLE QUESTION
(
  QUESTION_ID VARCHAR(200) NOT NULL,
  QUESTION    VARCHAR(200) NULL    ,
  PRIMARY KEY (QUESTION_ID)
) COMMENT '질문';

CREATE TABLE SURVEY
(
  QUESTION_ID VARCHAR(200) NOT NULL,
  ANSWER_ID   VARCHAR(200) NOT NULL
) COMMENT '설문지';

CREATE TABLE USERS
(
  USER_ID VARCHAR(200) NOT NULL,
  USER_PW VARCHAR(200) NULL    ,
  PRIMARY KEY (USER_ID)
) COMMENT '유저';

CREATE TABLE USERS_ANSWER
(
  USER_ID     VARCHAR(200) NOT NULL,
  QUESTION_ID VARCHAR(200) NOT NULL,
  ANSWER_ID   VARCHAR(200) NOT NULL
) COMMENT '유저답변';

ALTER TABLE USERS_ANSWER
  ADD CONSTRAINT FK_USERS_TO_USERS_ANSWER
    FOREIGN KEY (USER_ID)
    REFERENCES USERS (USER_ID);

ALTER TABLE SURVEY
  ADD CONSTRAINT FK_QUESTION_TO_SURVEY
    FOREIGN KEY (QUESTION_ID)
    REFERENCES QUESTION (QUESTION_ID);

ALTER TABLE USERS_ANSWER
  ADD CONSTRAINT FK_QUESTION_TO_USERS_ANSWER
    FOREIGN KEY (QUESTION_ID)
    REFERENCES QUESTION (QUESTION_ID);

ALTER TABLE SURVEY
  ADD CONSTRAINT FK_ANSWER_TO_SURVEY
    FOREIGN KEY (ANSWER_ID)
    REFERENCES ANSWER (ANSWER_ID);

ALTER TABLE USERS_ANSWER
  ADD CONSTRAINT FK_ANSWER_TO_USERS_ANSWER
    FOREIGN KEY (ANSWER_ID)
    REFERENCES ANSWER (ANSWER_ID);
