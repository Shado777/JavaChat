package question4;

import java.awt.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Server extends JFrame{
    
    private JPanel pn1,pn2,pn3;
    private JLabel servLb;
    private JTextField inputFld;
    private static JTextArea outFld;
    private JButton sendBtn, exitBtn;
    private Box horBox1,horBox2,horBoxArea;
    private Box vertBox1,vertBox2;
    public static DataInputStream input;
    public static DataOutputStream output;
    
    public Server() {
 
       
        //JFrame
        this.setTitle("Server Chat");
        this.setSize(600,380);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //JPanel
        pn1 = new JPanel();
        pn2 = new JPanel();
        pn3 = new JPanel(new GridLayout(2,2,5,5));
        
        //JLabel
        servLb = new JLabel("Server");
        servLb.setHorizontalAlignment(JLabel.LEFT);
        
        //JTextfields and area
        inputFld = new JTextField(20);
        outFld = new JTextArea(12,20);
        
        //JButton
        sendBtn = new JButton("Send");
        sendBtn.addActionListener(new send()); 
        
        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new exit()); 
        
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
    
    class send implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String sendTxt = inputFld.getText();
            String servTime = new SimpleDateFormat("HH.mm.ss").format(new Date());
            String sendFor = servTime+" Server "+sendTxt+"\n";
            
            try { 
                output.writeUTF(sendFor);
                outFld.append(sendFor); 
                inputFld.setText(null); 
            } 
            catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        }
    }
    
    class exit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    public static void main(String[]args) {
        Server serv = new Server();
        System.out.println("Server starting");
        try {
            ServerSocket serv1 = new ServerSocket(9000);
            Socket ser2 = serv1.accept();
            
            //sending
            input = new DataInputStream(ser2.getInputStream());
            output = new DataOutputStream(ser2.getOutputStream());
            String ins = input.readUTF();
            outFld.append(ins);
                     
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
