/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author seven
 */
public class ColumnListenerObj extends MouseAdapter{
    protected JTable table;
    protected int sortCol = 0;
    protected boolean isSortAsc = true;
    protected List linhas;
    
    public ColumnListenerObj(JTable t,List<Object> linhas) {
      table = t;
      this.linhas=linhas;
    }

   public ColumnListenerObj(JTable t,List<Object> linhas,boolean z) {
      table = t;
      this.linhas=linhas;
    }
        
    @Override
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
      TableColumnModel colModel = table.getColumnModel();
      int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
      int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();
      if (modelIndex < 0)
        return;
      if (sortCol == modelIndex)
        isSortAsc = !isSortAsc;
      else
        sortCol = modelIndex;

      for (int i = 0; i < table.getColumnCount(); i++) { 
        TableColumn column = colModel.getColumn(i);
        column.setHeaderValue(table.getColumnName(column.getModelIndex()));
      }
      table.getTableHeader().repaint();

      Collections.sort(this.linhas,new ColumnSorterObj(isSortAsc,table.getColumnName(columnModelIndex)));
      table.tableChanged(new TableModelEvent(table.getModel()));
      table.repaint();
    }
  }