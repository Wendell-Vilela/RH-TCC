package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaVGI extends JPanel {

    public TelaVGI() {
        setLayout(new BorderLayout(10, 15));
        
        setOpaque(false);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 204, 210), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitulo = new JLabel("VISÃO GERAL DE INDICADORES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30, 30, 30));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setOpaque(false);

        // 1. Linha dos Cards
        JPanel painelCards = new JPanel(new GridLayout(1, 4, 15, 0));
        painelCards.setOpaque(false);
        painelCards.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        painelCards.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelCards.add(criarCard("Turnover", "8,4%"));
        painelCards.add(criarCard("Absenteísmo", "3,1%"));
        painelCards.add(criarCard("Desempenho médio", "82%"));
        painelCards.add(criarCard("Custos RH", "R$ 248 mil"));

        painelCentral.add(painelCards);
        painelCentral.add(Box.createVerticalStrut(15));

        // 2. Banner Informativo
        JPanel painelBanner = new JPanel(new BorderLayout(15, 0));
        painelBanner.setOpaque(false);
        painelBanner.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 204, 210), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));
        painelBanner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
        painelBanner.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        // 3. Tabela de Indicadores (Não editável)
        String[] colunas = {"Indicador", "Meta", "Atual", "Tendência"};
        Object[][] dados = {
            {"Turnover", "≤ 10%", "8,4%", "Melhoria ↑"},
            {"Absenteísmo", "≤ 3,5%", "3,1%", "Regular →"},
            {"Desempenho médio", "≥ 80%", "82%", "Melhoria ↑"},
            {"Custos RH", "≤ R$ 260 mil", "R$ 248 mil", "Piora ↓"},
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };

        JTable tabela = new JTable(modeloTabela);
        tabela.setRowHeight(24);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        tabela.setFont(new Font("Arial", Font.PLAIN, 11));
        tabela.setGridColor(new Color(220, 220, 220));
        tabela.setSelectionBackground(new Color(220, 235, 252));
        tabela.setSelectionForeground(Color.BLACK);
        tabela.setOpaque(false);
        tabela.setBackground(new Color(0, 0, 0, 0));

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
        scrollTabela.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollTabela.getViewport().setOpaque(false);
        scrollTabela.setOpaque(false);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(200, 204, 210), 1));

        painelCentral.add(scrollTabela);

        add(painelCentral, BorderLayout.CENTER);
    }

    private JPanel criarCard(String titulo, String valor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 204, 210), 1, true),
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