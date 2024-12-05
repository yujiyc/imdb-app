package trabalho.imdb.apresentacao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class TelaMostraAtores extends JFrame {
    private JPanel painel = new JPanel();
    private AtorTableModel atorTableModel = new AtorTableModel();
    private JTable tabela = new JTable();

    public TelaMostraAtores() {
        setBounds(100, 100, 870, 400);
        setResizable(false);
        setTitle("Mostrar Atores");

        setContentPane(painel);
        painel.setLayout(null);

        tabela.setModel(atorTableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setSize(870, 400);
        tabela.getColumnModel().getColumn(0).setMaxWidth(140);
        tabela.getColumnModel().getColumn(1).setMaxWidth(140);
        tabela.getColumnModel().getColumn(2).setMaxWidth(100);
        tabela.getColumnModel().getColumn(3).setMaxWidth(60);
        tabela.getColumnModel().getColumn(4).setMaxWidth(430);
        painel.add(tabela);
    }

}
