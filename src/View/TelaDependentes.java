package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaDependentes extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField nome = new JTextField(30);

    private final JComboBox<String> parentesco =
        new JComboBox<>(new String[]{
            "Selecione",
            "Conjuge",
            "Filho",
            "Filha",
            "Pai",
            "Mae",
            "Outro"
        });

    private final JTextField nascimento =
        new JTextField(15);

    private final JTextField assistencia =
        new JTextField(30);

    public TelaDependentes() {

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
            BorderFactory.createTitledBorder("Cadastro de Dependente")
        );

        GridBagConstraints g =
            new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        componente(formulario, g, 0, "Beneficiario:", nome);
        componente(formulario, g, 1, "Parentesco:", parentesco);
        componente(formulario, g, 2, "Nascimento:", nascimento);
        componente(formulario, g, 3, "Assistencia:", assistencia);

        String[] colunas = {
            "Beneficiario",
            "Parentesco",
            "Nascimento",
            "Assistencia"
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