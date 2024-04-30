package co.edu.uptc.structures;

public class PriorittyQueue<T> {
    private MyQueue<T>[] queues;

    public PriorittyQueue(int level) {
        this.queues = new MyQueue[level];
    }

    public void push(T data, int levelPriority) {
        Node<T> node = new Node<T>(data);
        if (queues.length - 1 >= levelPriority) {
            if (queues[levelPriority] == null) {
                queues[levelPriority] = new MyQueue<T>();
                queues[levelPriority].push(data);
            }
        }
    }

    public T pull() {
        int count = 0;
        MyQueue<T> queueAux = new MyQueue<>();
        for (MyQueue<T> queue : queues) {
            if (!queue.isEmpty() && count == 0) {
                count++;
                queueAux = queue;
            }
        }
        return queueAux.poll();
    }


    public T peek() {
        int level = 0;
        int removed = 0;
        MyQueue<T> queueTemp = queues[level];
        if (!queues[0].isEmpty()) {
            while (removed < 1) {
                if (!queueTemp.isEmpty()) {
                    removed++;
                }
            }
        } else {
            while (queueTemp.isEmpty()) {
                level++;
                queueTemp = queues[level];
            }
        }
        return queueTemp.peek();
    }

    public boolean isEmpty() {
        boolean verification = true;
            for (MyQueue<T> queue : queues) {
                if (!(queue ==null)) {
                    verification= false;
                }
            }
            return verification;
        }

    }
