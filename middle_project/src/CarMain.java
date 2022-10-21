import java.util.Scanner;

public class CarMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("-------------------------------------------------------");
            System.out.println("P.설문시작 | S.통계 | D.삭제 | Q.종료 ");
            System.out.println("-------------------------------------------------------");

            System.out.print("선택 > ");
            String choose = sc.next();

            if (choose.equalsIgnoreCase("P")) {
                CarView cv = new CarView();
                cv.view();
            } else if (choose.equalsIgnoreCase("S")) {
                CarStats cs = new CarStats();
                cs.stats();
            } else if (choose.equalsIgnoreCase("D")) {
                CarDelete cd = new CarDelete();
                cd.del();
            } else if (choose.equalsIgnoreCase("Q")) {
                System.out.println("이용해 주셔서 감사합니다!");
                break;
            }
        }
        sc.close();
    }
}
