package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class GestaoFerias extends JPanel {

    private static final long serialVersionUID = 1L;

    public GestaoFerias() {
        setBackground(Cores.FUNDO);
        setLayout(new BorderLayout(20, 20));

        // Borda padronizada com título
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "GESTÃO DE FÉRIAS",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                Cores.TEXTO_TITULO
        );

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                borda
        ));

        // =========================
        // TABELA DE DADOS
        // =========================
        String[] colunas = {
            "Departamento",
            "Até 12 meses",
            "De 12-18 meses",
            "Risco (+18 meses)"
        };

        Object[][] dados = {
            {"Operações", 240, 42, 12},
            {"Comercial", 110, 15, 3},
            {"Financeiro", 45, 2, 0},
            {"Tecnologia", 88, 8, 1}
        };

        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabela = new JTable(modelo);

        // Aplica o estilo centralizado via utilitário
        funcaoFacilitar.estilizarTabela(tabela);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Cores.FUNDO);
        scrollPane.getViewport().setBackground(Cores.FUNDO);
        scrollPane.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        JPanel conteudo = new JPanel(new BorderLayout());
        conteudo.setBackground(Cores.FUNDO);
        conteudo.add(scrollPane, BorderLayout.CENTER);

        add(conteudo, BorderLayout.CENTER);

        // =========================
        // RODAPÉ COM RESUMO INFORMATIVO
        // =========================
        JPanel rodape = new JPanel(new GridLayout(1, 2, 10, 0));
        rodape.setBackground(Cores.FUNDO);
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblRisco = new JLabel("Total em Risco Crítico (+18m): 16 colaboradores");
        lblRisco.setForeground(Cores.AZUL_MENU);
        lblRisco.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblRisco.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel lblMedia = new JLabel("Média da Empresa: 18 dias acumulados / func.");
        lblMedia.setForeground(Cores.TEXTO_TITULO);
        lblMedia.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblMedia.setHorizontalAlignment(SwingConstants.RIGHT);

        rodape.add(lblRisco);
        rodape.add(lblMedia);

        add(rodape, BorderLayout.SOUTH);
    }
}