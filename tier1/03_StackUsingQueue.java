import java.util.ArrayDeque;
import java.util.Queue;

class StackUsingQueue {

    private final Queue<Integer> queue;

    public StackUsingQueue() {
        queue = new ArrayDeque<>();
    }


    public void push(int x) {
        queue.offer(x);

        for(int i =0; i< queue.size() - 1; i++){
            queue.offer(queue.poll());
        }
    }


    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args){
        StackUsingQueue myStack = new StackUsingQueue();
        myStack.push(1);
        myStack.push(2);
        myStack.top(); // return 2
        myStack.pop(); // return 2
        myStack.empty(); // return False
    }
}
