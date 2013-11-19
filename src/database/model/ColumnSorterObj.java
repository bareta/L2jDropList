/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.model;

import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author seven
 */
public class ColumnSorterObj  implements Comparator {
    int colIndex;
    boolean ascending;
    String ColName;
    
    public ColumnSorterObj(int colIndex, boolean ascending, String colName) {
        this.colIndex = colIndex;
        this.ascending = ascending;
        this.ColName = colName;
    }

    public ColumnSorterObj(boolean ascending,String colName) {
        this.ascending = ascending;
        this.ColName = colName;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public int compare(Object a, Object b) {

        Object o1 = null;
        Object o2 = null;
     
        if (o1 instanceof String && ((String)o1).length() == 0) {
            o1 = null;
        }
        if (o2 instanceof String && ((String)o2).length() == 0) {
            o2 = null;
        }
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        } else if (o1 instanceof Date){           
            if(ascending){
                return ((Date)o1).before((Date)o2) ? -1 : 0;
            } else {
                return ((Date)o2).before((Date)o1) ? -1 : 0;
            }        
        } else if (o1 instanceof Comparable){
            if (ascending) {
                return ((Comparable)o1).compareTo(o2);
            } else {
                return ((Comparable)o2).compareTo(o1);
            }
        } else {
            if (ascending) {
                return o1.toString().compareTo(o2.toString());
            } else {
                return o2.toString().compareTo(o1.toString());
            }
        }
    }
}