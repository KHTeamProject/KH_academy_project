import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CarSurvey { // 설문 및 아이디 비번
    static Scanner sc = new Scanner(System.in);

    public void Survey(Connection conn) {

        // query문 기본값 생성
        String query = "";

        // id 기본값 생성
        String id = "";

        while (true) {
            // id입력받고 중복 확인
            System.out.print("ID를 입력해 주세요 > ");
            id = sc.nextLine();

            System.out.print("비밀번호를 입력해 주세요 > ");
            String pw = sc.nextLine();
            // id가 중복 -> true 중복 X -> false
            if (checkDuplicate(id, pw, conn) == false) {
                break;
            }
            System.out.println("이미 존재하는 id입니다.\n다른 id를 입력해주세요\n");
        }

        // 설문 진행
        printQuestion(conn, id);

    }

    public static boolean checkDuplicate(String id, String pw, Connection conn) {
        try {
            String query = "INSERT INTO users VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // 데이터 바인딩
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            int count = pstmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            return true;
        }
        return false;
    }

    public static void printQuestion(Connection conn, String id) {
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = null;
            String query = "SELECT QUESTION FROM question";
            ResultSet rs = stmt.executeQuery(query);
            // 문항 출력
            int count = 0;
            while (rs.next()) {
                count++;
                String answer = "";
                while (true) {

                    System.out.println(rs.getString("QUESTION"));
                    printAnswerList(conn);
                    System.out.print("답) ");
                    answer = sc.nextLine();
                    if(Integer.parseInt(answer) <1 || Integer.parseInt(answer) >5){
                        System.out.println("없는 답변 입니다. 다시 입력해주세요");
                        continue;
                    }
                    break;
                }
                query = "INSERT INTO users_answer VALUES(?,?,?)";
                pstmt = conn.prepareStatement(query);
                String queid = "Q" + count;
                String ansid = "A" + answer;

                // 데이터 바인딩
                pstmt.setString(1, id);
                pstmt.setString(2, queid);
                pstmt.setString(3, ansid);

                int qcount = pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printAnswerList(Connection conn) {
        try {
            Statement stmt4ans = conn.createStatement();
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
