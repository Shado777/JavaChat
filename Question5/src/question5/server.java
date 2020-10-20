package question5;

import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class server extends ConnectionImplement{
    
    public server() throws RemoteException{
        super();
    }
    
    public static void main(String[]args){
        
        try{
        ConnectionImplement imp = new ConnectionImplement();
            
            ConnectInterface conInt = (ConnectInterface) UnicastRemoteObject.exportObject(imp, 0);
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.bind("insert", conInt); 
            System.err.println("Server is ready");
        }
        catch(Exception ex) {
        
        }
        
    }
    
}
