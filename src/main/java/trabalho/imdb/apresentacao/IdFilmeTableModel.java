package trabalho.imdb.apresentacao;

import javax.swing.table.AbstractTableModel;

import trabalho.imdb.exception.SelectException;
import trabalho.imdb.negocio.Sistemaa;

public class IdFilmeTableModel extends AbstractTableModel {
    private Sistemaa sistema = Sistemaa.getInstance();
    private String[] colunas = { "ID" };

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.mostrarFilmes().size();
        } catch (SelectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int pos, int arg1) {
        try {
            return sistema.mostrarFilmes().get(pos).getId();
        } catch (SelectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
