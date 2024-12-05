package trabalho.imdb.apresentacao;

import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;

import trabalho.imdb.exception.SelectException;
import trabalho.imdb.negocio.Sistemaa;

public class AtorTableModel extends AbstractTableModel {
    private Sistemaa sistema = Sistemaa.getInstance();
    private String[] colunas = { "ID", "Nome", "DataNascimento", "Sexo", "Biografia" };

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.mostrarAtores().size();
        } catch (SelectException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar Ator!", "Erro", JOptionPane.INFORMATION_MESSAGE);
            // e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                try {
                    return sistema.mostrarAtores().get(linha).getId();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Ator!", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            case 1:
                try {
                    return sistema.mostrarAtores().get(linha).getNome();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Ator!", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            case 2:
                try {
                    return sistema.mostrarAtores().get(linha).getDataNascimento();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Ator!", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            case 3:
                try {
                    return sistema.mostrarAtores().get(linha).getSexo();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Ator!", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            case 4:
                try {
                    return sistema.mostrarAtores().get(linha).getBiografia();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Ator!", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            default:
                break;
        }
        return null;
    }

    public String getColumnName(int num) {
        return this.colunas[num];
    }
}
