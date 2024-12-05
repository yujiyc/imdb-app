package trabalho.imdb.apresentacao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class TelaMostraFilmes extends JFrame {
    private JPanel painel = new JPanel();
    private FilmeTableModel filmeTableModel = new FilmeTableModel();
    private JTable tabela = new JTable();

    public TelaMostraFilmes() {
        setBounds(100, 100, 870, 400);
        setResizable(false);
        setTitle("Mostrar Filmes");

        setContentPane(painel);
        painel.setLayout(null);

        tabela.setModel(filmeTableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setSize(870, 400);
        tabela.getColumnModel().getColumn(0).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMaxWidth(280);
        tabela.getColumnModel().getColumn(2).setMaxWidth(50);
        tabela.getColumnModel().getColumn(3).setMaxWidth(50);
        tabela.getColumnModel().getColumn(4).setMaxWidth(370);
        painel.add(tabela);
    }

}
