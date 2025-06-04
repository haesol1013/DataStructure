import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST <Key extends Comparable<Key>, Value> {
    // Tree의 root를 저장할 필드
    public Node<Key, Value> root;

    // BST 클래스의 생성자
    public BST(Key key, Value value) {
        root = new Node<>(key, value);
    }

    // root의 getter
    public Node<Key, Value> getRoot() {
        return root;
    }

    // key 값을 주면 해당하는 value를 반환하는 메소드
    public Value get(Key key) {
        return get(root, key);
    }

    // target key와 current node의 key를 비교하며 value를 반환하느 함수
    public Value get(Node<Key, Value> node, Key key) {
        // target key를 발견하지 못함
        if (node == null) {
            return null;
        }
        // compareTo 함수를 통해 target key와 current node의 key 비교
        int compare = node.getKey().compareTo(key);
        // current node의 key가 더 큰 경우 왼쪽 child 재귀 호출
        if (compare > 0) {
            return get(node.getLeft(), key);
        // target key가 더 큰 경우 오른쪽 child 재귀 호출
        } else if (compare < 0) {
            return get(node.getRight(), key);
        // key가 같은 경우 node의 value return
        } else {
            return node.getValue();
        }
    }

    // key와 value를 받아 새로운 위치에 node를 생성하는 메소드
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Node<Key, Value> put(Node<Key, Value> node, Key key, Value value){
        // leaf의 child로 새로운 node 생성
        if (node == null) {
            return new Node<>(key, value);
        }
        // compareTo 함수를 통해 target key와 current node의 key 비교
        int compare = node.getKey().compareTo(key);
        // current node의 key가 더 큰 경우 왼쪽 child 재귀 호출 후 left child로 설정
        if (compare > 0) {
            node.setLeft(put(node.getLeft(), key, value));
        // current node의 key가 더 작은 경우 오늘쪽 child 재귀 호출 후 right child로 설정
        } else if (compare < 0) {
            node.setRight(put(node.getRight(), key, value));
        // key가 같은경우 value 업데이트
        } else {
            node.setValue(value);
        }
        // node 반환
        return node;
    }

    // tree의 최솟값을 반환하는 메소드
    public Key min() {
        return min(root).getKey();
    }

    // subtree의 최솟값을 갖는 node를 반환하는 메소드
    public Node<Key, Value> min(Node<Key, Value> node) {
        // left child가 null이면 해당 노드 반환
        if (node.getLeft() == null) {
            return node;
        }
        // 그렇지 않으면 left child 재귀 호출
        return min(node.getLeft());
    }

    // 최솟값을 갖는 node를 삭제하는 메소드
    public void deleteMin() {
        root = deleteMin(root);
    }

    // 최솟값을 갖는 node를 삭제하는 메소드
    public Node<Key, Value> deleteMin(Node<Key, Value> node) {
        // left child가 null이면 right child 반환
        if (node.getLeft() == null) {
            return node.getRight();
        }
        // left chlid를 재귀 호출하고 반환된 값을 다시 left child로 set
        node.setLeft(deleteMin(node.getLeft()));
        return node;
    }

    // 최댓값을 갖는 node를 삭제하는 메소드
    public void deleteMax() {
        root = deleteMax(root);
    }

    // 최댓값을 갖는 node를 삭제하는 메소드
    public Node<Key, Value> deleteMax(Node<Key, Value> node) {
        // right child가 null이면 left child를 반환함
        if (node.getRight() == null) {
            return node.getLeft();
        }
        // right chlid를 재귀 호출하고 반환된 값을 다시 right child로 set
        node.setRight(deleteMax(node.getRight()));
        return node;
    }

    // 특정 key를 갖는 node를 삭제하는 메소드
    public void delete(Key key) {
        root = delete(root, key);
    }

    // 특정 key를 갖는 node를 삭제하는 메소드
    public Node<Key, Value> delete(Node<Key, Value> node, Key key) {
        // node가 null이면 null 반환S

        if (node == null) {
            return null;
        }
        // compareTo 메소드로 current key와 target key를 비교
        int compare = node.getKey().compareTo(key);
        // current key가 더 클 경우
        if (compare > 0) {
            // left child를 재귀호출하고 left child로 set
            node.setLeft(delete(node.getLeft(), key));
        // target key가 더 클 경우
        } else if (compare < 0) {
            // right child를 재귀호출하고 right child로 set
            node.setRight(delete(node.getRight(), key));
        // current key와 target key가 같은 경우
        } else {
            // right child가 null인 경우
            if (node.getRight() == null) {
                // left child를 반환
                // null 또는 left child node가 반환됨
                return node.getLeft();
            }
            // left child가 null인 경우
            if (node.getLeft() == null) {
                // right child node가 반환됨
                return node.getRight();
            }
            // child가 모두 존재하는 경우
            Node<Key, Value> tmp = node;
            // right subtree의 가장 작은 node를 reference
            node = min(tmp.getRight());
            // tmp의 min node를 제거한 right subtree를 node의 rig(ht subtree로 set
            node.setRight(deleteMin(tmp.getRight()));
            // tmp의 left subtree를 node의 left subtree로 set
            node.setLeft(tmp.getLeft());
        }
        return node;
    }

    // tree의 height을 반환하는 메소드
    public int height() {
        return height(root);
    }

    // tree의 height을 반환하는 메소드
    private int height(Node<Key, Value> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public void print(Node<Key, Value> node) {
        System.out.println("\ninorder: ");
        inorder(node);
        System.out.println("\npreorder: ");
        preorder(node);
        System.out.println("\nlevelorder: ");
        levelorder(node);
    }

    // preorder로 tree를 순회하는 메소드
    public void preorder(Node<Key, Value> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getKey() + " ");
        preorder(node.getLeft());
        preorder(node.getRight());
    }

    // inorder로 tree를 순회하는 메소드
    public void inorder(Node<Key, Value> node) {
        if (node == null) {
            return;
        }
        inorder(node.getLeft());
        System.out.print(node.getKey() + " ");
        inorder(node.getRight());
    }

    // levelorder로 tree를 순회하는 메소드
    public void levelorder(Node<Key, Value> node) {
        Queue<Node<Key, Value>> queue = new LinkedList<>();
        Node<Key, Value> target;
        queue.add(node);
        while (!queue.isEmpty()) {
            target = queue.remove();
            System.out.print(target.getKey() + " ");
            if (target.getLeft() != null) {
                queue.add(target.getLeft());
            }
            if (target.getRight() != null) {
                queue.add(target.getRight());
            }
        }
    }
}
