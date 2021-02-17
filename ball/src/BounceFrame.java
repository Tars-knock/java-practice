import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class BounceFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private BallComponent comp;
    public static final int steps = 1000;
    public static final in DELAY = 3;

    public BounceFrame()
    {
        setTitle("Bounce");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "开始", event -> addBall());
        addButton(buttonPanel, "结束", event ->System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener)
    {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall()
    {
        try{
            Ball ball = new Ball();
            comp.add(ball);

            for(int i = 1; i <= STEPS; i++)
            {
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        }
        catch(InterruptException e)
        {}
    }

}