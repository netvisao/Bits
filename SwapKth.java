import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Amine
 * Date: 8/25/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */


// http://www.geeksforgeeks.org/morgan-stanley-interview-set-2-2/#comment-26892
public class SwapKth {

    static class Node<T> {

        private Node() {
            ;
        }


        public Node(T value) {
            if (value == null) throw new NullPointerException("value");
            this.value = value;
        }

        T value;
        Node<T> next = null;
        int count() {
            Node<T> currentNode = this;
            for (int i = 1;;i++) {
                if (currentNode.next == null) return i;
                currentNode = currentNode.next;
            }
        }

        Node<T> push(T value) {
            Node<T> newNode = new Node<T>();
            newNode.value = value;
            Node<T> next = this.next;
            this.next = newNode;
            newNode.next = next;
            return newNode;
        }

        public String toString() {
            String s = this.value.toString();
            Node<T> currentNode = this;

            for (;;) {
                if (currentNode.next == null) {
                    return s;
                }
                s += "->" + currentNode.next.value;
                currentNode = currentNode.next;
            }
        }
    }


    public static <T> Node<T> swapKth(Node<T> node1, int k) {
        int count = node1.count();
        if (k > count || k < 1) return node1;
        if (2 * k == count + 1) return node1;
        int[] stops = {Math.min(k, count - k + 1), Math.max(k, count - k + 1)};

        Node<T> prvNode = null, tmpNode, prevKthNode = null, currentNode = node1, kthNode, kthNodeFromEnd;
        for (int i = 1;; i++) {

            if (i == stops[0]) prevKthNode = prvNode;
            if (i == stops[1]) break;

            prvNode = currentNode;
            currentNode = currentNode.next;
        }

        //Now swap
        kthNode = prevKthNode == null ? node1 : prevKthNode.next;
        kthNodeFromEnd = prvNode.next;

        if (prevKthNode != null)
            prevKthNode.next = kthNodeFromEnd;
        else
            node1 = kthNodeFromEnd;


        prvNode.next = kthNode;

        tmpNode = kthNode.next;
        kthNode.next = kthNodeFromEnd.next;
        kthNodeFromEnd.next = tmpNode;

        return node1;
    }


    public static void main(String[] args) {

        int k = 3;
        Node<Integer> node1 = new Node<Integer>();
        node1.value = 1;
        node1.push(4).push(3).push(2).push(5);

        System.out.println(swapKth(node1, 6));
        System.out.println(swapKth(node1, 3));
        System.out.println(node1 = swapKth(node1, 5));
        System.out.println(swapKth(node1, 2));

    }



}
