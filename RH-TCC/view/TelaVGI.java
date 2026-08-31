package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaVGI extends JPanel {

	private static final long serialVersionUID = 1L;

	public TelaVGI() {
        setLayout(new BorderLayout(10, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(245, 246, 248));

        // Cabeçalho
        JLabel lblTitulo = new JLabel("MÓDULO I: VISÃO GERAL DE INDICADORES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30, 30, 30));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel Central para Cards e Tabela
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
        painelBanner.setBackground(Color.WHITE);
        painelBanner.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));
        painelBanner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

        JLabel lblBannerTitulo = new JLabel("Business Intelligence aplicado ao RH");
        lblBannerTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        
        JLabel lblBannerSub = new JLabel("Dados operacionais transformados em indicadores estratégicos.");
        lblBannerSub.setFont(new Font("Arial", Font.PLAIN, 11));
        lblBannerSub.setForeground(new Color(100, 100, 100));

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
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        tabela.setGridColor(new Color(230, 230, 230));
        tabela.setSelectionBackground(new Color(240, 244, 250));

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));
        scrollTabela.getViewport().setBackground(Color.WHITE);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        painelCentral.add(scrollTabela);

        add(painelCentral, BorderLayout.CENTER);
    }

    private JPanel criarCard(String titulo, String valor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 12, 12, 12)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTitulo.setForeground(new Color(80, 80, 80));

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 18));
        lblValor.setForeground(new Color(20, 20, 20));

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblValor);

        return card;
    }
}
