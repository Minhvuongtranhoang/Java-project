package multiThread;
import javax.swing.*;
import java.awt.*;

class TrafficLight {
    enum LightColor {
        RED, YELLOW, GREEN
    }

    private LightColor currentColor;

    public TrafficLight() {
        this.currentColor = LightColor.RED;
    }

    public synchronized void changeColor() {
        switch (currentColor) {
            case RED:
                currentColor = LightColor.GREEN;
                break;
            case YELLOW:
                currentColor = LightColor.RED;
                break;
            case GREEN:
                currentColor = LightColor.YELLOW;
                break;
        }
    }

    public synchronized LightColor getCurrentColor() {
        return currentColor;
    }
}

class TrafficLightPanel extends JPanel {
    private TrafficLight trafficLight;

    public TrafficLightPanel(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        TrafficLight.LightColor currentColor = trafficLight.getCurrentColor();
        switch (currentColor) {
            case RED:
                g.setColor(Color.RED);
                g.fillOval(50, 50, 100, 100);
                break;
            case YELLOW:
                g.setColor(Color.YELLOW);
                g.fillOval(50, 170, 100, 100);
                break;
            case GREEN:
                g.setColor(Color.GREEN);
                g.fillOval(50, 290, 100, 100);
                break;
        }
    }
}

class TrafficLightFrame extends JFrame {
    private TrafficLight trafficLight;

    public TrafficLightFrame(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;

        TrafficLightPanel panel = new TrafficLightPanel(trafficLight);
        add(panel);

        // Tạo một luồng riêng biệt để cập nhật màu sáng đèn giao thông và giao diện đồ họa
        Thread updateThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                trafficLight.changeColor();
                SwingUtilities.invokeLater(panel::repaint);
            }
        });
        updateThread.start();
    }
}

public class TrafficLightSimulation {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        TrafficLightFrame frame = new TrafficLightFrame(trafficLight);
        frame.setSize(200, 450);
        frame.setTitle("Traffic Light Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}