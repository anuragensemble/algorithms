import datastructures.linkedlists.LinkedList;
import datastructures.linkedlists.LinkedList.Node;
public class ReverseKGroupLinkedListRecursive {

    Node reverseKGroup(Node head, int k) {
        int count = 0;
        Node node = head;
        while (count < k && node != null) {
            node = node.next;
            count++;
        }

        if (count == k) {
            Node newHead = reverseLinkedList(head, k);
            head.next = reverseKGroup(node, k);
            return newHead;
        }
        return head;
    }

    Node reverseLinkedList(Node head, int k) {
        Node current = head;
        Node previous = null;
        while (k > 0) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            k--;
        }
        return previous;
    }

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.enqueue(1);
        ll.enqueue(2);
        ll.enqueue(3);
        ll.enqueue(4);
        ll.enqueue(5);
        System.out.println(ll);
        System.out.println(ll.head.value);
        ReverseKGroupLinkedListRecursive reverse = new ReverseKGroupLinkedListRecursive();
        Node reversed = reverse.reverseKGroup(ll.head, 3);
        System.out.println(reversed);
    }
}
