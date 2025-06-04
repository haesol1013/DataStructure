public class BinaryTree<Key extends Comparable<Key>> {
    // root node를 저장하기 위한 변수 선언
    private Node<Key> root;

    // 생성 시에는 root를 null로 설정
    public BinaryTree() {
        root = null;
    }

    // root의 getter
    public Node<Key> getRoot() {
        return this.root;
    }

    // root의 setter
    public void setRoot(Node<Key> root) {
        this.root = root;
    }

    // tree가 비었는 지를 확인하는 메소드
    public boolean isEmpty() {
        return this.root == null;
    }

    // 전위 순회(Preorder): 노드(N) → 왼쪽 서브트리(L) → 오른쪽 서브트리(R) 순으로 방문
    public void preorder(Node<Key> node) {
        if (node != null) {
            // 노드에 도달하면 값 출력 (N)
            System.out.print(node.getKey() + " ");
            // 왼쪽 노드 재귀 호출 (L)
            preorder(node.getLeft());
            // 오른쪽 노드 재귀 호출 (R)
            preorder(node.getRight());
        }
    }

    // 중위 순회(Inorder): 왼쪽 서브트리(L) → 노드(N) → 오른쪽 서브트리(R) 순으로 방문
    public void inorder(Node<Key> node) {
        if (node != null) {
            // 왼쪽 노드 재귀 호출 (L)
            inorder(node.getLeft());
            // 노드에 도달하면 값 출력 (N)
            System.out.print(node.getKey() + " ");
            // 오른쪽 노드 재귀 호출 (R)
            inorder(node.getRight());
        }
    }

    // 후위 순회(Postorder): 왼쪽 서브트리(L) → 오른쪽 서브트리(R) → 노드(N) 순으로 방문
    public void postorder(Node<Key> node) {
        if (node != null) {
            // 왼쪽 노드 재귀 호출 (L)
            postorder(node.getLeft());
            // 오른쪽 노드 재귀 호출 (R)
            postorder(node.getRight());
            // 노드에 도달하면 값 출력 (N)
            System.out.print(node.getKey() + " ");
        }
    }

    // 노드의 수를 리턴하는 재귀 함수
    public int size(Node<Key> node) {
        // 노드가 null이라면 0 리턴 (Base Case)
        if (node == null) {
            return 0;
        } else {
            // 자신 카운트 + 왼쪽 노드 재귀 호출 + 오른쪽 노드 재귀 호출
            // 이 과정에서 모든 노드가 카운트됨
            return 1 + size(node.getLeft()) + size(node.getRight());
        }
    }

    // 노드의 깊이를 리턴하는 재귀 함수
    public int height(Node<Key> node) {
        // 노드가 null이라면 0 리턴 (Base Case)
        if (node == null) {
            return 0;
        } else {
            // 자신 카운트 (서브 트리의 루트 노드)
            // 왼쪽 노드, 오른쪽 노드 재귀 호출하여 높은 값을 취함
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
    }

    // 두 노드를 받아 각 노드를 루트로 하는 두 트리를 비교하는 함수
    public static <Key extends Comparable<Key>> boolean isEqual(Node<Key> n, Node<Key> m) {
        // 두 노드 중 하나라도 null이면, 둘 다 null일 때만 true, 아니면 false 반환
        if (n == null || m == null) {
            return n == m;
        }

        // 두 노드의 값을 비교하고 다르면 false return
        if (n.getKey().compareTo(m.getKey()) != 0) {
            return false;
        }

        // 트리의 모든 노드를 비교하기 위해 두 노드의 Child 노드를 재귀적으로 비교
        return (isEqual(n.getLeft(), m.getLeft()) &&
                isEqual(n.getRight(), m.getRight()));
    }

    // 주어진 노드를 루트로 하는 서브트리의 깊은 복사(deep copy)를 수행하여 새로운 트리를 생성하고 루트 노드 반환
    public Node<Key> copy(Node<Key> node) {
        // null 노드라면 역시 null 노드 반환
        if (node == null) {
            return null;
        }

        // 새로운 노드 생성
        Node<Key> newNode = new Node<>(node.getKey(), null, null);
        // 재귀적으로 인자로 주어진 노드의 Child 노드까지 복사
        newNode.setLeft(copy(node.getLeft()));
        newNode.setRight(copy(node.getRight()));
        return newNode;
    }
}
