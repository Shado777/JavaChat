package question5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends JFrame {
    
    private JLabel appLb, devLb, idLb, nameLb, surnameLb, ageLb, phoneLb, selLb;
    private JTextField idFld, nameFld, surnameFld, ageFld, phoneFld;
    private JComboBox selBox;
    private JButton regBtn;
    
    //static
    static int mID;
    static String mName;
    static String mSurname;
    static int mAge;
    static int mPhone;
    static String mDegree;
    
    public Client() {
    
        //JFrame
        this.setTitle("");
        this.setSize(540,360);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(8,2,5,5)); 
        
        //JLabels
        appLb = new JLabel("PIHE Registration App v1.0"); 
        devLb = new JLabel("Developer: Rohan Viljoen"); 
        idLb = new JLabel("ID Number:"); 
        nameLb = new JLabel("First Name:"); 
        surnameLb = new JLabel("Surname:"); 
        ageLb = new JLabel("Age:"); 
        phoneLb = new JLabel("Cell Number:"); 
        selLb = new JLabel("Select Degree:");
        
        //JTextFields
        idFld = new JTextField();
        nameFld = new JTextField();
        surnameFld = new JTextField();
        ageFld = new JTextField();
        phoneFld = new JTextField();
        
        //ComboBox
        String[] degrees = {"BSC IT","BSc Computer Science","Higher Certificate in IT",
        "BSc Biomedicine","Bachelor of Commerce","Bachelor of Arts"};
        selBox = new JComboBox(degrees);
        
        //JButton
        regBtn = new JButton("Register"); 
        regBtn.addActionListener(new register()); 
                
        //Adding to JFrame
        this.add(appLb);
        this.add(devLb);
        this.add(idLb);
        this.add(idFld);
        this.add(nameLb);
        this.add(nameFld);
        this.add(surnameLb);
        this.add(surnameFld);
        this.add(ageLb);
        this.add(ageFld);
        this.add(phoneLb);
        this.add(phoneFld);
        this.add(selLb);
        this.add(selBox);
        this.add(regBtn);
        
        //JFrame
        this.setVisible(true);
    }
    
    class register implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String idTxt = idFld.getText();
            mName = nameFld.getText();
            mSurname = surnameFld.getText();
            String ageTxt = ageFld.getText();
            String phoneTxt = phoneFld.getText();
            mDegree = selBox.getSelectedItem().toString();
            
            if(idFld.getText().isEmpty() || nameFld.getText().isEmpty() || surnameFld.getText().isEmpty() || ageFld.getText().isEmpty() || phoneFld.getText().isEmpty()) {
            
                JOptionPane.showMessageDialog(null, "One or more fields are empty","Error",JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    
                    mID = Integer.parseInt(idTxt);
                    mAge = Integer.parseInt(ageTxt);
                    mPhone = Integer.parseInt(phoneTxt);
                    
                    Registry reg = LocateRegistry.getRegistry(null);
                    ConnectionImplement imp = new ConnectionImplement();
                    try {
                        ConnectInterface conInt = (ConnectInterface) reg.lookup("insert");
                    } catch (NotBoundException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AccessException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        imp.insert(mID, mName, mSurname, mAge, mPhone, mDegree);
                    } catch (Exception ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                JOptionPane.showMessageDialog(null, "Student has been registered sucessfully","Success",JOptionPane.INFORMATION_MESSAGE);
            }  
        }
            
    }
    
    public static void main(String[]args) throws NotBoundException {
    
        Client client = new Client();
        
    }
}
