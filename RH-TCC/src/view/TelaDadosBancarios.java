package view;

import java.awt.*;
import javax.swing.*;

public class TelaDadosBancarios extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField banco = new JTextField(35);
    private final JTextField codigoBanco = new JTextField(10);
    private final JTextField agencia = new JTextField(15);
    private final JTextField conta = new JTextField(20);
    private final JTextField pix = new JTextField(30);

    private final JComboBox<String> tipoConta =
        new JComboBox<>(new String[]{
            "Selecione",
            "Conta Corrente",
            "Conta Poupanca",
            "Conta Salario"
        });

    public TelaDadosBancarios() {

        setLayout(new BorderLayout());

        setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        montar();
    }

    private void montar() {

        JPanel formulario =
            new JPanel(new GridBagLayout());

        formulario.setBorder(
            BorderFactory.createTitledBorder("Dados Bancarios")
        );

        GridBagConstraints g =
            new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        componente(formulario, g, 0, "Banco:", banco);
        componente(formulario, g, 1, "Codigo do Banco:", codigoBanco);
        componente(formulario, g, 2, "Agencia:", agencia);
        componente(formulario, g, 3, "Conta:", conta);
        componente(formulario, g, 4, "Tipo de Conta:", tipoConta);
        componente(formulario, g, 5, "PIX:", pix);

        JPanel botoes =
            new JPanel(new FlowLayout(FlowLayout.LEFT));

        botoes.add(new JButton("Novo"));
        botoes.add(new JButton("Salvar"));
        botoes.add(new JButton("Excluir"));
        botoes.add(new JButton("Limpar"));

        JPanel conteudo =
            new JPanel(new BorderLayout());

        conteudo.add(formulario, BorderLayout.NORTH);
        conteudo.add(botoes, BorderLayout.SOUTH);

        add(conteudo, BorderLayout.NORTH);
    }

    private void componente(
        JPanel painel,
        GridBagConstraints g,
        int linha,
        String texto,
        Component campo
    ) {

        g.gridx = 0;
        g.gridy = linha;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;

        painel.add(new JLabel(texto), g);

        g.gridx = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;

        painel.add(campo, g);
    }
}