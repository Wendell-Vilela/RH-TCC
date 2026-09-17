package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CustoMensal extends JPanel {

    private static final long serialVersionUID = 1L;

    // Formatador de Moeda (R$)
    private final NumberFormat fmtMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    // Labels de valores dinâmicos
    private JLabel investimentoTotaltxt;
    private JLabel salarioTotaltxt;
    private JLabel encargoTotaltxt;
    private JLabel beneficioTotaltxt;

    public CustoMensal() {
        setBackground(Cores.FUNDO);
        setLayout(new BorderLayout(20, 20));

        // Borda padronizada com título
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "CUSTO MENSAL DA FOLHA",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                Cores.TEXTO_TITULO
        );

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                borda
        ));

        // Área Central com Cards Métricos e Tabela
        add(criarConteudoCentro(), BorderLayout.CENTER);

        // Inicializa com valores de exemplo
        atualizarValores(185000.00, 68450.00, 32100.00);
    }

    private JPanel criarConteudoCentro() {
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(Cores.FUNDO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // Linha 0: Card do Investimento Total em Destaque
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.15;
        gbc.insets = new java.awt.Insets(0, 0, 15, 0);
        centro.add(criarCardInvestimentoTotal(), gbc);

        // Linha 1 - Esquerda: Detalhamento por Categoria
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.40;
        gbc.weighty = 0.85;
        gbc.insets = new java.awt.Insets(0, 0, 0, 10);
        centro.add(criarCardCategorias(), gbc);

        // Linha 1 - Direita: Tabela de Composição de Custos por Tipo
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.60;
        gbc.insets = new java.awt.Insets(0, 10, 0, 0);
        centro.add(criarCardTabelaComposicao(), gbc);

        return centro;
    }

    private JPanel criarCardInvestimentoTotal() {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Cores.FUNDO);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        JPanel textoPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        textoPanel.setBackground(Cores.FUNDO);

        JLabel lblInvestimento = new JLabel("INVESTIMENTO TOTAL DA FOLHA DE PAGAMENTO");
        lblInvestimento.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblInvestimento.setForeground(Cores.TEXTO_TITULO);

        investimentoTotaltxt = new JLabel("R$ 0,00");
        investimentoTotaltxt.setFont(new Font("SansSerif", Font.BOLD, 26));
        investimentoTotaltxt.setForeground(Cores.AZUL_MENU);

        textoPanel.add(lblInvestimento);
        textoPanel.add(investimentoTotaltxt);

        card.add(textoPanel, BorderLayout.CENTER);
        return card;
    }

    private JPanel criarCardCategorias() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Cores.FUNDO);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel titulo = new JLabel("Divisão do Custo Mensal");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 13));
        titulo.setForeground(Cores.TEXTO_TITULO);
        titulo.setAlignmentX(LEFT_ALIGNMENT);

        card.add(titulo);
        card.add(Box.createVerticalStrut(15));

        // Sub-cards das 3 categorias principais
        salarioTotaltxt = new JLabel("R$ 0,00");
        card.add(criarLinhaCategoria("Salários Base (Bruto)", salarioTotaltxt));
        card.add(Box.createVerticalStrut(10));

        encargoTotaltxt = new JLabel("R$ 0,00");
        card.add(criarLinhaCategoria("Encargos (INSS / FGTS / Provisões)", encargoTotaltxt));
        card.add(Box.createVerticalStrut(10));

        beneficioTotaltxt = new JLabel("R$ 0,00");
        card.add(criarLinhaCategoria("Benefícios Corporativos (VR/VT/Plano)", beneficioTotaltxt));

        return card;
    }

    private JPanel criarLinhaCategoria(String titulo, JLabel valorLabel) {
        JPanel item = new JPanel(new BorderLayout(10, 0));
        item.setBackground(Cores.FUNDO);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        item.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        JLabel lblNome = new JLabel(titulo);
        lblNome.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblNome.setForeground(Cores.TEXTO_TITULO);

        valorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        valorLabel.setForeground(Cores.AZUL_MENU);

        item.add(lblNome, BorderLayout.WEST);
        item.add(valorLabel, BorderLayout.EAST);

        return item;
    }

    private JPanel criarCardTabelaComposicao() {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Cores.FUNDO);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel tituloTabela = new JLabel("Detalhamento Analítico dos Custos");
        tituloTabela.setFont(new Font("SansSerif", Font.BOLD, 13));
        tituloTabela.setForeground(Cores.TEXTO_TITULO);

        String[] colunas = {"Item de Custo", "Grupo", "Valor Total", "% Representação"};
        Object[][] dados = {
            {"Salários Contratuais", "Salários", "R$ 185.000,00", "64,8%"},
            {"INSS Patronal (20%)", "Encargos", "R$ 37.000,00", "13,0%"},
            {"FGTS Mensal (8%)", "Encargos", "R$ 14.800,00", "5,2%"},
            {"Provisão de Férias/13º", "Encargos", "R$ 16.650,00", "5,8%"},
            {"Plano de Saúde / Odonto", "Benefícios", "R$ 18.500,00", "6,5%"},
            {"Vale Refeição / Alimentação", "Benefícios", "R$ 9.600,00", "3,4%"},
            {"Vale Transporte", "Benefícios", "R$ 4.000,00", "1,3%"}
        };

        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        JTable tabela = new JTable(model);

        // Aplica estilização padrão utilitária
        funcaoFacilitar.estilizarTabela(tabela);

        // Sobrescreve alinhamentos específicos da tabela de custos
        DefaultTableCellRenderer renderDireita = new DefaultTableCellRenderer();
        renderDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabela.getColumnModel().getColumn(2).setCellRenderer(renderDireita);

        DefaultTableCellRenderer renderCentro = new DefaultTableCellRenderer();
        renderCentro.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(3).setCellRenderer(renderCentro);

        JScrollPane sp = new JScrollPane(tabela);
        sp.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        sp.setBackground(Cores.FUNDO);
        sp.getViewport().setBackground(Cores.FUNDO);

        card.add(tituloTabela, BorderLayout.NORTH);
        card.add(sp, BorderLayout.CENTER);

        return card;
    }

    // Método público para atualizar os valores na tela dinamicamente
    public void atualizarValores(double salarios, double encargos, double beneficios) {
        double total = salarios + encargos + beneficios;

        salarioTotaltxt.setText(fmtMoeda.format(salarios));
        encargoTotaltxt.setText(fmtMoeda.format(encargos));
        beneficioTotaltxt.setText(fmtMoeda.format(beneficios));
        investimentoTotaltxt.setText(fmtMoeda.format(total));
    }
}