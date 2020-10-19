package question4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame{
    
     private JPanel pn1,pn2,pn3;
    private JLabel servLb;
    private JTextField inputFld;
    private JTextArea outFld;
    private JButton sendBtn, exitBtn;
    private Box horBox1,horBox2,horBoxArea;
    private Box vertBox1,vertBox2;
    
    public Client() {
        //JFrame
        this.setTitle("Client Chat");
        this.setSize(600,380);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //JPanel
        pn1 = new JPanel();
        pn2 = new JPanel();
        pn3 = new JPanel(new GridLayout(5,5,5,5));
        
        //JLabel
        servLb = new JLabel("Client");
        servLb.setHorizontalAlignment(JLabel.LEFT);
        
        //JTextfields and area
        inputFld = new JTextField(20);
        outFld = new JTextArea(12,20);
        
        //JButton
        sendBtn = new JButton("Send");
        exitBtn = new JButton("Exit");
        
        //Box Layout
        //horisontal boxes
                
        horBox1 = Box.createHorizontalBox();
        horBox1.add(Box.createHorizontalStrut(5));
        horBox1.add(inputFld);
        horBox1.add(Box.createHorizontalStrut(5));
        horBox1.add(sendBtn);
        
        horBox2 = Box.createHorizontalBox();
        horBox2.add(Box.createHorizontalStrut(230));
        horBox2.add(exitBtn);
      
        horBoxArea = Box.createHorizontalBox();
        horBoxArea.add(Box.createHorizontalStrut(-5));
        horBoxArea.add(outFld);
        horBoxArea.add(Box.createHorizontalStrut(10));
        
        //vertical boxes
        vertBox1 = Box.createVerticalBox();
        vertBox1.add(servLb);
        vertBox1.add(Box.createVerticalStrut(40));
        vertBox1.add(horBox1);
        vertBox1.add(Box.createVerticalStrut(120));
        vertBox1.add(horBox2);
        
        vertBox2 = Box.createVerticalBox();
        vertBox2.add(Box.createVerticalStrut(20));
        vertBox2.add(horBoxArea);
        vertBox2.add(Box.createVerticalStrut(20));
        
        //adding to panel
        
        pn1.add(vertBox1);
        pn2.add(vertBox2);
        
        //JFrame
        this.add(pn3, BorderLayout.NORTH);
        this.add(pn1, BorderLayout.WEST);
        this.add(pn2, BorderLayout.EAST);
        this.setVisible(true); 
    }
    
    public static void main(String[]args) {
        Client client = new Client();
    }
}
