package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaDesempenho extends JPanel {

    public TelaDesempenho() {
        setLayout(new BorderLayout(10, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(245, 246, 248));

        // Cabeçalho
        JLabel lblTitulo = new JLabel("MÓDULO III: DESEMPENHO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30, 30, 30));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel Central
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setOpaque(false);

        // 1. Linha superior: Gráfico de Barras Horizontais e Gráfico Radar (Competências)
        JPanel painelGraficos = new JPanel(new GridLayout(1, 2, 15, 0));
        painelGraficos.setOpaque(false);
        painelGraficos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));

        painelGraficos.add(criarCardBarrasHorizontais());
        painelGraficos.add(criarCardRadarCompetencias());

        painelCentral.add(painelGraficos);
        painelCentral.add(Box.createVerticalStrut(15));

        // 2. Tabela de Colaboradores
        String[] colunas = {"Colaborador", "Nota", "Status"};
        Object[][] dados = {
            {"Ana Carvalho", "4,5", "• Destaque"},
            {"João Pereira", "3,8", "• Acompanhar"},
            {"Marina Costa", "4,2", "• Estável"}
        };

        JTable tabela = new JTable(dados, colunas);
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        tabela.setGridColor(new Color(230, 230, 230));
        tabela.setSelectionBackground(new Color(240, 244, 250));

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        scrollTabela.getViewport().setBackground(Color.WHITE);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        painelCentral.add(scrollTabela);

        add(painelCentral, BorderLayout.CENTER);
    }

    private JPanel criarCardBarrasHorizontais() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));

        JLabel lblTitulo = new JLabel("Desempenho por equipe");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitulo.setForeground(new Color(50, 50, 50));
        card.add(lblTitulo, BorderLayout.NORTH);

        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                String[] equipes = {"RH", "Comercial", "Financeiro", "Operações"};
                int[] largurasBarras = {220, 195, 210, 185};
                String[] valores = {"88%", "79%", "84%", "76%"};
                int yInicial = 25;
                int alturaBarra = 18;
                int espacamento = 30;

                // Linhas de grade verticais e rótulos do eixo X
                g2.setColor(new Color(240, 240, 240));
                g2.drawLine(85, 15, 85, 150);
                g2.drawLine(160, 15, 160, 150);
                g2.drawLine(235, 15, 235, 150);
                g2.drawLine(310, 15, 310, 150);

                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(120, 120, 120));
                g2.drawString("0%", 80, 162);
                g2.drawString("25%", 150, 162);
                g2.drawString("50%", 225, 162);
                g2.drawString("75%", 300, 162);
                g2.drawString("100%", 365, 162);

                for (int i = 0; i < equipes.length; i++) {
                    // Nome da equipe
                    g2.setFont(new Font("Arial", Font.PLAIN, 11));
                    g2.setColor(new Color(50, 50, 50));
                    g2.drawString(equipes[i], 10, yInicial + (i * espacamento) + 13);

                    // Barra preta
                    g2.setColor(Color.BLACK);
                    g2.fillRect(85, yInicial + (i * espacamento), largurasBarras[i], alturaBarra);

                    // Valor percentual (RH em destaque roxo)
                    if (i == 0) {
                        g2.setFont(new Font("Arial", Font.BOLD, 11));
                        g2.setColor(new Color(90, 40, 150));
                    } else {
                        g2.setFont(new Font("Arial", Font.PLAIN, 11));
                        g2.setColor(new Color(50, 50, 50));
                    }
                    g2.drawString(valores[i], 85 + largurasBarras[i] + 8, yInicial + (i * espacamento) + 14);
                }
            }
        };
        grafico.setOpaque(false);
        card.add(grafico, BorderLayout.CENTER);

        return card;
    }

    private JPanel criarCardRadarCompetencias() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));

        JLabel lblTitulo = new JLabel("Competências (média geral)");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitulo.setForeground(new Color(50, 50, 50));
        card.add(lblTitulo, BorderLayout.NORTH);

        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int cx = 185;
                int cy = 85;
                int r = 45;

                // Desenho da teia do gráfico radar (losango)
                g2.setColor(new Color(230, 230, 230));
                g2.drawPolygon(new int[]{cx, cx + r, cx, cx - r}, new int[]{cy - r, cy, cy + r, cy}, 4);
                g2.drawPolygon(new int[]{cx, cx + (r/2), cx, cx - (r/2)}, new int[]{cy - (r/2), cy, cy + (r/2), cy}, 4);

                // Linhas cruzadas internas
                g2.drawLine(cx, cy - r, cx, cy + r);
                g2.drawLine(cx - r, cy, cx + r, cy);

                // Polígono de dados preenchido (roxo claro translúcido)
                int[] xDados = {cx, cx + 38, cx, cx - 40};
                int[] yDados = {cy - 40, cy, cy + 35, cy};
                g2.setColor(new Color(110, 60, 180, 40));
                g2.fillPolygon(xDados, yDados, 4);

                // Borda do polígono de dados e pontos
                g2.setColor(new Color(90, 40, 150));
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawPolygon(xDados, yDados, 4);

                for (int i = 0; i < 4; i++) {
                    g2.fillOval(xDados[i] - 3, yDados[i] - 3, 7, 7);
                }

                // Rótulos externos das competências
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(50, 50, 50));
                
                // Topo
                g2.drawString("Comunicação", cx - 30, cy - 52);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.setColor(new Color(90, 40, 150));
                g2.drawString("82%", cx + 12, cy - 52);

                // Direita
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(50, 50, 50));
                g2.drawString("Entrega", cx + r + 12, cy + 3);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.setColor(new Color(90, 40, 150));
                g2.drawString("80%", cx + r + 12, cy + 15);

                // Baixo
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(50, 50, 50));
                g2.drawString("Liderança", cx - 22, cy + r + 18);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.setColor(new Color(90, 40, 150));
                g2.drawString("78%", cx + 24, cy + r + 18);

                // Esquerda
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(50, 50, 50));
                g2.drawString("Qualidade", cx - r - 45, cy + 3);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.setColor(new Color(90, 40, 150));
                g2.drawString("85%", cx - r - 45, cy + 15);
            }
        };
        grafico.setOpaque(false);
        card.add(grafico, BorderLayout.CENTER);

        return card;
    }
}
