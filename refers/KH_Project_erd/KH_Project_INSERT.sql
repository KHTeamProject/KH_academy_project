INSERT INTO question(QUESTION,QUESTION_ID)
VALUES('1. 현대 자동차의 브랜드 이미지에 만족하십니까? - 답은 한가지만 선택가능.','Q1');

INSERT INTO question(QUESTION,QUESTION_ID)
VALUES('2. 현대 차량의 주행성능에 만족하십니까? - 답은 한가지만 선택가능.','Q2');

INSERT INTO question(QUESTION,QUESTION_ID)
VALUES('3. 현대 차량의 가격에 만족하십니까? - 답은 한가지만 선택가능.','Q3');

INSERT INTO question(QUESTION,QUESTION_ID)
VALUES('4. 현대 차량의 디자인에 만족하십니까? - 답은 한가지만 선택가능.','Q4');

INSERT INTO question(QUESTION,QUESTION_ID)
VALUES('5. 현대 차량의 내구성에 만족하십니까? - 답은 한가지만 선택가능.','Q5');

INSERT INTO question(QUESTION,QUESTION_ID)
VALUES('6. 현대 직원의 응대에 만족하십니까? - 답은 한가지만 선택가능.','Q6');

INSERT INTO answer(ANSWER,ANSWER_ID)
VALUES('(1)매우만족','A1');

INSERT INTO answer(ANSWER,ANSWER_ID)
VALUES('(2)만족','A2');

INSERT INTO answer(ANSWER,ANSWER_ID)
VALUES('(3)보통','A3');

INSERT INTO answer(ANSWER,ANSWER_ID)
VALUES('(4)불만족','A4');

INSERT INTO answer(ANSWER,ANSWER_ID)
VALUES('(5)매우불만족','A5');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q1','A1');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q1','A2');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q1','A3');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q1','A4');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q1','A5');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q2','A1');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q2','A2');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q2','A3');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q2','A4');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q2','A5');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q3','A1');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q3','A2');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q3','A3');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q3','A4');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q3','A5');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q4','A1');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q4','A2');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q4','A3');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q4','A4');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q4','A5');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q5','A1');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q5','A2');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q5','A3');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q5','A4');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q5','A5');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q6','A1');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q6','A2');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q6','A3');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q6','A4');

INSERT INTO survey(QUESTION_ID,ANSWER_ID)
VALUES('Q6','A5');

SELECT * FROM question;

SELECT * FROM answer;

SELECT * FROM survey;