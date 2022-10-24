import java.util.Scanner;

public class CarStats { //통계 김용범 
    public void stats(){
        Scanner sc2 = new Scanner(System.in);
        String answer;
        while(true){
            System.out.println("1. 설문자별 답변 결과 | 2. 질문별 총 답변수 | 3. 상위메뉴로 이동");
            System.out.println("선택) ");
            answer = sc2.next();
            if (answer.equals("1") ) {
                
                //아래는 예시
                System.out.println("<ID별 답변 결과>");
                System.out.println();
                System.out.println("ID)             질문(1)     질문(2)     질문(3)     질문(4)     질문(5)     질문(6)");
                System.out.println("USER ID(1)       3           2           4           1              5          3");
                System.out.println("USER ID(2)       1           2           4           3              5          3");
                System.out.println("USER ID(3)       2           1           4           3              5          3");
                System.out.println("USER ID(4)       4           3           1           2              5          3");
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
