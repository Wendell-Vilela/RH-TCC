package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import view.Cores;

public class TelaVGI extends JPanel {

    private static final long serialVersionUID = 1L;

    public TelaVGI() {
        setLayout(new BorderLayout(10, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(Cores.FUNDO);

        // Cabeçalho
        JLabel lblTitulo = new JLabel("MÓDULO I: VISÃO GERAL DE INDICADORES");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setForeground(Cores.TEXTO_TITULO);
        add(lblTitulo, BorderLayout.NORTH);

        // Painel Central para Cards, Banner e Tabela
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setOpaque(false);

        // 1. Linha dos Cards (4 indicadores)
        JPanel painelCards = new JPanel(new GridLayout(1, 4, 15, 0));
        painelCards.setOpaque(false);
        painelCards.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        painelCards.add(criarCard("Turnover", "8,4%"));
        painelCards.add(criarCard("Absenteísmo", "3,1%"));
        painelCards.add(criarCard("Desempenho médio", "82%"));
        painelCards.add(criarCard("Custos RH", "R$ 248 mil"));

        painelCentral.add(painelCards);
        painelCentral.add(Box.createVerticalStrut(15));

        // 2. Banner Informativo (Business Intelligence)
        JPanel painelBanner = new JPanel(new BorderLayout(15, 0));
        painelBanner.setBackground(Cores.FUNDO);
        painelBanner.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));
        painelBanner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

        JLabel lblBannerTitulo = new JLabel("Business Intelligence aplicado ao RH");
        lblBannerTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblBannerTitulo.setForeground(Cores.TEXTO_TITULO);

        JLabel lblBannerSub = new JLabel("Dados operacionais transformados em indicadores estratégicos.");
        lblBannerSub.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblBannerSub.setForeground(Cores.TEXTO_TITULO);

        JPanel painelTextosBanner = new JPanel();
        painelTextosBanner.setLayout(new BoxLayout(painelTextosBanner, BoxLayout.Y_AXIS));
        painelTextosBanner.setOpaque(false);
        painelTextosBanner.add(lblBannerTitulo);
        painelTextosBanner.add(Box.createVerticalStrut(3));
        painelTextosBanner.add(lblBannerSub);

        painelBanner.add(painelTextosBanner, BorderLayout.CENTER);
        painelCentral.add(painelBanner);
        painelCentral.add(Box.createVerticalStrut(15));

        // 3. Tabela de Indicadores
        String[] colunas = {"Indicador", "Meta", "Atual", "Tendência"};
        Object[][] dados = {
            {"Turnover", "≤ 10%", "8,4%", "↓ Melhorando"},
            {"Absenteísmo", "≤ 3,5%", "3,1%", "↓ Melhorando"},
            {"Desempenho médio", "≥ 80%", "82%", "↑ Melhorando"},
            {"Custos RH", "≤ R$ 260 mil", "R$ 248 mil", "↓ Melhorando"}
        };

        JTable tabela = new JTable(dados, colunas);
        tabela.setRowHeight(30);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabela.setForeground(Cores.TEXTO_TITULO);
        tabela.setBackground(Cores.FUNDO);
        tabela.setGridColor(Cores.BORDA);
        tabela.setSelectionBackground(Cores.AZUL_MENU);
        tabela.setSelectionForeground(Cores.FUNDO);

        // Estilização do Cabeçalho da Tabela
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tabela.getTableHeader().setBackground(Cores.FUNDO);
        tabela.getTableHeader().setForeground(Cores.TEXTO_TITULO);
        
        // Centralização do texto nas células (opcional para melhor apresentação)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));
        scrollTabela.getViewport().setBackground(Cores.FUNDO);
        scrollTabela.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        painelCentral.add(scrollTabela);

        add(painelCentral, BorderLayout.CENTER);
    }

    private JPanel criarCard(String titulo, String valor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Cores.FUNDO);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            new EmptyBorder(12, 12, 12, 12)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblTitulo.setForeground(Cores.TEXTO_TITULO);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblValor.setForeground(Cores.AZUL_MENU);

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblValor);

        return card;
    }
}