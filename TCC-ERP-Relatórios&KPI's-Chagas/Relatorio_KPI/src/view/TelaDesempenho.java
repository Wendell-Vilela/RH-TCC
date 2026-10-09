package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaDesempenho extends JPanel {

    private static final Font FONT_REGULAR = new Font("Arial", Font.PLAIN, 10);
    private static final Font FONT_BOLD = new Font("Arial", Font.BOLD, 10);
    private static final Color COLOR_TEXTO = new Color(50, 50, 50);
    private static final Color COLOR_GRID = new Color(220, 220, 220);

    public TelaDesempenho() {
        // Layout principal que recebe o Scroll geral para evitar espremer o conteúdo
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de conteúdo interno com layout flexível
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setOpaque(false);
        painelConteudo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 204, 210), 1),
            new EmptyBorder(25, 25, 25, 25)
        ));

        JLabel lblTitulo = new JLabel("DESEMPENHO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(30, 30, 30));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelConteudo.add(lblTitulo);
        painelConteudo.add(Box.createVerticalStrut(20));

        // 1. Gráficos superiores (utilizando painel intermediário com tamanho mínimo garantido)
        JPanel painelGraficos = new JPanel(new GridLayout(1, 2, 20, 0));
        painelGraficos.setOpaque(false);
        painelGraficos.setPreferredSize(new Dimension(800, 240));
        painelGraficos.setMinimumSize(new Dimension(600, 200));
        painelGraficos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));
        painelGraficos.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelGraficos.add(criarCardBarrasHorizontais());
        painelGraficos.add(criarCardRadarCompetencias());

        painelConteudo.add(painelGraficos);
        painelConteudo.add(Box.createVerticalStrut(20));

        // 2. Tabela de Colaboradores
        String[] colunas = {"Colaborador", "Nota", "Status"};
        Object[][] dados = {
            {"Ana Carvalho", "4,5", "• Destaque"},
            {"João Pereira", "3,8", "• Acompanhar"},
            {"Marina Costa", "4,2", "• Estável"}
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabela = new JTable(modeloTabela);
        tabela.setRowHeight(35);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        tabela.setGridColor(COLOR_GRID);
        tabela.setSelectionBackground(new Color(220, 235, 252));
        tabela.setSelectionForeground(Color.BLACK);
        tabela.setOpaque(false);
        tabela.setBackground(new Color(0, 0, 0, 0));
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.getViewport().setOpaque(false);
        scrollTabela.setOpaque(false);
        scrollTabela.setPreferredSize(new Dimension(800, 160));
        scrollTabela.setMinimumSize(new Dimension(600, 120));
        scrollTabela.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(200, 204, 210), 1));

        painelConteudo.add(scrollTabela);

        // ScrollPane principal que engloba toda a tela (evita que o conteúdo se esprema e suma ao diminuir a janela)
        JScrollPane scrollPrincipal = new JScrollPane(painelConteudo);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.setOpaque(false);
        scrollPrincipal.getViewport().setOpaque(false);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16); // Rolagem mais suave

        add(scrollPrincipal, BorderLayout.CENTER);
    }

    private JPanel criarCardBarrasHorizontais() {
        JPanel card = new JPanel(new BorderLayout());
        card.setOpaque(false);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 204, 210), 1, true),
            new EmptyBorder(15, 18, 15, 18)
        ));

        JLabel lblTitulo = new JLabel("Desempenho por equipe");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        lblTitulo.setForeground(COLOR_TEXTO);
        card.add(lblTitulo, BorderLayout.NORTH);

        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                String[] equipes = {"RH", "Comercial", "Financeiro", "Operações"};
                int[] valoresNumericos = {88, 79, 84, 76};
                String[] valoresStr = {"88%", "79%", "84%", "76%"};
                
                int xInicial = 90;
                int larguraDisponivel = getWidth() - xInicial - 55;
                int larguraMaxima = Math.max(120, larguraDisponivel); // Proteção para largura mínima

                int yInicial = 25;
                int alturaBarra = 18;
                int espacamento = 32;
                int alturaGrade = getHeight() - 30;

                double[] percentuaisGrid = {0.0, 0.25, 0.50, 0.75, 1.0};
                String[] labelsGrid = {"0%", "25%", "50%", "75%", "100%"};

                for (int i = 0; i < percentuaisGrid.length; i++) {
                    int posX = xInicial + (int) (larguraMaxima * percentuaisGrid[i]);
                    
                    g2.setColor(COLOR_GRID);
                    g2.drawLine(posX, 10, posX, alturaGrade);

                    g2.setFont(FONT_REGULAR);
                    g2.setColor(new Color(120, 120, 120));
                    int larguraTexto = g2.getFontMetrics().stringWidth(labelsGrid[i]);
                    g2.drawString(labelsGrid[i], posX - (larguraTexto / 2), getHeight() - 8);
                }

                for (int i = 0; i < equipes.length; i++) {
                    g2.setFont(FONT_REGULAR);
                    g2.setColor(COLOR_TEXTO);
                    g2.drawString(equipes[i], 10, yInicial + (i * espacamento) + 13);

                    int larguraBarra = (int) (larguraMaxima * (valoresNumericos[i] / 100.0));

                    g2.setColor(Color.BLACK);
                    g2.fillRect(xInicial, yInicial + (i * espacamento), larguraBarra, alturaBarra);

                    if (i == 0) {
                        g2.setFont(FONT_BOLD);
                    } else {
                        g2.setFont(FONT_REGULAR);
                    }
                    g2.setColor(COLOR_TEXTO);
                    g2.drawString(valoresStr[i], xInicial + larguraBarra + 8, yInicial + (i * espacamento) + 14);
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
            new EmptyBorder(15, 18, 15, 18)
        ));

        JLabel lblTitulo = new JLabel("Competências (média geral)");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        lblTitulo.setForeground(COLOR_TEXTO);
        card.add(lblTitulo, BorderLayout.NORTH);

        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int cx = getWidth() / 2;
                int cy = getHeight() / 2;
                int r = Math.max(30, Math.min(cx, cy) - 40); // Proteção para raio mínimo

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

                int[] xDados = {cx, cx + (int)(r * pEntrega), cx, cx - (int)(r * pQualidade)};
                int[] yDados = {cy - (int)(r * pComms), cy, cy + (int)(r * pLideranca), cy};

                g2.setColor(new Color(80, 80, 80, 35));
                g2.fillPolygon(xDados, yDados, 4);

                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawPolygon(xDados, yDados, 4);
 
                for (int i = 0; i < 4; i++) {
                    g2.fillOval(xDados[i] - 3, yDados[i] - 3, 7, 7);
                }

                g2.setFont(FONT_REGULAR);
                g2.setColor(COLOR_TEXTO);
                g2.drawString("Comunicação", cx - 35, cy - r - 10);
                g2.setFont(FONT_BOLD);
                g2.setColor(Color.BLACK);
                g2.drawString("82%", cx + 38, cy - r - 10);

                g2.setFont(FONT_REGULAR);
                g2.setColor(COLOR_TEXTO);
                g2.drawString("Entrega", cx + r + 10, cy - 3);
                g2.setFont(FONT_BOLD);
                g2.setColor(Color.BLACK);
                g2.drawString("80%", cx + r + 10, cy + 11);

                g2.setFont(FONT_REGULAR);
                g2.setColor(COLOR_TEXTO);
                g2.drawString("Liderança", cx - 25, cy + r + 18);
                g2.setFont(FONT_BOLD);
                g2.setColor(Color.BLACK);
                g2.drawString("78%", cx + 32, cy + r + 18);

                g2.setFont(FONT_REGULAR);
                g2.setColor(COLOR_TEXTO);
                g2.drawString("Qualidade", cx - r - 65, cy - 3);
                g2.setFont(FONT_BOLD);
                g2.setColor(Color.BLACK);
                g2.drawString("85%", cx - r - 65, cy + 11);
            }
        };
        grafico.setOpaque(false);
        card.add(grafico, BorderLayout.CENTER);

        return card;
    }
}