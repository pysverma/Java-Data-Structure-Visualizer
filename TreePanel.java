import javax.swing.*;
import java.awt.*;

class TreePanel extends JPanel {
    private TreeNode root;
    private final int NODE_SIZE = 30;
    private final int LEVEL_HEIGHT = 60;

    public void setTree(TreeNode root) {
        this.root = root;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(250, 255, 250));
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.setStroke(new BasicStroke(2));
        drawTree(g2, root, getWidth() / 2, 40, getWidth() / 4);
    }

    private void drawTree(Graphics2D g, TreeNode node, int x, int y, int offset) {
        if (node == null) return;

        // Draw node
        g.setColor(new Color(102, 204, 255));
        g.fillOval(x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE);

        // Draw value
        String val = String.valueOf(node.val);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(val);
        int textHeight = fm.getAscent();
        g.drawString(val, x - textWidth / 2, y + textHeight / 4);

        // Draw left child
        if (node.left != null) {
            int childX = x - offset;
            int childY = y + LEVEL_HEIGHT;
            g.drawLine(x, y, childX, childY);
            drawTree(g, node.left, childX, childY, offset / 2);
        }

        // Draw right child
        if (node.right != null) {
            int childX = x + offset;
            int childY = y + LEVEL_HEIGHT;
            g.drawLine(x, y, childX, childY);
            drawTree(g, node.right, childX, childY, offset / 2);
        }
    }
}
