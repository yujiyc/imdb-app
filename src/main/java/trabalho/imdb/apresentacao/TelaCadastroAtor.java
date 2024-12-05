package trabalho.imdb.apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import trabalho.imdb.exception.InsertException;
import trabalho.imdb.exception.SelectException;
import trabalho.imdb.negocio.Sistemaa;

public class TelaCadastroAtor extends JFrame {
    private JPanel painel = new JPanel();
    private Sistemaa sistema = Sistemaa.getInstance();
    private JTextField caixaTexto = new JTextField();
    private JButton botaoCadastrar = new JButton("CADASTRAR");
    private JPanel painelCadastro = new JPanel();

    public TelaCadastroAtor() {
        setBounds(100, 100, 320, 170);
        setResizable(false);
        setTitle("Cadastro Ator");

        setContentPane(painel);
        painel.setLayout(null);

        cadastro();
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "    Ator cadastrado com sucesso!", "", JOptionPane.DEFAULT_OPTION);
    }

    public void cadastro() {
        painelCadastro.setLayout(null);
        painelCadastro.setSize(300, 110);
        painelCadastro.setLocation(10, 10);
        painelCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite o nome do ator:"));
        add(painelCadastro);

        caixaTexto.setBounds(50, 30, 200, 20);
        painelCadastro.add(caixaTexto);

        botaoCadastrar.setBounds(50, 60, 200, 25);
        painelCadastro.add(botaoCadastrar);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    sistema.cadastrarAtor(caixaTexto.getText());
                    JOptionPane.showMessageDialog(null, "Ator cadastrado com sucesso!", "", JOptionPane.DEFAULT_OPTION);
                } catch (InsertException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar Ator!", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                } catch (SelectException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Ator!", "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                    // e.printStackTrace();
                }
            }
        });
    }
}
