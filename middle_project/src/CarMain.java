import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class CarMain { // 메인화면
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // database 접속
        String url = "jdbc:mysql://localhost:3306/kh_project";
        String user = "root";
        String password = "*khacademy!";
        // database 지정
        try {
            // Connection connection = DriverManager.getConnection(url, user, password);
            Connection conn = DriverManager.getConnection(url, user, password);

            while (true) {
                System.out.println("-------------------------------------------------------");
                System.out.println("P.설문시작 | S.통계 | D.삭제 | Q.종료 ");
                System.out.println("-------------------------------------------------------");

                System.out.print("선택 > ");
                String choose = sc.next();

                if (choose.equalsIgnoreCase("P")) {
                    CarSurvey cv = new CarSurvey();
                    cv.Survey(conn);
                } else if (choose.equalsIgnoreCase("S")) {
                    CarStats cs = new CarStats();
                    cs.stats();
                } else if (choose.equalsIgnoreCase("D")) {
                    CarDelete cd = new CarDelete();
                    cd.del();
                } else if (choose.equalsIgnoreCase("Q")) {
                    System.out.println("이용해 주셔서 감사합니다!");
                    break;
                } else {
                    System.out.println("없는 메뉴 입니다. 다시 선택해주세요.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
