import java.sql.*;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/kh_project";
        String user = "root";
        String password = "*khacademy!";
        String query = "";
        // database 지정
        try {
            
            // Connection connection = DriverManager.getConnection(url, user, password);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();

            //답항 숫자 세기
            ResultSet resultSet_AnswerCount = statement.executeQuery("SELECT COUNT(ANSWER_ID) FROM answer;");
            int answerCount = 0;
            while(resultSet_AnswerCount.next()) {
                answerCount = Integer.parseInt(resultSet_AnswerCount.getString("COUNT(ANSWER_ID)"));
                System.out.println(answerCount);
            }

            //질문 숫자 세기
            ResultSet resultSet_QuestionCount = statement.executeQuery("SELECT COUNT(QUESTION_ID) FROM question;");
            int questionCount = 0;
            while(resultSet_QuestionCount.next()) {
                questionCount = Integer.parseInt(resultSet_QuestionCount.getString("COUNT(QUESTION_ID)"));
                System.out.println(questionCount);
            }
            
            String[][] result = new String[questionCount+1][answerCount+1];
            // 배열 채우기
            for(int i = 0 ; i<questionCount+1 ; i++) {
                for(int j =0 ; j<answerCount+1 ; j++) {
                    if(i==0 && j != 0) {
                        result[i][j]= "\t답("+j+")";
                    } else if(j==0 && i != 0) {
                        result[i][j]= "질문("+i+")";
                    } else {
                        result[i][j]=" ";
                    }
                    
                }
            }

            //갯수
            for(int i = 1 ; i<=questionCount ; i++) {
                for(int j =1 ; j<=answerCount ; j++) {  
                    query = "SELECT COUNT(ANSWER_ID) FROM users_answer WHERE QUESTION_ID = "+"'"+"Q"+i+"'"+" AND ANSWER_ID = "+"'"+"A"+j+"'"+";";
                    System.out.println(query);
                    ResultSet resultSet = statement.executeQuery(query);
                    String answer = " ";
                    while(resultSet.next()) {
                        System.out.println(resultSet.getString("COUNT(ANSWER_ID)"));
                        answer = resultSet.getString("COUNT(ANSWER_ID)");
                    }
                    result[i][j] = "\t"+answer;

                }
           
            }

            for(int i = 0 ; i<questionCount+1 ; i++) {
                for(int j =0 ; j<answerCount+1 ; j++) {
                    System.out.print(result[i][j]);
                }
                System.out.println();
            }
            

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
}
