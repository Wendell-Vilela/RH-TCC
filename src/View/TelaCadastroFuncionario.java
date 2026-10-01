package view;

import java.awt.*;
import javax.swing.*;

public class TelaCadastroFuncionario extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField id = new JTextField(7);
    private final JTextField nome = new JTextField(40);
    private final JTextField matricula = new JTextField(15);
    private final JTextField cargo = new JTextField(30);
    private final JTextField departamento = new JTextField(30);
    private final JTextField email = new JTextField(40);
    private final JTextField telefone = new JTextField(15);

    private final JComboBox<String> status = new JComboBox<>(
        new String[]{
            "Selecione o status",
            "Ativo",
            "Afastado",
            "Ferias",
            "Desligado"
        }
    );

    public TelaCadastroFuncionario() {

        setLayout(new BorderLayout());

        setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        montar();
    }

    private void montar() {

        JPanel formulario = new JPanel(new GridBagLayout());

        formulario.setBorder(
            BorderFactory.createTitledBorder("Cadastro de Funcionario")
        );

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        componente(formulario, g, 0, "Codigo:", id);
        componente(formulario, g, 1, "Nome Completo*:", nome);
        componente(formulario, g, 2, "Matricula:", matricula);
        componente(formulario, g, 3, "Cargo:", cargo);
        componente(formulario, g, 4, "Departamento:", departamento);
        componente(formulario, g, 5, "E-mail:", email);
        componente(formulario, g, 6, "Telefone:", telefone);
        componente(formulario, g, 7, "Status:", status);

        id.setEditable(false);

        JPanel botoes = new JPanel(
            new FlowLayout(FlowLayout.LEFT)
        );

        botoes.add(new JButton("Novo"));
        botoes.add(new JButton("Salvar"));
        botoes.add(new JButton("Excluir"));
        botoes.add(new JButton("Limpar"));

        JPanel conteudo = new JPanel(new BorderLayout());

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