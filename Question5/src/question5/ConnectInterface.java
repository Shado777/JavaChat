package question5;

import java.rmi.Remote; 
import java.util.List;

public interface ConnectInterface extends Remote{
    
    public void insert(int mID, String mName, String mSurname, int mAge, int mCellNumber, String  mDegree) throws Exception; 
}
