package database.model;

import java.util.Vector;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

 
public class MyColumnModelItem extends DefaultTableColumnModel {
    
    private Object[] columnData = new Object[]{
        "ID", 100, //column 1: header value, width
        "NOME", 50, //column 2: header value, width
        "PREÇO", 50, //column 3: header value, width
        "ICON", 50 //column 4: header value, width
    };
 
    public MyColumnModelItem() {
        super();
        for (int i = 0; i < columnData.length; i += 2) {
            String title = (String) columnData[i];
            Integer width = (Integer) columnData[i + 1];
            TableColumn tableColumn = new TableColumn(i / 2, width);
            tableColumn.setHeaderValue(title);
            addColumn(tableColumn);
        }
    }
 
    //this getter is needed because it is missing in DefaultTableColumnModel:
    public Vector<TableColumn> getTableColumns() {
        return tableColumns;
    }
 
    //this setter is needed because it is missing in DefaultTableColumnModel:
    public void setTableColumns(Vector<TableColumn> tableColumns) {
        this.tableColumns = tableColumns;
    }
}