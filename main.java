import java.util.ArrayList;
import java.util.Scanner;

public class TaskManagerProject {

    // Task Node for Linked List
    static class TaskNode {
        String description;
        boolean completed;
        TaskNode next;

        public TaskNode(String description) {
            this.description = description;
            this.completed = false;
            this.next = null;
        }
    }

    // Queue implementation (FIFO) using Linked List
    static class TaskQueue {
        TaskNode front, rear;

        public TaskQueue() {
            front = rear = null;
        }

        // Enqueue (add to end)
        void enqueue(String description) {
            TaskNode newNode = new TaskNode(description);
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            System.out.println("Task added to queue.");
        }

        // Dequeue (remove from front)
        TaskNode dequeue() {
            if (front == null) {
                System.out.println("Queue is empty.");
                return null;
            }
            TaskNode temp = front;
            front = front.next;
            if (front == null) rear = null;
            return temp;
        }

        // Mark as completed
        boolean markCompleted(int idx) {
            TaskNode curr = front;
            int count = 0;
            while (curr != null) {
                if (count == idx) {
                    curr.completed = true;
                    return true;
                }
                curr = curr.next;
                count++;
            }
            return false;
        }

        // Delete task by index
        boolean delete(int idx) {
            if (front == null) return false;
            if (idx == 0) {
                front = front.next;
                if (front == null) rear = null;
                return true;
            }
            TaskNode curr = front, prev = null;
            int count = 0;
            while (curr != null && count < idx) {
                prev = curr;
                curr = curr.next;
                count++;
            }
            if (curr == null) return false;
            prev.next = curr.next;
            if (curr == rear) rear = prev;
            return true;
        }

        // View all tasks (return as ArrayList for flexible display)
        ArrayList<TaskNode> viewTasks() {
            ArrayList<TaskNode> list = new ArrayList<>();
            TaskNode curr = front;
            while (curr != null) {
                list.add(curr);
                curr = curr.next;
            }
            return list;
        }

        boolean isEmpty() {
            return front == null;
        }
    }

    // --- Array example for demonstration ---
    static void demoArray() {
        System.out.println("Demo: Array of sample priorities for new tasks.");
        int[] priorities = {1, 2, 3, 2, 1};
        System.out.print("Priorities: ");
        for (int i = 0; i < priorities.length; i++) {
            System.out.print(priorities[i] + " ");
        }
        System.out.println();
    }

    // --- ArrayList example for demonstration ---
    static void demoArrayList() {
        ArrayList<String> quickList = new ArrayList<>();
        quickList.add("Sample 1");
        quickList.add("Sample 2");
        quickList.add("Sample 3");
        System.out.println("Demo: ArrayList contents:");
        for (int i = 0; i < quickList.size(); i++) {
            System.out.println((i+1) + ". " + quickList.get(i));
        }
    }

    // --- Main menu and logic ---
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskQueue queue = new TaskQueue();

        while (true) {
            System.out.println("\n--- Task Manager Project ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Dequeue Task (FIFO)");
            System.out.println("6. Demo: Array");
            System.out.println("7. Demo: ArrayList");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = -1;
            try { choice = Integer.parseInt(sc.nextLine()); } catch (Exception e) {}

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String desc = sc.nextLine();
                    queue.enqueue(desc);
                    break;
                case 2:
                    ArrayList<TaskNode> tasks = queue.viewTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks in queue.");
                    } else {
                        System.out.println("Tasks in queue:");
                        for (int i = 0; i < tasks.size(); i++) {
                            TaskNode t = tasks.get(i);
                            System.out.printf("%d. %s [%s]\n", i+1, t.description, t.completed ? "Completed" : "Pending");
                        }
                    }
                    break;
                case 3:
                    tasks = queue.viewTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks to complete.");
                        break;
                    }
                    System.out.print("Enter task number to mark as completed: ");
                    int idxC = -1;
                    try { idxC = Integer.parseInt(sc.nextLine()) - 1; } catch (Exception e) {}
                    if (queue.markCompleted(idxC)) {
                        System.out.println("Task marked as completed.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case 4:
                    tasks = queue.viewTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks to delete.");
                        break;
                    }
                    System.out.print("Enter task number to delete: ");
                    int idxD = -1;
                    try { idxD = Integer.parseInt(sc.nextLine()) - 1; } catch (Exception e) {}
                    if (queue.delete(idxD)) {
                        System.out.println("Task deleted.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case 5:
                    TaskNode removed = queue.dequeue();
                    if (removed != null) {
                        System.out.println("Dequeued and removed: " + removed.description);
                    }
                    break;
                case 6:
                    demoArray();
                    break;
                case 7:
                    demoArrayList();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
