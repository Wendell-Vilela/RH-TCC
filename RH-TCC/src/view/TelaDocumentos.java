package view;

import java.awt.*;
import javax.swing.*;

public class TelaDocumentos extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField cpf = new JTextField(20);
    private final JTextField rg = new JTextField(20);

    private final JTextField passaporte = new JTextField(20);
    private final JTextField validadePassaporte = new JTextField(20);

    private final JTextField pis = new JTextField(20);
    private final JTextField ctps = new JTextField(20);

    public TelaDocumentos() {

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
            BorderFactory.createTitledBorder("Documentos")
        );

        GridBagConstraints g =
            new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        componente(formulario, g, 0, "CPF:", cpf);
        componente(formulario, g, 1, "RG:", rg);
        componente(formulario, g, 2, "Passaporte:", passaporte);
        componente(formulario, g, 3, "Validade:", validadePassaporte);
        componente(formulario, g, 4, "PIS:", pis);
        componente(formulario, g, 5, "CTPS:", ctps);

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