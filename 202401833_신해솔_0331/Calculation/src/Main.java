import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(":: 프로그램을 시작합니다. ::");
        System.out.println("[ 수식 입력을 시작합니다. ]");
        System.out.println("[ Infix 를 postfix 로 ]");

        String string;
        while (true) {
            System.out.print("> 수식을 입력하세요 : ");
            string = scanner.nextLine();
            if ("!".equals(string)) {
                break;
            }

            Postfix postfix = new Postfix(string);

            System.out.println("입력 : " + postfix.getInfix());
            System.out.println("postfix : " + postfix.getPostfix());

            System.out.println("계산 결과 : " + postfix.calculate() + "\n");
        }
        System.out.println("[ 수식 입력을 종료합니다. ]");
        System.out.println(":: 프로그램을 종료합니다. ::");
    }
}
