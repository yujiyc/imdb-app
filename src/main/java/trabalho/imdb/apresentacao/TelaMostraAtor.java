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

public class TelaMostraAtor extends JFrame {
    private JPanel painel = new JPanel();
    private Sistemaa sistema = Sistemaa.getInstance();
    private JTextField caixaTexto = new JTextField();
    private JPanel painelCadastro = new JPanel();
    private JPanel painelMostra = new JPanel();
    private JButton botaoCadastrar = new JButton("BUSCAR");
    private JButton botaoId = new JButton("MOSTRAR ID");

    public TelaMostraAtor() {
        setBounds(100, 100, 320, 430);
        setResizable(false);
        setTitle("Mostra Ator");

        setContentPane(painel);
        painel.setLayout(null);

        procuraFilme();
    }

    public void procuraFilme() {
        painelCadastro.setLayout(null);
        painelCadastro.setSize(300, 140);
        painelCadastro.setLocation(10, 10);
        painelCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite o id do ator:"));
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
                TelaMostraIdAtor mostraId = new TelaMostraIdAtor();
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
        painelMostra.setSize(300, 146);
        painelMostra.setLocation(10, 140);
        painelMostra.setBorder(javax.swing.BorderFactory.createTitledBorder("Ator encontrado:"));
        add(painelMostra);
        try {
            JLabel textoId = new JLabel(sistema.mostrarAtor(id).getId());
            textoId.setBounds(50, 30, 200, 20);
            textoId.setHorizontalAlignment(JLabel.CENTER);
            // textoId.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoId);

            JLabel textoNome = new JLabel(sistema.mostrarAtor(id).getNome());
            textoNome.setBounds(50, 49, 200, 20);
            textoNome.setHorizontalAlignment(JLabel.CENTER);
            // textoNome.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoNome);

            JLabel textoData = new JLabel(sistema.mostrarAtor(id).getDataNascimento());
            textoData.setBounds(50, 68, 200, 20);
            textoData.setHorizontalAlignment(JLabel.CENTER);
            // textoRanking.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoData);

            JLabel textoSexo = new JLabel(sistema.mostrarAtor(id).getSexo());
            textoSexo.setBounds(50, 87, 200, 20);
            textoSexo.setHorizontalAlignment(JLabel.CENTER);
            // textoAno.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoSexo);

            JLabel textoBio = new JLabel(sistema.mostrarAtor(id).getBiografia());
            textoBio.setBounds(50, 106, 200, 20);
            textoBio.setHorizontalAlignment(JLabel.CENTER);
            // textoAtor.setBorder(BorderFactory.createLineBorder(Color.black));
            painelMostra.add(textoBio);

        } catch (SelectException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar Ator!", "Erro", JOptionPane.INFORMATION_MESSAGE);
            // e.printStackTrace();
        }

    }
}