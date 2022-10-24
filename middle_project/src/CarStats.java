import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class CarStats { //통계 김용범 
    public void stats(Connection conn) {
        Scanner sc = new Scanner(System.in);
        String answer;
        while(true){
            System.out.println("1. ID별 답변 결과 | 2. 질문별 총 답변수 | 3. 상위메뉴로 이동");
            System.out.println("선택) ");
            answer = sc.next();
            if (answer.equals("1") ) {
                
                //아래는 예시
                System.out.println("<ID별 답변 결과>");
                String format = "%-10s%-10s%-10s%-10s%-10s%-10s%-10s%n";
                System.out.printf(format,"ID)","질문(1)","질문(2)","질문(3)","질문(4)","질문(5)","질문(6)");
                
                try {
                    String query = "SELECT USER_ID FROM users";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery(query);

                    String query_answer = "SELECT ANSWER_ID FROM users_answer WHERE USER_ID = ? ORDER BY QUESTION_ID";
                    PreparedStatement pstmt = conn.prepareStatement(query_answer);
                    
                    // 반복문을 통해서 id조회한 값들을 프린트 하면서 동시에 답변한 내용도 조회해서 옆에 같이 표시하기
                    while(resultSet.next()) {
                        String userId = resultSet.getString("USER_ID");
                        pstmt.setString(1,userId);     
                        ResultSet resultSet2 = pstmt.executeQuery();
                            
                        
                        System.out.printf("%-10s",userId);
                        while(resultSet2.next()){
                            String answerId = resultSet2.getString("ANSWER_ID");
                            int answer2 = 0;
                            switch (answerId) {
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
                                case "A6":
                                    answer2 = 6;
                                    break;                                    
                                default:
                                    break;
                            }
                            // System.out.print("             "+answer2);
                            String format2 = "%-12d";
                            System.out.printf(format2,answer2);
                        }
                        System.out.println();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }  
                System.out.println();
            } else if (answer.equals("2")) {
                
                //아래는 예시
                System.out.println("<질문별 총 답변수>");
                System.out.println();
                System.out.println("ID)             답(1)       답(2)       답(3)       답(4)   답(5)   답(6)");
                System.out.println("질문(1)       3           2           4           1      3         3");
                System.out.println("질문(2)       1           2           4           3      2         2");
                System.out.println("질문(3)       2           1           4           3      3         1");
                System.out.println("질문(4)       4           3           1           2      3         2");
            } else if (answer.equals("3")) {
                break;
            }
        }
        
    }
}
