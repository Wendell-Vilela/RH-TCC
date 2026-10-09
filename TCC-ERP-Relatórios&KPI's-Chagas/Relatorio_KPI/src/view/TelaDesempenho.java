package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaDesempenho extends JPanel {

    public TelaDesempenho() {
        setLayout(new BorderLayout(10, 15));
        
        setOpaque(false);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 204, 210), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitulo = new JLabel("DESEMPENHO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30, 30, 30));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setOpaque(false);

        // 1. Gráficos superiores
        JPanel painelGraficos = new JPanel(new GridLayout(1, 2, 15, 0));
        painelGraficos.setOpaque(false);
        painelGraficos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));
        painelGraficos.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelGraficos.add(criarCardBarrasHorizontais());
        painelGraficos.add(criarCardRadarCompetencias());

        painelCentral.add(painelGraficos);
        painelCentral.add(Box.createVerticalStrut(15));

        // 2. Tabela de Colaboradores (Não editável)
        String[] colunas = {"Colaborador", "Nota", "Status"};
        Object[][] dados = {
            {"Ana Carvalho", "4,5", "• Destaque"},
            {"João Pereira", "3,8", "• Acompanhar"},
            {"Marina Costa", "4,2", "• Estável"}
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a edição
            }
        };

        JTable tabela = new JTable(modeloTabela);
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        tabela.setGridColor(new Color(220, 220, 220));
        tabela.setSelectionBackground(new Color(220, 235, 252));
        tabela.setSelectionForeground(Color.BLACK);
        tabela.setOpaque(false);
        tabela.setBackground(new Color(0, 0, 0, 0));

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        scrollTabela.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollTabela.getViewport().setOpaque(false);
        scrollTabela.setOpaque(false);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(200, 204, 210), 1));

        painelCentral.add(scrollTabela);

        add(painelCentral, BorderLayout.CENTER);
    }

    private JPanel criarCardBarrasHorizontais() {
        JPanel card = new JPanel(new BorderLayout());
        card.setOpaque(false);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 204, 210), 1, true),
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

                g2.setColor(new Color(220, 220, 220));
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
                    g2.setFont(new Font("Arial", Font.PLAIN, 11));
                    g2.setColor(new Color(50, 50, 50));
                    g2.drawString(equipes[i], 10, yInicial + (i * espacamento) + 13);

                    g2.setColor(Color.BLACK);
                    g2.fillRect(85, yInicial + (i * espacamento), largurasBarras[i], alturaBarra);

                    if (i == 0) {
                        g2.setFont(new Font("Arial", Font.BOLD, 11));
                        g2.setColor(Color.BLACK);
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
        card.setOpaque(false);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 204, 210), 1, true),
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

                int cx = 205;
                int cy = 95;
                int r = 65;

                for (double scale : new double[]{0.25, 0.50, 0.75, 1.0}) {
                    int sr = (int) (r * scale);
                    g2.setColor(scale == 1.0 ? new Color(180, 180, 180) : new Color(225, 225, 225));
                    g2.drawPolygon(
                        new int[]{cx, cx + sr, cx, cx - sr},
                        new int[]{cy - sr, cy, cy + sr, cy},
                        4
                    );
                }

                g2.setColor(new Color(210, 210, 210));
                g2.drawLine(cx, cy - r, cx, cy + r);
                g2.drawLine(cx - r, cy, cx + r, cy);

                double pComms = 0.82;
                double pEntrega = 0.80;
                double pLideranca = 0.78;
                double pQualidade = 0.85;

                int[] xDados = {
                    cx,
                    cx + (int)(r * pEntrega),
                    cx,
                    cx - (int)(r * pQualidade)
                };
                int[] yDados = {
                    cy - (int)(r * pComms),
                    cy,
                    cy + (int)(r * pLideranca),
                    cy
                };

                g2.setColor(new Color(80, 80, 80, 35));
                g2.fillPolygon(xDados, yDados, 4);

                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawPolygon(xDados, yDados, 4);
 
                for (int i = 0; i < 4; i++) {
                    g2.fillOval(xDados[i] - 3, yDados[i] - 3, 7, 7);
                }

                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(50, 50, 50));
                
                g2.drawString("Comunicação", cx - 35, cy - r - 12);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.setColor(Color.BLACK);
                g2.drawString("82%", cx + 38, cy - r - 12);

                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(50, 50, 50));
                g2.drawString("Entrega", cx + r + 12, cy - 3);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.setColor(Color.BLACK);
                g2.drawString("80%", cx + r + 12, cy + 11);

                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(50, 50, 50));
                g2.drawString("Liderança", cx - 25, cy + r + 16);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.setColor(Color.BLACK);
                g2.drawString("78%", cx + 32, cy + r + 16);

                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(50, 50, 50));
                g2.drawString("Qualidade", cx - r - 65, cy - 3);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.setColor(Color.BLACK);
                g2.drawString("85%", cx - r - 65, cy + 11);
            }
        };
        grafico.setOpaque(false);
        card.add(grafico, BorderLayout.CENTER);

        return card;
    }
}