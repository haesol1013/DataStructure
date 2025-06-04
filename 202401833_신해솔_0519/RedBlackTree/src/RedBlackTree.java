import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {
        RedBlackTree<Integer, String> rbt = new RedBlackTree<>();
        rbt.put(700, "strawberry");
        rbt.put(250, "eggplant");
        rbt.put(100, "apple");
        rbt.put(500, "raspberry");
        rbt.put(150, "cherry");
        rbt.put(300, "honeydew");
        rbt.put(900, "watermelon");
        rbt.put(400, "mango");
        rbt.put(450, "pear");
        rbt.put(350, "lime");

        System.out.println(rbt.min());
        System.out.println(rbt.get(500));
        rbt.print();

        System.out.println("\n--------------------------------");
        rbt.delete(350);
        rbt.print();
        System.out.println("\n--------------------------------");

    }

    // node의 color를 쉽게 관리하기 위한 RED, BLACK 상수 정의
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    // Tree의 root를 저장
    private Node root;

    // Node 쿨래스
    private class Node {
        // Node 클래스의 기본 필드
        Key key;
        Value value;
        Node left, right;
        // LLRB를 위한 color 변수
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.left = null;
            this.right = null;
        }
    }

    // 빈 tree인지를 반환하는 메소드
    private boolean isEmpty() {
        return root == null;
    }

    // node의 색이 red인지를 검사하는 메소드
    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    // key 값을 주면 해당하는 value를 반환하는 메소드
    public Value get(Key key) {
        return get(root, key);
    }

    // target key와 current node의 key를 비교하며 value를 반환하는 함수
    private Value get(Node node, Key key) {
        // target key를 발견하지 못함
        if (node == null) {
            return null;
        }
        // compareTo 함수를 통해 target key와 current node의 key 비교
        int compare = node.key.compareTo(key);
        // current node의 key가 더 큰 경우 왼쪽 child 재귀 호출
        if (compare > 0) {
            return get(node.left, key);
        // target key가 더 큰 경우 오른쪽 child 재귀 호출
        } else if (compare < 0) {
            return get(node.right, key);
        // key가 같은 경우 node의 value return
        } else {
            return node.value;
        }
    }

    // 대상 node를 왼쪽으로 회전하는 메서드
    private Node rotateLeft(Node node) {
        // 대상 노드의 right child를 x에 저장
        Node x = node.right;
        // x의 left child를 대상 노드의 right chlid로 설정
        node.right = x.left;
        // node를 x의 left child로 설정
        x.left = node;
        // x의 color를 node의 color로 설정
        x.color = node.color;
        // node의 color를 Red로 설정
        node.color = RED;
        // x 반환 (원래 대상 node의 위치에 x삽입)
        return x;
    }

    // 대상 node를 오른쪽으로 회전하는 메서드
    private Node rotateRight(Node node) {
        // 대상 노드의 left child를 x에 저장
        Node x = node.left;
        // x의 right child를 대상 노드의 left chlid로 설정
        node.left = x.right;
        // node를 x의 right child로 설정
        x.right = node;
        // x의 color를 node의 color로 설정
        x.color = node.color;
        // node의 color를 Red로 설정
        node.color = RED;
        // x 반환 (원래 대상 node의 위치에 x삽입)
        return x;
    }

    // 대상 node와 left, right child의 색을 전환하는 메서드
    private void flipColors(Node node) {
        node.color = !node.color;
        if (node.left != null) {
            node.left.color = !node.left.color;
        }
        if (node.right != null) {
            node.right.color = !node.right.color;
        }
    }

    // red link를 왼쪽 아래로 내려보내는 메서드
    private Node moveRedLeft(Node node) {
        // flip colors(case 1, 2)
        flipColors(node);
        // right child의 left child가 red인 경우 (case 2)
        if (node.right != null && isRed(node.right.left)) {
            // right child를 오른쪽으로 회전
            node.right = rotateRight(node.right);
            // 대상 노드를 왼쪽으로 회전
            node = rotateLeft(node);
            // flip colors
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        // flip colors (case 1, 2)
        flipColors(node);
        // left child의 left child가 red인 경우 (case 2)
        if (node.left != null && isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node fixUp(Node node) {
        if (node == null) {
            return null;
        }
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    // 새로운 key, value를 갖는 node를 삽입하는 메서드
    public void put(Key key, Value value) {
        root = put(root, key, value);
        // root는 항상 black
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, RED);
        }
        // compareTo 함수를 통해 target key와 current node의 key 비교
        int compare = node.key.compareTo(key);
        // current node의 key가 더 큰 경우 왼쪽 child 재귀 호출 후 left child로 설정
        if (compare > 0) {
            node.left = put(node.left, key, value);
            // current node의 key가 더 작은 경우 오늘쪽 child 재귀 호출 후 right child로 설정
        } else if (compare < 0) {
            node.right = put(node.right, key, value);
            // key가 같은경우 value 업데이트
        } else {
            node.value = value;
            return node;
        }
        return fixUp(node);
    }


    // tree의 최솟값을 반환하는 메소드
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    // subtree의 최솟값을 갖는 node를 반환하는 메소드
    private Node min(Node node) {
        // left child가 null이면 해당 노드 반환
        if (node.left == null) {
            return node;
        }
        // 그렇지 않으면 left child 재귀 호출
        return min(node.left);
    }

    // 최솟값을 삭제하는 메서드
    public void deleteMin() {
        if (isEmpty()) {
            return;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node node) {
        // // left child가 null이면 null 반환 (삭제)
        if (node.left == null) {
            return null;
        }
        // left child와 left child의 left child가 모두 black이면
        // moveRedLeft 메서드 호출
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        // left child로 재귀 호출하고 반환된 값을 left child로 다시 설정
        node.left = deleteMin(node.left);
        // fixUp 메서드로 tree 재구성
        return fixUp(node);
    }

    // 특정 key를 갖는 nodeㄹㄹ 삭제하는 메서드
    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node delete(Node node, Key key) {
       // node의 key가 target key 보다 크다면
        if (key.compareTo(node.key) < 0) {
            // left child와 left child의 left child가 모두 black이면
            // moveRedLeft 메서드 호출
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            // left child로 재귀 호출하고 반환된 값을 left child로 다시 설정
            node.left = delete(node.left, key);
        } else {
            // node의 left child가 red라면
            if (isRed(node.left)) {
                // node를 오른쪽으로 회전
                node = rotateRight(node);
            }
            // node가 leap일 때
            if (key.compareTo(node.key) == 0 && node.right == null) {
                // delete node
                return null;
            }
            // right child가 black이고 right의 left child도 black이면
            if (!isRed(node.right) && !isRed(node.right.left)) {
                // node를 오른쪽으로 회전
                node = moveRedRight(node);
            }
            if (key.compareTo(node.key) == 0) {
                // successor의 정보를 복사
                Node successor = min(node.right);
                node.key = successor.key;
                node.value = successor.value;
                // delete successor
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(node.right, key);
            }
        }
        return fixUp(node);
    }

    // preorder로 tree를 순회하는 메소드
    private void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key + " ");
        preorder(node.left);
        preorder(node.right);
    }

    // inorder로 tree를 순회하는 메소드
    private void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.key + " ");
        inorder(node.right);
    }

    // levelorder로 tree를 순회하는 메소드
    private void levelorder(Node node) {
        Queue<Node> queue = new LinkedList<>();
        Node target;
        queue.add(node);
        while (!queue.isEmpty()) {
            target = queue.remove();
            System.out.print(target.key + " ");
            if (target.left != null) {
                queue.add(target.left);
            }
            if (target.right != null) {
                queue.add(target.right);
            }
        }
    }

    private void print( ) {
        System.out.println("\ninorder: ");
        inorder(root);
        System.out.println("\npreorder: ");
        preorder(root);
        System.out.println("\nlevelorder: ");
        levelorder(root);
    }
}
