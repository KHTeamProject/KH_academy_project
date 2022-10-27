import java.sql.*;
import java.util.Scanner;

public class CarDelete { // 삭제
    Scanner sc = new Scanner(System.in);
    Statement statement, statement2;
    ResultSet resultSet;

    public void del(Connection conn) {
        System.out.print("ID를 입력해주세요. > ");
        String uId = sc.nextLine();
        System.out.print("비밀번호를 입력해주세요. > ");
        String uPw = sc.nextLine();
        boolean correct = false;

        String query = "SELECT * FROM USERS";

        try {
            statement = conn.createStatement();
            statement2 = conn.createStatement();
            resultSet = statement.executeQuery(query);

            // 불러오기
            String userId = "", userPw = "", deleteQueryUser = "";
            while (resultSet.next()) {
                userId = resultSet.getString("USER_ID");
                userPw = resultSet.getString("USER_PW");
                deleteQueryUser = "DELETE FROM users WHERE USER_ID = " + "'" + userId + "'";
                String deleteQueryUserAnswer = "DELETE FROM users_answer WHERE USER_ID = " + "'" + userId + "'";

                if (uId.equals(userId) && uPw.equals(userPw)) { // 일치 할 때
                    statement2.execute(deleteQueryUserAnswer);
                    statement2.execute(deleteQueryUser);

                    System.out.println("설문이 삭제되었습니다.");
                    correct = true;
                    statement2.close();
                    if(correct == true)
                        break;
                    
                }

            }
            if(correct == false)
                System.out.println("일치하지 않습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}