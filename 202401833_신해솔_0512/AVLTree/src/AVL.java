import java.util.LinkedList;
import java.util.Queue;

public class AVL<Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {
        AVL<Integer, String> tree = new AVL<>();
        tree.put(30, "Apple");
        tree.put(40, "Grape");
        tree.put(100, "Lime");
        tree.put(20, "Mange");
        tree.put(10, "Strawberry");
        tree.put(60, "Banana");
        tree.put(70, "Cherry");
        tree.put(120, "Watermelon");
        tree.put(110, "Melon");
        tree.print(tree.root);
        System.out.println("\nmin value: " + tree.min());
        tree.deleteMin();
        System.out.println("\nmin value: " + tree.min());
        tree.delete(30);
        tree.print(tree.root);
    }
    // AVL 트리의 루트
    private Node root;

    // inner Class Node
    public class Node {
        private final Key key;
        private Value value;
        private int height;
        private Node left, right;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.left = null;
            this.right = null;
        }
    }
    // tree의 height을 반환하는 메소드
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // node의 두 subtree의 height을 통해 새 높이를 반환하는 메소드
    private int getNewHeight(Node node) {
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // 새로운 key, value를 갖는 node 삽입
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
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
        // 높이 재설정
        node.height = getNewHeight(node);
        // 균형 맞추기
        return balance(node);
    }

    // 트리의 불균형을 조정하는 메소드
    private Node balance(Node node) {
        // left subtree의 height과 right subtree height 차가 2이상 일 때
        if (bf(node) > 1) {
            // LR
            if (bf(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            // LL
            node = rotateRight(node);
        // right subtree의 height과 left subtree height 차가 2이상 일 때
        } else if (bf(node) < -1) {
            // RL
            if (bf(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            // LL
            node = rotateLeft(node);
        }
        return node;
    }

    // 왼쪽 서브트리의 height - 오른쪽 서브트리의 높이 값을 반환
    private int bf(Node node) {
        return height(node.left) - height(node.right);
    }

    // 대상 node를 오른쪽으로 회전
    private Node rotateRight(Node node) {
        // 대상 노드의 left child를 x에 저장
        Node x = node.left;
        // 대상 노드의 left child를 x의 right child로 설정
        node.left = x.right;
        // x의 right child를 대상 노드로 설정
        x.right = node;
        // 대상 노드와 x의 높이를 재설정
        node.height = getNewHeight(node);
        x.height = getNewHeight(x);
        // x 반환 (대상 노드 위치에 x로 교체)
        return x;
    }

    // 대상 node를 오른쪽으로 회전
    private Node rotateLeft(Node node) {
        // 대상 노드의 right child를 x에 저장
        Node x = node.right;
        // 대상 노드의 right child를 x의 left child로 설정
        node.right = x.left;
        // x의 right child를 대상 노드로 설정
        x.left = node;
        // 대상 노드와 x의 높이를 재설정
        node.height = getNewHeight(node);
        x.height = getNewHeight(x);
        // x 반환 (대상 노드 위치에 x로 교체)
        return x;
    }

    // tree의 최솟값을 반환하는 메소드
    public Key min() {
        return min(root).key;
    }

    // subtree의 최솟값을 갖는 node를 반환하는 메소드
    public Node min(Node node) {
        // left child가 null이면 해당 노드 반환
        if (node.left == null) {
            return node;
        }
        // 그렇지 않으면 left child 재귀 호출
        return min(node.left);
    }

    // 최솟값을 갖는 node를 삭제하는 메소드
    public void deleteMin() {
        root = deleteMin(root);
    }

    // 최솟값을 갖는 node를 삭제하는 메소드
    public Node deleteMin(Node node) {
        // left child가 null이면 right child 반환
        if (node.left == null) {
            return node.right;
        }
        // left chlid를 재귀 호출하고 반환된 값을 다시 left child로 set
        node.left = deleteMin(node.left);

        // 높이 재설정, 균형 맞추기
        node.height = getNewHeight(node);
        return balance(node);
    }

    // 특정 key를 갖는 node를 삭제하는 메소드
    public void delete(Key key) {
        root = delete(root, key);
    }

    public Node delete(Node node, Key key) {
        // node가 null이면 null 반환
        if (node == null) {
            return null;
        }
        // compareTo 메소드로 current key와 target key를 비교
        int compare = node.key.compareTo(key);
        // current key가 더 클 경우
        if (compare > 0) {
            // left child를 재귀호출하고 left child로 set
            node.left = delete(node.left, key);
            // target key가 더 클 경우
        } else if (compare < 0) {
            // right child를 재귀호출하고 right child로 set
            node.right = delete(node.right, key);
            // current key와 target key가 같은 경우
        } else {
            // right child가 null인 경우
            if (node.right == null) {
                // left child를 반환
                // null 또는 left child node가 반환됨
                return node.left;
            }
            // left child가 null인 경우
            if (node.left == null) {
                // right child node가 반환됨
                return node.right;
            }
            // child가 모두 존재하는 경우
            Node tmp = node;
            // right subtree의 가장 작은 node를 reference
            node = min(tmp.right);
            // tmp의 min node를 제거한 right subtree를 node의 right subtree로 set
            node.right = deleteMin(tmp.right);
            // tmp의 left subtree를 node의 left subtree로 set
            node.left = tmp.left;
        }
        // 높이 재설정, 균형 맞추기
        node.height = getNewHeight(node);
        return balance(node);
    }

    // preorder로 tree를 순회하는 메소드
    public void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key + " ");
        preorder(node.left);
        preorder(node.right);
    }

    // inorder로 tree를 순회하는 메소드
    public void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.key + " ");
        inorder(node.right);
    }

    // levelorder로 tree를 순회하는 메소드
    public void levelorder(Node node) {
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

    public void print(Node node) {
        System.out.println("\ninorder: ");
        inorder(node);
        System.out.println("\npreorder: ");
        preorder(node);
        System.out.println("\nlevelorder: ");
        levelorder(node);
    }
}
