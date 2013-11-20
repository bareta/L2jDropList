package database.model;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
 
    //the default constructor is needed (there is no default constructor in DefaultTableModel):
    public MyTableModel() {
        super(0, 4);
    }
 
    //this setter is needed because it is missing in DefaultTableModel:
    public void setDataVector(Vector vector) {
        dataVector = vector;
    }
 
    @Override
    public Vector getDataVector() {
        return dataVector;
    }    
    //this getter is needed because it is missing in DefaultTableModel:
    public Vector getColumnIdentifiers() {
        return columnIdentifiers;
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        }
        return String.class;
    }
    
    
}