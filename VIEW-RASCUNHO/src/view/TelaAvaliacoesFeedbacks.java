package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class TelaAvaliacoesFeedbacks extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JComboBox<String> area = new JComboBox<>(
        new String[]{"Selecione a área", "Tecnologia", "Recursos Humanos", "Financeiro", "Marketing"}
    );

    private final JComboBox<String> funcionario = new JComboBox<>(
        new String[]{"Selecione o funcionário", "Josimar dos Santos", "Roberto Minelo", "Gabrielly Siqueira", "Mathias Fonseca"}
    );

    private final JButton selecionar = new JButton("Selecionar");

    private final JTextField autoavaliacao = new JTextField(5);
    private final JTextField avaliacaoGestor = new JTextField(5);
    private final JTextField avaliacaoPares = new JTextField(5);

    private final JTable tabelaFeedbacks = new JTable();

    public TelaAvaliacoesFeedbacks() {
        setBackground(Cores.FUNDO);
        setLayout(new BorderLayout(20, 20));

        // Borda principal padronizada com título
        TitledBorder bordaPrincipal = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "AVALIAÇÕES E FEEDBACKS",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                Cores.TEXTO_TITULO
        );

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                bordaPrincipal
        ));

        montar();
    }

    private void montar() {

        // Seleção do Funcionário
        JPanel selecao = new JPanel(new GridBagLayout());
        selecao.setBackground(Cores.FUNDO);
        selecao.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "Selecionar Funcionário",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                Cores.TEXTO_TITULO
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);

        componente(selecao, g, 0, "Área:", area);
        componente(selecao, g, 1, "Funcionário:", funcionario);

        g.gridx = 2;
        g.gridy = 1;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;

        selecionar.setBackground(Cores.AZUL_MENU);
        selecionar.setForeground(Cores.FUNDO);
        selecionar.setFont(new Font("SansSerif", Font.BOLD, 12));
        selecao.add(selecionar, g);

        // Avaliações
        JPanel avaliacoes = new JPanel(new GridBagLayout());
        avaliacoes.setBackground(Cores.FUNDO);
        avaliacoes.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "Avaliações",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                Cores.TEXTO_TITULO
        ));

        GridBagConstraints a = new GridBagConstraints();
        a.insets = new Insets(5, 5, 5, 5);

        componente(avaliacoes, a, 0, "Autoavaliação:", autoavaliacao);
        componente(avaliacoes, a, 1, "Avaliação do Gestor:", avaliacaoGestor);
        componente(avaliacoes, a, 2, "Avaliação dos Pares:", avaliacaoPares);

        autoavaliacao.setEditable(false);
        avaliacaoGestor.setEditable(false);
        avaliacaoPares.setEditable(false);

        // Tabela de Feedbacks
        String[] colunas = {
            "Competência",
            "Avaliador",
            "Data",
            "Feedback"
        };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaFeedbacks.setModel(modelo);
        tabelaFeedbacks.setRowHeight(35);

        // Estilização da tabela via classe utilitária
        funcaoFacilitar.estilizarTabela(tabelaFeedbacks);

        tabelaFeedbacks.getColumnModel().getColumn(0).setPreferredWidth(120);
        tabelaFeedbacks.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabelaFeedbacks.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabelaFeedbacks.getColumnModel().getColumn(3).setPreferredWidth(450);

        JScrollPane scrollTabela = new JScrollPane(tabelaFeedbacks);
        scrollTabela.setBackground(Cores.FUNDO);
        scrollTabela.getViewport().setBackground(Cores.FUNDO);
        scrollTabela.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        JPanel painelFeedbacks = new JPanel(new BorderLayout());
        painelFeedbacks.setBackground(Cores.FUNDO);
        painelFeedbacks.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "Feedbacks Recentes",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                Cores.TEXTO_TITULO
        ));

        painelFeedbacks.add(scrollTabela, BorderLayout.CENTER);

        // Estruturação do Conteúdo
        JPanel parteSuperior = new JPanel(new BorderLayout(10, 10));
        parteSuperior.setBackground(Cores.FUNDO);
        parteSuperior.add(selecao, BorderLayout.NORTH);
        parteSuperior.add(avaliacoes, BorderLayout.CENTER);

        JPanel conteudo = new JPanel(new BorderLayout(10, 10));
        conteudo.setBackground(Cores.FUNDO);
        conteudo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        conteudo.add(parteSuperior, BorderLayout.NORTH);
        conteudo.add(painelFeedbacks, BorderLayout.CENTER);

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

        JLabel label = new JLabel(texto);
        label.setForeground(Cores.TEXTO_TITULO);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));
        painel.add(label, g);

        g.gridx = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;

        painel.add(campo, g);
    }

    // Getters

    public JComboBox<String> getArea() {
        return area;
    }

    public JComboBox<String> getFuncionario() {
        return funcionario;
    }

    public JButton getSelecionar() {
        return selecionar;
    }

    public JTextField getAutoavaliacao() {
        return autoavaliacao;
    }

    public JTextField getAvaliacaoGestor() {
        return avaliacaoGestor;
    }

    public JTextField getAvaliacaoPares() {
        return avaliacaoPares;
    }

    public JTable getTabelaFeedbacks() {
        return tabelaFeedbacks;
    }
}