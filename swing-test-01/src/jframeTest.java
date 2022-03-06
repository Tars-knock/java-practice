import jdk.jshell.execution.JdiExecutionControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class jframeTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            SimpleFrame frame = null;

            try {
                frame = new SimpleFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
           frame.setLocation(200,200);
           frame.setTitle("Tars-knock.cn");
        });


    }
}
class SimpleFrame extends JFrame{

    private static final int DEFAULT_WIDTH = 574;
    private static final int DEFAULT_HIGHT = 574;
    private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_HIGHT);
//    public SimpleFrame(){
//        setSize(DEFAULT_SIZE);
//    }
    public SimpleFrame() throws Exception {
        //设置主题
        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        UIManager.setLookAndFeel(lookAndFeel);
        setSize(DEFAULT_SIZE);
        setLayout(new BorderLayout());

        var aButton = new JButton("aButton");   //按钮
        var buttonPanel = new JPanel();            //按钮容器
        buttonPanel.add(aButton);
        add(buttonPanel, BorderLayout.SOUTH);



        var buttonListener = new listener();    //创建事件监听器
        aButton.addActionListener(buttonListener);  //为按钮绑定事件监听器

        this.addWindowListener(new frameLister());  //为frame添加窗口事件监听器

//        add(new DrawImage());
//        pack();     这句可能导致窗口没有尺寸？
    }

}


//简单的事件监听器类
class listener implements ActionListener{
    private int a = 0;
    public void actionPerformed(ActionEvent event){
        System.out.println("点击按钮"+ a++);
    }
}

//窗口事件监听器类
class frameLister extends WindowAdapter{
    public void windowClosing(WindowEvent e){
        System.out.println("退出程序");
    }
    public void windowIconified(WindowEvent e){
        System.out.println("最小化程序");
    }
}


//一个失败的图像显示component
class DrawImage extends JComponent{

    private static final int DEFAULT_WIDTH = 574;
    private static final int DEFAULT_HIGHT = 574;

    public void paintComponent(Graphics g){
        var g2 = (Graphics2D) g;
        String file = "./img/test.jpg";
        Image image = new ImageIcon(file).getImage();
        System.out.println(g2.drawImage(image,0 , 0, null));
    }
    public Dimension getPreferreSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HIGHT);
    }
}
