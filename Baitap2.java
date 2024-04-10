package multiThread;
import java.awt.Color;
import java.awt.Graphics; 
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Baitap2 extends JPanel implements Runnable {

	private int x = 0;
    private int y = 0;
    private int dx = 1;
    private int dy = 1;
    
    public Baitap2() {
        new Thread(this).start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, 50, 50);
    }
    
    public void run() {
        while (true) {
            // cập nhật vị trí
            x += dx;
            y += dy;
            
            // kiểm tra va chạm
            if (x < 0 || x > getWidth()) {
                dx = -dx; 
            }
            if (y < 0 || y > getHeight()) {
                dy = -dy;
            }
            
            repaint();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new Baitap2());
        frame.setVisible(true);
    }
}