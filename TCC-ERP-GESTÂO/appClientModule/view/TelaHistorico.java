package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaHistorico extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField ano = new JTextField(10);
    private final JTextField evento = new JTextField(30);
    private final JTextField cargo = new JTextField(30);
    private final JTextArea observacao = new JTextArea(3, 30);

    public TelaHistorico() {

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
            BorderFactory.createTitledBorder("Historico Profissional")
        );

        GridBagConstraints g =
            new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        componente(formulario, g, 0, "Ano:", ano);
        componente(formulario, g, 1, "Evento:", evento);
        componente(formulario, g, 2, "Cargo:", cargo);
        componente(
            formulario,
            g,
            3,
            "Observacao:",
            new JScrollPane(observacao)
        );

        String[] colunas = {
            "Ano",
            "Evento",
            "Cargo",
            "Observacao"
        };

        DefaultTableModel modelo =
            new DefaultTableModel(colunas, 0);

        JTable tabela =
            new JTable(modelo);

        JScrollPane scroll =
            new JScrollPane(tabela);

        JPanel botoes =
            new JPanel(new FlowLayout(FlowLayout.LEFT));

        botoes.add(new JButton("Novo"));
        botoes.add(new JButton("Salvar"));
        botoes.add(new JButton("Excluir"));
        botoes.add(new JButton("Limpar"));

        JPanel conteudo =
            new JPanel(new BorderLayout(0, 10));

        conteudo.add(formulario, BorderLayout.NORTH);
        conteudo.add(scroll, BorderLayout.CENTER);
        conteudo.add(botoes, BorderLayout.SOUTH);

        add(conteudo, BorderLayout.CENTER);
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