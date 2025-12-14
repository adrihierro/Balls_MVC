package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DataPanel extends JTable {
    private int numpelotas = 0;
    private DefaultTableModel modelTable;

    public DataPanel(){

        String[] nombreColumnas = {"dato","valor"};

        Object[][] datos = {
                {"Numero de pelotas",numpelotas},
        };

        modelTable = new DefaultTableModel(datos, nombreColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }

        };

        setRowHeight(25);

        setModel(modelTable);
        setFillsViewportHeight(true);
    }

    public void setNumpelotas(int numpelotas) {
        this.numpelotas = numpelotas;
        modelTable.setValueAt(numpelotas,0,1);
    }
}
