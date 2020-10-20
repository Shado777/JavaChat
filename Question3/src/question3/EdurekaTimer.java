package question3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class EdurekaTimer extends JFrame{
    
    static int mills =0;
    static int sec =0;
    static boolean pause = false;
    private JPanel pn1;
    private JLabel timerLb;
    private JTextField timerFld;
    private JButton startBtn, stopBtn;
    private Box horBox1,horBox2,vertBox1;
    
    public EdurekaTimer() {
    
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
        timerFld.setEditable(false); 
        
        //JButton
        startBtn = new JButton("Start Timer");
        startBtn.addActionListener(new startThread()); 
        
        stopBtn = new JButton("Stop Timer");
        stopBtn.addActionListener(new pause()); 
        
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
    
    class startThread implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pause = false;
            
            Thread timer = new Thread(){
                public void run() {
                
                    //for loop to infinite count when there is no pause
                    for(;;) {
                        if(pause == false){
                        
                         try{
                            sleep(1); //thread counts in seconds
                            if(mills > 1000) { //thousand milliseconds are a second
                                mills = 0;
                                sec++;
                            }
                            mills++;
                            timerFld.setText(""+sec);
                        }
                        catch(Exception e) {}
                        
                        }
                        else{
                            break;
                        }
                    }
                }
            };
            timer.start();
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
        
    }
}
