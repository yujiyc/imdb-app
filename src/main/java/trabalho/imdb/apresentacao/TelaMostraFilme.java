package trabalho.imdb.apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.Color;

// import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import trabalho.imdb.exception.SelectException;
import trabalho.imdb.negocio.Sistemaa;

public class TelaMostraFilme extends JFrame {
    private JPanel painel = new JPanel();
    private Sistemaa sistema = Sistemaa.getInstance();
    private JTextField caixaTexto = new JTextField();
    private JPanel painelCadastro = new JPanel();
    private JPanel painelMostra = new JPanel();
    private JButton botaoCadastrar = new JButton("BUSCAR");
    private JButton botaoId = new JButton("MOSTRAR ID");

    public TelaMostraFilme() {
        setBounds(100, 100, 320, 430);
        setResizable(false);
        setTitle("Mostra Filme");

        setContentPane(painel);
        painel.setLayout(null);

        procuraFilme();
    }

    public void procuraFilme() {
        painelCadastro.setLayout(null);
        painelCadastro.setSize(300, 140);
        painelCadastro.setLocation(10, 10);
        painelCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite o id do filme:"));
        add(painelCadastro);

        caixaTexto.setBounds(50, 30, 200, 20);
        painelCadastro.add(caixaTexto);

        botaoCadastrar.setBounds(50, 60, 200, 25);
        painelCadastro.add(botaoCadastrar);

        botaoId.setBounds(50, 95, 200, 25);
        painelCadastro.add(botaoId);

        botaoId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                TelaMostraIdFilme mostraId = new TelaMostraIdFilme();
                mostraId.setVisible(true);
            }
        });

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                mostra(caixaTexto.getText());
            }
        });
    }

    public void mostra(String id) {
        painelMostra.setLayout(null);
        painelMostra.setSize(300, 186);
        painelMostra.setLocation(10, 170);
        painelMostra.setBorder(javax.swing.BorderFactory.createTitledBorder("Filme encontrado:"));
        add(painelMostra);
        try {
            JLabel textoId = new JLabel(sistema.mostrarFilme(id).getId());
            textoId.setBounds(50, 30, 200, 20);
            textoId.setHorizontalAlignment(JLabel.CENTER);
            // textoId.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoId);

            JLabel textoNome = new JLabel(sistema.mostrarFilme(id).getNome());
            textoNome.setBounds(50, 49, 200, 20);
            textoNome.setHorizontalAlignment(JLabel.CENTER);
            // textoNome.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoNome);

            JLabel textoRanking = new JLabel(sistema.mostrarFilme(id).getRanking());
            textoRanking.setBounds(50, 68, 200, 20);
            textoRanking.setHorizontalAlignment(JLabel.CENTER);
            // textoRanking.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoRanking);

            JLabel textoAno = new JLabel(sistema.mostrarFilme(id).getAno());
            textoAno.setBounds(50, 87, 200, 20);
            textoAno.setHorizontalAlignment(JLabel.CENTER);
            // textoAno.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoAno);

            JLabel textoAtor = new JLabel(sistema.mostrarFilme(id).getElencoPrincipal().get(0).getNome());
            textoAtor.setBounds(50, 106, 200, 20);
            textoAtor.setHorizontalAlignment(JLabel.CENTER);
            // textoAtor.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoAtor);
            JLabel textoAtor2 = new JLabel(sistema.mostrarFilme(id).getElencoPrincipal().get(1).getNome());
            textoAtor2.setBounds(50, 125, 200, 20);
            textoAtor2.setHorizontalAlignment(JLabel.CENTER);
            // textoAtor2.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoAtor2);
            JLabel textoAtor3 = new JLabel(sistema.mostrarFilme(id).getElencoPrincipal().get(2).getNome());
            textoAtor3.setBounds(50, 144, 200, 20);
            textoAtor3.setHorizontalAlignment(JLabel.CENTER);
            // textoAtor3.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoAtor3);

        } catch (SelectException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar Filme!", "Erro", JOptionPane.INFORMATION_MESSAGE);
            // e.printStackTrace();
        }

    }
}
