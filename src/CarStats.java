import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CarStats { //통계 김용범
    public void stats(Connection connection) {
        Scanner sc = new Scanner(System.in);
        String answer;
        while(true){ // 메뉴
            System.out.println("1. ID별 답변 결과 | 2. 질문별 총 답변수 | 3. 상위메뉴로 이동");
            System.out.println("선택) ");
            answer = sc.next();
            if (answer.equals("1") ) {
                // 1. ID별 답변 결과
                answerById(connection);
            } else if (answer.equals("2")) {
                // 2. 질문별 총 답변수
                answerStatistics(connection);
            } else if (answer.equals("3")) {
                break;
            }
        }
    }

    //id별 답변결과
    public void answerById(Connection connection){
        
        //질문 숫자 세기. 질문 숫자가 6개 이상으로 가변적일 경우를 대비해서 숫자를 세서 하기로 하였습니다.
        ResultSet resultSet_QuestionCount;
        int questionCount = 0;
        try {
            Statement statement2 = connection.createStatement();
            resultSet_QuestionCount = statement2.executeQuery("SELECT COUNT(QUESTION_ID) FROM question;");
            while(resultSet_QuestionCount.next()) {
                questionCount = Integer.parseInt(resultSet_QuestionCount.getString("COUNT(QUESTION_ID)"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
        //ID별 답변결과 상단 출력
        System.out.println("<ID별 답변 결과>");
        System.out.printf("%-10s","ID)");
        
        //질문수 만큼 질문1, 질문2... 상단에 출력하기
        int i = 0; 
        while(i<questionCount){
            System.out.printf("%-10s","질문("+(i+1)+")");
            i++;
        }
        System.out.println();
        
        try {
            // user 목록 조회하기
            String query = "SELECT USER_ID FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);

            // user별로 답변내용 조회준비
            String query_answer = "SELECT ANSWER_ID FROM users_answer WHERE USER_ID = ? ORDER BY QUESTION_ID";
            PreparedStatement pstmt = connection.prepareStatement(query_answer);
            
            // 반복문을 통해서 id를 한줄에 하나씩 출력하면서 동시에 답변한 내용도 조회해서 옆에 같이 표시하기
            while(resultSet.next()) { // user id를 하나씩 프린트하기
                String userId = resultSet.getString("USER_ID");
                pstmt.setString(1,userId);     
                ResultSet resultSet2 = pstmt.executeQuery();
                
                System.out.printf("%-10s",userId);
                while(resultSet2.next()){ //user가 정해지면 그 user가 답변한 내용을 뽑아내서 보여준다
                    String answerId = resultSet2.getString("ANSWER_ID");
                    int answer2 = 0;
                    switch (answerId) { // 답변내용
                        case "A1":
                            answer2 = 1;
                            break;
                        case "A2":
                            answer2 = 2;
                            break;
                        case "A3":
                            answer2 = 3;
                            break;
                        case "A4":
                            answer2 = 4;
                            break;
                        case "A5":
                            answer2 = 5;
                            break;
                        default:
                            break;
                    }
                    String format2 = "%-12d";
                    System.out.printf(format2,answer2);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        System.out.println();
    }    

    //질문별 총 답변수
    public void answerStatistics(Connection connection){
        System.out.println("<질문별 총 답변수>");
        String format = "%-10s%-10s%-10s%-10s%-10s%-10s%n";
        String format2 = "%-8s%-11s%-11s%-11s%-11s%-11s%n";
        System.out.printf(format,"Question)","답(1)","답(2)","답(3)","답(4)","답(5)");

        //질문별로 답변한 답항갯수의 합을 구하는 쿼리문
        //쿼리 결과는 Q1..Q6 각 질문에 대해 다음과 같이 한줄로 나온다.
        // A1   A2  A3  A4  A5
        // 2    7   0   1   3
        String query = "SELECT * "+
                        "FROM (SELECT COUNT(ANSWER_ID) AS A1 FROM users_answer "+
                            "WHERE QUESTION_ID=? AND ANSWER_ID = 'A1') AS A1"+
                        "INNER JOIN (SELECT COUNT(ANSWER_ID) AS A2 FROM users_answer "+
                                    "WHERE QUESTION_ID=? AND ANSWER_ID = 'A2') AS A2 "+
                        "INNER JOIN (SELECT COUNT(ANSWER_ID) AS A3 FROM users_answer "+
                                    "WHERE QUESTION_ID=? AND ANSWER_ID = 'A3') AS A3 "+
                        "INNER JOIN (SELECT COUNT(ANSWER_ID) AS A4 FROM users_answer "+
                                    "WHERE QUESTION_ID=? AND ANSWER_ID = 'A4') AS A4 "+
                        "INNER JOIN (SELECT COUNT(ANSWER_ID) AS A5 FROM users_answer "+
                                    "WHERE QUESTION_ID=? AND ANSWER_ID = 'A5') AS A5";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet;
            
            //질문 숫자 세기
            Statement statement = connection.createStatement();
            ResultSet resultSet_QuestionCount = statement.executeQuery("SELECT COUNT(QUESTION_ID) FROM question;");
            int questionCount = 0;
            while(resultSet_QuestionCount.next()) {
                questionCount = Integer.parseInt(resultSet_QuestionCount.getString("COUNT(QUESTION_ID)"));
            }

            for (int i = 0;i<questionCount;i++) { //Q1..Qn를 돌면서 각 질문에 대한 통계를 불러온다.
                String question = "Q"+(i+1);
                preparedStatement.setString(1,question); //예시, 이번쿼리는 Q1,Q2.... 등 으로 설정한다.
                preparedStatement.setString(2,question); 
                preparedStatement.setString(3,question);
                preparedStatement.setString(4,question);
                preparedStatement.setString(5,question);

                resultSet = preparedStatement.executeQuery(); 

                resultSet.next();
                int countA1,countA2,countA3,countA4,countA5; // 답변한 갯수의 합들 담는 변수
                countA1 = resultSet.getInt("A1");
                countA2 = resultSet.getInt("A2");
                countA3 = resultSet.getInt("A3");
                countA4 = resultSet.getInt("A4");
                countA5 = resultSet.getInt("A5");
                System.out.printf(format2,"질문"+(i+1),countA1,countA2,countA3,countA4,countA5); //각문항에 답변한 갯수 합 출력
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
