package view;

import java.awt.*;
import javax.swing.*;

public class TelaDadosPessoais extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField nome = new JTextField(40);
    private final JTextField nascimento = new JTextField(15);

    private final JComboBox<String> estadoCivil =
        new JComboBox<>(new String[]{
            "Selecione",
            "Solteiro",
            "Casado",
            "Divorciado",
            "Viuvo"
        });

    private final JTextField naturalidade = new JTextField(30);
    private final JTextField nacionalidade = new JTextField(30);
    private final JTextField endereco = new JTextField(40);
    private final JTextField cidade = new JTextField(30);

    public TelaDadosPessoais() {

        setLayout(new BorderLayout());

        setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        montar();
    }

    private void montar() {

        JPanel formulario = new JPanel(new GridBagLayout());

        formulario.setBorder(
            BorderFactory.createTitledBorder("Dados Pessoais")
        );

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        componente(formulario, g, 0, "Nome Completo*:", nome);
        componente(formulario, g, 1, "Data de Nascimento:", nascimento);
        componente(formulario, g, 2, "Estado Civil:", estadoCivil);
        componente(formulario, g, 3, "Naturalidade:", naturalidade);
        componente(formulario, g, 4, "Nacionalidade:", nacionalidade);
        componente(formulario, g, 5, "Endereco:", endereco);
        componente(formulario, g, 6, "Cidade:", cidade);

        JPanel botoes = criarBotoes();

        JPanel conteudo = new JPanel(new BorderLayout());

        conteudo.add(formulario, BorderLayout.NORTH);
        conteudo.add(botoes, BorderLayout.SOUTH);

        add(conteudo, BorderLayout.NORTH);
    }

    private JPanel criarBotoes() {

        JPanel botoes =
            new JPanel(new FlowLayout(FlowLayout.LEFT));

        botoes.add(new JButton("Novo"));
        botoes.add(new JButton("Salvar"));
        botoes.add(new JButton("Excluir"));
        botoes.add(new JButton("Limpar"));

        return botoes;
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