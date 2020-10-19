package question3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class EdurekaTimer extends JFrame implements Runnable{
    
    private String threadName;
    private Thread timerRun;
    private boolean pause;
    private JPanel pn1;
    private JLabel timerLb;
    private JTextField timerFld;
    private JButton startBtn, stopBtn;
    private Box horBox1,horBox2,vertBox1;
    
    public EdurekaTimer() {
    
        pause = true;
        //JFrame
        this.setTitle("Edureka Timer"); 
        this.setSize(300,130);
        this.setResizable(false); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //JPanel
        pn1 = new JPanel();
        
        //JLabel
        timerLb = new JLabel("Time(in seconds)");
        
        //JTextfield
        timerFld = new JTextField(10);
        timerFld.setText("0"); 
        timerFld.setEditable(false); 
        
        //JButton
        startBtn = new JButton("Start Timer");
        startBtn.addActionListener(new startThread()); 
        
        stopBtn = new JButton("Stop Timer");
        
        //Horisontal box
        horBox1 = Box.createHorizontalBox();
        horBox1.add(Box.createHorizontalStrut(15));
        horBox1.add(timerLb);
        horBox1.add(Box.createHorizontalStrut(5));
        horBox1.add(timerFld);
        horBox1.add(Box.createHorizontalStrut(15));
        
        horBox2 = Box.createHorizontalBox();
        horBox2.add(Box.createHorizontalStrut(15));
        horBox2.add(startBtn);
        horBox2.add(Box.createHorizontalStrut(5));
        horBox2.add(stopBtn);
        horBox2.add(Box.createHorizontalStrut(15));
        
        //Vertical Box
        vertBox1 = Box.createVerticalBox();
        vertBox1.add(Box.createVerticalStrut(5));
        vertBox1.add(horBox1);
        vertBox1.add(Box.createVerticalStrut(5));
        vertBox1.add(horBox2);
        vertBox1.add(Box.createVerticalStrut(5));
        
        //Adding to panel
        pn1.add(vertBox1);
        
        //Adding to JFrame
        this.add(pn1);
        this.setVisible(true); 
    }
    
    public EdurekaTimer(String threadName, boolean pause){
        this.threadName = threadName;
        this.pause = pause;
    }
    
    public boolean getPause() {
        return pause;
    }
    
    @Override
    public void run() {
        String countShow = null;
        String countTxt = timerFld.getText();
        int count = Integer.parseInt(countTxt);
        int seconds = 1;
            
        while(pause == false) {
                
          try {
            count++;
            countShow = Integer.toString(count);
            Thread.sleep(seconds);
          } 
          catch (InterruptedException ex) {
            Logger.getLogger(EdurekaTimer.class.getName()).log(Level.SEVERE, null, ex);
          }  
        }
        timerFld.setText(countShow);
    }
    public void start(){
        
        if(pause == false) {
           timerRun = new Thread(this,threadName);
           timerRun.start();
        }
    }
    
    class startThread implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pause = false;
        }
    }
    
    class pause implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            pause = true;
        }
    }
    
    public static void main(String[]args) {
    
        EdurekaTimer timer = new EdurekaTimer();
        boolean result = timer.getPause();
        if(result == false) {
            timer.start();
        }
    }
}
