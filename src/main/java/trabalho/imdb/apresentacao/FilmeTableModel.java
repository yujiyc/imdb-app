package trabalho.imdb.apresentacao;

import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;

import trabalho.imdb.exception.SelectException;
import trabalho.imdb.negocio.Sistemaa;

public class FilmeTableModel extends AbstractTableModel {
    private Sistemaa sistema = Sistemaa.getInstance();
    private String[] colunas = { "ID", "Nome", "Ranking", "Ano", "Elenco" };

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    // E AGORA?
    @Override
    public int getRowCount() {
        try {
            return sistema.mostrarFilmes().size();
        } catch (SelectException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar Filme.", "Erro", JOptionPane.INFORMATION_MESSAGE);
            // e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                try {
                    return sistema.mostrarFilmes().get(linha).getId();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Filme.", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            case 1:
                try {
                    return sistema.mostrarFilmes().get(linha).getNome();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Filme.", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            case 2:
                try {
                    return sistema.mostrarFilmes().get(linha).getRanking();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Filme.", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            case 3:
                try {
                    return sistema.mostrarFilmes().get(linha).getAno();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Filme.", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            case 4:
                try {
                    String retorno = "";
                    for (int i = 0; i < sistema.mostrarFilmes().get(linha).getElencoPrincipal().size(); i++) {
                        retorno += sistema.mostrarFilmes().get(linha).getElencoPrincipal().get(i).getNome() + "  ";
                    }
                    return retorno;
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Filme.", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }

            default:
                break;
        }
        return null;
    }
}
