/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.group2;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author enuj
 */
class MyModel extends AbstractTableModel {
    
     // TableModel's column names
    //Employee Number, Last Name, First Name, SSS No., PhilHealth No., TIN, and Pagibig No.
//        private final String[] columnNames = {
//                "Emp Number", "Last Name", "First Name", "SSS Number", "Philhealth Number", "TIN", "Pagibig Number"
//        };

        public String data[][] = Utils.GetData();

        /**
         * Returns the number of rows in the table model.
         */
        public int getRowCount() {
            return data.length;
        }
        
        private final String[] columnNames = data[0];

        /**
         * Returns the number of columns in the table model.
         */
        public int getColumnCount() {
            return columnNames.length;
        }

        /**
         * Returns the column name for the column index.
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        /**
         * Returns data type of the column specified by its index.
         */
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        /**
         * Returns the value of a table model at the specified
         * row index and column index.
         */
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
    
    
}
