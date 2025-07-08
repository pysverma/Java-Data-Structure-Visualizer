import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Queue;

public class DSVisualizerGUI extends JFrame {

    BinaryTree tree = new BinaryTree();
    JTextArea treeArea = new JTextArea(5, 30);

    TreePanel treePanelDraw = new TreePanel();


    Stack<Integer> stack = new Stack<>();
    Queue<Integer> queue = new LinkedList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();

    JTextArea stackArea = new JTextArea(5, 30);
    JTextArea queueArea = new JTextArea(5, 30);
    JTextArea listArea = new JTextArea(5, 30);

    public DSVisualizerGUI() {
        setTitle("🧠 Data Structures Visualizer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Stack", stackPanel());
        tabs.add("Queue", queuePanel());
        tabs.add("Linked List", listPanel());

        add(tabs);
        setVisible(true);
        tabs.add("Binary Tree", treePanel());
    }

    // ==== Stack Tab ====
    JPanel stackPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField input = new JTextField(10);
        JButton pushBtn = new JButton("Push");
        JButton popBtn = new JButton("Pop");

        JPanel controls = new JPanel();
        controls.add(new JLabel("Value:"));
        controls.add(input);
        controls.add(pushBtn);
        controls.add(popBtn);

        stackArea.setEditable(false);
        panel.add(controls, BorderLayout.NORTH);
        panel.add(new JScrollPane(stackArea), BorderLayout.CENTER);

        pushBtn.addActionListener(e -> {
            try {
                int val = Integer.parseInt(input.getText());
                stack.push(val);
                updateStackArea();
                input.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input!");
            }
        });

        popBtn.addActionListener(e -> {
            if (!stack.isEmpty()) {
                stack.pop();
                updateStackArea();
            } else {
                JOptionPane.showMessageDialog(this, "Stack is empty!");
            }
        });

        return panel;
    }

    void updateStackArea() {
        StringBuilder sb = new StringBuilder("Stack (bottom → top):\n");
        for (int val : stack) {
            sb.append(val).append(" <- ");
        }
        if (!stack.isEmpty()) sb.setLength(sb.length() - 4);
        stackArea.setText(sb.toString());
    }

    // ==== Queue Tab ====
    JPanel queuePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField input = new JTextField(10);
        JButton enqueueBtn = new JButton("Enqueue");
        JButton dequeueBtn = new JButton("Dequeue");

        JPanel controls = new JPanel();
        controls.add(new JLabel("Value:"));
        controls.add(input);
        controls.add(enqueueBtn);
        controls.add(dequeueBtn);

        queueArea.setEditable(false);
        panel.add(controls, BorderLayout.NORTH);
        panel.add(new JScrollPane(queueArea), BorderLayout.CENTER);

        enqueueBtn.addActionListener(e -> {
            try {
                int val = Integer.parseInt(input.getText());
                queue.offer(val);
                updateQueueArea();
                input.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input!");
            }
        });

        dequeueBtn.addActionListener(e -> {
            if (!queue.isEmpty()) {
                queue.poll();
                updateQueueArea();
            } else {
                JOptionPane.showMessageDialog(this, "Queue is empty!");
            }
        });

        return panel;
    }

    void updateQueueArea() {
        StringBuilder sb = new StringBuilder("Queue (front → rear):\n");
        for (int val : queue) {
            sb.append(val).append(" -> ");
        }
        sb.append("null");
        queueArea.setText(sb.toString());
    }

    // ==== Linked List Tab ====
    JPanel listPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField input = new JTextField(10);
        JButton insertBtn = new JButton("Insert");
        JButton deleteBtn = new JButton("Delete Last");

        JPanel controls = new JPanel();
        controls.add(new JLabel("Value:"));
        controls.add(input);
        controls.add(insertBtn);
        controls.add(deleteBtn);

        listArea.setEditable(false);
        panel.add(controls, BorderLayout.NORTH);
        panel.add(new JScrollPane(listArea), BorderLayout.CENTER);

        insertBtn.addActionListener(e -> {
            try {
                int val = Integer.parseInt(input.getText());
                linkedList.add(val);
                updateListArea();
                input.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input!");
            }
        });

        deleteBtn.addActionListener(e -> {
            if (!linkedList.isEmpty()) {
                linkedList.removeLast();
                updateListArea();
            } else {
                JOptionPane.showMessageDialog(this, "List is empty!");
            }
        });

        return panel;
    }

    void updateListArea() {
        StringBuilder sb = new StringBuilder("Linked List:\n");
        for (int val : linkedList) {
            sb.append(val).append(" -> ");
        }
        sb.append("null");
        listArea.setText(sb.toString());
    }

    JPanel treePanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JTextField input = new JTextField(10);
    JButton insertBtn = new JButton("Insert");

    JPanel controls = new JPanel();
    controls.setBackground(new Color(230, 255, 230));
    controls.add(new JLabel("Value:"));
    controls.add(input);
    controls.add(insertBtn);

    insertBtn.addActionListener(e -> {
        try {
            int val = Integer.parseInt(input.getText());
            tree.insert(val);
            treeArea.setText("Inorder Traversal:\n" + tree.inorder());
            treePanelDraw.setTree(tree.root); // <- trigger visual update
            input.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    });

    treeArea.setEditable(false);
    treeArea.setBackground(new Color(245, 255, 245));
    treeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

    JScrollPane treeScroll = new JScrollPane(treePanelDraw);
    treeScroll.setPreferredSize(new Dimension(500, 300));

    JPanel visualPanel = new JPanel(new BorderLayout());
    visualPanel.add(new JScrollPane(treeArea), BorderLayout.NORTH);
    visualPanel.add(treeScroll, BorderLayout.CENTER);

    panel.add(controls, BorderLayout.NORTH);
    panel.add(visualPanel, BorderLayout.CENTER);

    return panel;
}

    // ==== Main Entry ====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DSVisualizerGUI::new);
    }
}
