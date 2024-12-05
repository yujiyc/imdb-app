package trabalho.imdb.apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import trabalho.imdb.dados.Filme;
import trabalho.imdb.exception.InsertException;
import trabalho.imdb.exception.SelectException;
import trabalho.imdb.negocio.Sistemaa;

public class TelaCadastroFilme extends JFrame {
    private JPanel painel = new JPanel();
    private Sistemaa sistema = Sistemaa.getInstance();
    private JTextField caixaTexto = new JTextField();
    private JButton botaoCadastrar = new JButton("CADASTRAR");
    private JPanel painelCadastro = new JPanel();

    private JPanel painelListar = new JPanel();
    private List<Filme> listaRetorno = new ArrayList<Filme>();
    private int m = 0;

    public TelaCadastroFilme() {
        setBounds(100, 100, 670, 530);
        setResizable(false);
        setTitle("Cadastro Filme");

        setContentPane(painel);
        painel.setLayout(null);

        cadastro();
    }

    public void cadastro() {
        painelCadastro.setLayout(null);
        painelCadastro.setSize(300, 110);
        painelCadastro.setLocation(185, 10);
        painelCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite o nome do filme:"));
        add(painelCadastro);

        caixaTexto.setBounds(50, 30, 200, 20);
        painelCadastro.add(caixaTexto);

        botaoCadastrar.setBounds(50, 60, 200, 25);
        painelCadastro.add(botaoCadastrar);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                menu(caixaTexto.getText());
            }
        });
    }

    public void menu(String nome) {
        painelListar.setLayout(null);
        painelListar.setSize(650, 350);
        painelListar.setLocation(10, 130);
        painelListar.setBorder(javax.swing.BorderFactory.createTitledBorder("Escolha de filmes"));
        add(painelListar);

        listaRetorno = sistema.listarFilmes(nome);

        List<JCheckBox> lista = new ArrayList<JCheckBox>();
        int j = 30;
        for (int i = 0; i < listaRetorno.size(); i++) {
            JCheckBox check = new JCheckBox(
                    "ID: " + listaRetorno.get(i).getId() + " Titulo: " + listaRetorno.get(i).getNome());
            check.setBounds(10, j, 600, 20);
            check.setHorizontalAlignment(JLabel.LEFT);
            lista.add(check);
            j += 20;
            painelListar.add(check);
        }

        for (int i = 0; i < listaRetorno.size(); i++) {
            lista.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        sistema.cadastrarFilme(listaRetorno.get(m).getId(), listaRetorno.get(m).getNome());
                        m++;
                        JOptionPane.showMessageDialog(null, "   Filme cadastrado com sucesso!", "",
                                JOptionPane.DEFAULT_OPTION);
                    } catch (InsertException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar Filme.", "Erro",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.err.println(e);
                        // e.printStackTrace();
                    } catch (SelectException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao buscar Filme.", "Erro",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.err.println(e);
                        // e.printStackTrace();
                    }
                }
            });
        }
    }
}
