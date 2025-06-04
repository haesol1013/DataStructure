import java.util.HashMap;

public class Postfix {
    // 중위 표기식과 후위 표기식을 저장하는 변수 선언
    private String infix;
    private StringBuilder postfix;
    // 연산자 우선순위를 저장하는 HashMap 선언
    private HashMap<Character, Integer> priority = new HashMap<>(6);

    // Postfix의 생성자
    public Postfix(String string) {
        // 중위 표기식 저장 및 후위 표기식 StringBuilder 초기화
        infix = string;
        postfix = new StringBuilder();

        // 연산자 우선순위 설정
        priority.put('^', 2);
        priority.put('%', 1);
        priority.put('*', 1);
        priority.put('/', 1);
        priority.put('+', 0);
        priority.put('-', 0);
        // 중위 표기식을 후위 표기식으로 변환
        infixToPostfix();
    }

    // 중위 표기식을 후위 표기식으로 변환하는 메서드
    private void infixToPostfix() {
        // 연산자를 저장할 스택 생성
        ArrayStack<Character> stack = new ArrayStack<>();
        // 중위 표기식의 각 문자에 대해 처리
        for (Character s: this.infix.toCharArray()) {
            // 숫자인 경우 바로 출력
            if (Character.isDigit(s)) {
                this.postfix.append(s);
                // 여는 괄호인 경우 스택에 푸시
            } else if (s.equals('(')) {
                stack.push(s);
                // 닫는 괄호인 경우 여는 괄호를 만날 때까지 스택에서 팝
            } else if (s.equals(')')) {
                while (!stack.peek().equals('(')) {
                    postfix.append(stack.pop());
                }
                stack.pop();
                // 연산자인 경우 우선순위에 따라 처리
            } else {
                while (!stack.isEmpty() && !stack.peek().equals('(') && hasHigherOrEqualPrecedence(s, stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(s);
            }
        }
        // 스택에 남아있는 연산자 모두 출력
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
    }

    // 연산자 우선순위 비교 메서드
    private boolean hasHigherOrEqualPrecedence(Character curr, Character top) {
        // 지수 연산자(^)의 경우 오른쪽에서 왼쪽으로 결합
        if (curr.equals('^')) {
            return this.priority.get(curr) < this.priority.get(top);
            // 다른 연산자는 왼쪽에서 오른쪽으로 결합
        } else {
            return this.priority.get(curr) <= this.priority.get(top);
        }
    }

    // 후위 표기식을 계산하는 메서드
    public double calculate() {
        // 피연산자를 저장할 스택 생성
        ArrayStack<Double> stack = new ArrayStack<>();
        // 후위 표기식의 각 문자에 대해 처리
        for (Character s: this.postfix.toString().toCharArray()) {
            // 숫자인 경우 스택에 푸시
            if (Character.isDigit(s)) {
                stack.push((double) Character.getNumericValue(s));
                // 연산자인 경우 피연산자 두 개를 스택에서 팝하여 계산
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                // 연산자에 따라 계산 후 결과를 스택에 푸시
                switch (s) {
                    case '^' -> stack.push(Math.pow(operand1, operand2));
                    case '%' -> stack.push(operand1 % operand2);
                    case '*' -> stack.push(operand1 * operand2);
                    case '/' -> stack.push(operand1 / operand2);
                    case '+' -> stack.push(operand1 + operand2);
                    case '-' -> stack.push(operand1 - operand2);
                }
            }
        }
        // 계산 결과 반환
        return stack.pop();
    }

    // 중위 표기식을 문자열 형태로 반환
    public String getInfix() {
        return infix;
    }

    // 후위 표기식을 문자열 형태로 반환
    public String getPostfix() {
        return String.valueOf(postfix);
    }
}