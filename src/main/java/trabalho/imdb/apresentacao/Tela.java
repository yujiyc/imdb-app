package trabalho.imdb.apresentacao;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela extends JFrame {
    private JPanel painel = new JPanel();
    private JComboBox<Menu> comboBox = new JComboBox<Menu>(Menu.values());

    public Tela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 570, 300);
        setResizable(false);
        setTitle("Consulta IMDB");
        setContentPane(painel);
        painel.setLayout(null);

        ImageIcon foto = new ImageIcon(getClass().getResource("imdb.png"));
        JLabel label = new JLabel(foto);
        label.setBounds(178,10,213,50);
        add(label);

        menu();
    }

    public void menu() {
        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(null);
        painelMenu.setSize(250, 110);
        painelMenu.setLocation(160, 80);
        painelMenu.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        add(painelMenu);

        comboBox.setBounds(50, 30, 150, 20);
        painelMenu.add(comboBox);

        JButton botaoGerador = new JButton("CONFIRMAR");
        botaoGerador.setBounds(50, 60, 150, 20);
        painelMenu.add(botaoGerador);

        botaoGerador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Menu.CADASTRARFILME == (Menu) comboBox.getSelectedItem()) {
                    TelaCadastroFilme cadastroFilme = new TelaCadastroFilme();
                    cadastroFilme.setVisible(true);
                }
                if (Menu.CADASTRARATOR == (Menu) comboBox.getSelectedItem()) {
                    TelaCadastroAtor cadastroAtor = new TelaCadastroAtor();
                    cadastroAtor.setVisible(true);
                }
                if (Menu.MOSTRARATORES == (Menu) comboBox.getSelectedItem()) {
                    TelaMostraAtores mostraAtores = new TelaMostraAtores();
                    mostraAtores.setVisible(true);
                }
                if (Menu.MOSTRARFILMES == (Menu) comboBox.getSelectedItem()) {
                    TelaMostraFilmes mostraFilmes = new TelaMostraFilmes();
                    mostraFilmes.setVisible(true);
                }
                if (Menu.MOSTRARFILME == (Menu) comboBox.getSelectedItem()) {
                    TelaMostraFilme mostraFilme = new TelaMostraFilme();
                    mostraFilme.setVisible(true);
                }
                if (Menu.MOSTRARATOR == (Menu) comboBox.getSelectedItem()) {
                    TelaMostraAtor mostraAtor = new TelaMostraAtor();
                    mostraAtor.setVisible(true);
                }
            }
        });
    }
}
