package trabalho.imdb.apresentacao;

import javax.swing.JFrame;
// import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaMostraIdFilme extends JFrame {
    // private JPanel painel = new JPanel();
    private IdFilmeTableModel idFilmeTableModel = new IdFilmeTableModel();
    private JTable tabela = new JTable();
    private JScrollPane scroll = new JScrollPane(tabela);

    public TelaMostraIdFilme() {
        setBounds(100, 100, 150, 200);
        setResizable(false);

        // setContentPane(painel);
        // painel.setLayout(null);

        tabela.setModel(idFilmeTableModel);
        tabela.setFillsViewportHeight(true);
        // tabela.setSize(870, 400);
        tabela.getColumnModel().getColumn(0).setMaxWidth(150);
        // tabela.getColumnModel().getColumn(1).setMaxWidth(280);
        // tabela.getColumnModel().getColumn(2).setMaxWidth(50);
        // tabela.getColumnModel().getColumn(3).setMaxWidth(50);
        // tabela.getColumnModel().getColumn(4).setMaxWidth(370);
        add(scroll);
        // painel.add(tabela);
    }
}
