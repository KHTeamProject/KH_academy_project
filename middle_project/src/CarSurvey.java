import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CarSurvey { // 설문 및 아이디 비번
    Scanner sc = new Scanner(System.in);

    public void Survey(Connection conn) {

        // id 기본값 생성
        String id = "";

        while (true) {
            // id 입력
            System.out.print("ID를 입력해 주세요 > ");
            id = sc.nextLine();

            // 비밀번호 입력
            System.out.print("비밀번호를 입력해 주세요 > ");
            String pw = sc.nextLine();

            // 중복 확인
            // id가 중복 -> true 중복 X -> false
            if (checkDuplicate(id, pw, conn) == false) {
                break;
            }
            System.out.println("이미 존재하는 id입니다.\n다른 id를 입력해주세요\n");
        }

        // 설문 진행
        printQuestion(conn, id);

    }

    public boolean checkDuplicate(String id, String pw, Connection conn) {
        try {
            // 쿼리문 작성
            String query = "INSERT INTO users VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // 데이터 바인딩
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            // 데이터 INSERT
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            return true;
        }
        return false;
    }

    public void printQuestion(Connection conn, String id) {
        try {
            Statement stmt = conn.createStatement();

            // INSERT문을 위한 PreparedStatement 생성
            PreparedStatement pstmt = null;

            // SELECT 쿼리문 작성
            String query = "SELECT QUESTION FROM question";
            ResultSet rs = stmt.executeQuery(query);

            // 문항 출력
            int count = 0;
            while (rs.next()) {
                count++;
                String answer = "";
                while (true) {
                    System.out.println(rs.getString("QUESTION"));

                    // 답항 출력
                    printAnswerList(conn);

                    // 설문자의 답 입력
                    System.out.print("답) ");
                    answer = sc.nextLine();

                    // 답을 String으로 입력받았으나 비교를 쉽게 하기 위해 integer로 바꾼후 답항 범위를 벗어나면 다시 입력하도록 함
                    if(Integer.parseInt(answer) <1 || Integer.parseInt(answer) >5){
                        System.out.println("없는 답변 입니다. 다시 입력해주세요");
                        continue;
                    }
                    break;
                }

                // INSERT 쿼리문 작성
                query = "INSERT INTO users_answer VALUES(?,?,?)";
                pstmt = conn.prepareStatement(query);

                // 데이터의 참조 무결성을 지키기 위해 기존 데이터에 있던 값으로 치환
                String queid = "Q" + count;
                String ansid = "A" + answer;

                // 데이터 바인딩
                pstmt.setString(1, id);
                pstmt.setString(2, queid);
                pstmt.setString(3, ansid);

                // 데이터 INSERT
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printAnswerList(Connection conn) {
        try {
            // printQuestion에 있는 stmt를 여기서 재사용하면 다음 문항이 출력 되지 않음
            // 따라서 새로운 Statement를 생성
            Statement stmt4ans = conn.createStatement();

            // 쿼리문 작성
            String query = "SELECT ANSWER FROM answer";
            ResultSet resultSet = stmt4ans.executeQuery(query);

            // 답항 출력
            while (resultSet.next()) {
                System.out.print(resultSet.getString("ANSWER") + " ");
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
