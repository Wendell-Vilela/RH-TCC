package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
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

public class ViewTaxaTurnover extends JPanel {

    private static final long serialVersionUID = 1L;

    // Cores Monocromáticas
    private static final Color COR_FUNDO = Color.WHITE;
    private static final Color COR_TEXTO = Color.BLACK;
    private static final Color COR_SUBTEXTO = Color.DARK_GRAY;
    private static final Color COR_BORDA = Color.GRAY;

    public ViewTaxaTurnover() {
        setBackground(COR_FUNDO);
        setLayout(new BorderLayout(15, 15));

        TitledBorder bordaPrincipal = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COR_BORDA),
                "ANÁLISE DA TAXA DE TURNOVER",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                COR_TEXTO
        );

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                bordaPrincipal
        ));

        add(criarConteudoCentro(), BorderLayout.CENTER);
    }

    private JPanel criarConteudoCentro() {
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(COR_FUNDO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // Cards Superiores
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.15;
        gbc.insets = new Insets(0, 0, 15, 0);
        centro.add(criarCardsIndicadores(), gbc);

        // Gráfico de Pizza (Esquerda)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.85;
        gbc.insets = new Insets(0, 0, 0, 10);
        centro.add(criarCardGrafico(), gbc);

        // Tabela por Setor (Direita)
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(0, 10, 0, 0);
        centro.add(criarCardDetalhamentoSetores(), gbc);

        return centro;
    }

    private JPanel criarCardsIndicadores() {
        JPanel painelCards = new JPanel(new GridLayout(1, 4, 15, 0));
        painelCards.setBackground(COR_FUNDO);

        painelCards.add(cardMetrica("Taxa Global Turnover", "4.0%", "Dentro da meta (< 5%)"));
        painelCards.add(cardMetrica("Saídas Voluntárias", "1.2%", "2 colaboradores este mês"));
        painelCards.add(cardMetrica("Saídas Involuntárias", "2.8%", "4 colaboradores este mês"));
        painelCards.add(cardMetrica("Índice de Estabilidade", "96.0%", "142 mantidos no quadro"));

        return painelCards;
    }

    private JPanel cardMetrica(String titulo, String valor, String subtitulo) {
        JPanel card = new JPanel(new GridLayout(2, 1, 0, 2));
        card.setBackground(COR_FUNDO);
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COR_BORDA),
                titulo,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.PLAIN, 11),
                COR_SUBTEXTO
        ));

        JLabel v = new JLabel(valor);
        v.setFont(new Font("SansSerif", Font.BOLD, 18));
        v.setForeground(COR_TEXTO);

        JLabel sub = new JLabel(subtitulo);
        sub.setFont(new Font("SansSerif", Font.PLAIN, 11));
        sub.setForeground(COR_SUBTEXTO);

        card.add(v);
        card.add(sub);

        return card;
    }

    private JPanel criarCardGrafico() {
        JPanel card = new JPanel(new BorderLayout(15, 15));
        card.setBackground(COR_FUNDO);
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COR_BORDA),
                "Distribuição Percentual de Rotatividade",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                COR_TEXTO
        ));

        JPanel containerGrafico = new JPanel(new GridLayout(1, 2, 10, 0));
        containerGrafico.setBackground(COR_FUNDO);

        containerGrafico.add(new GraficoPizza());
        containerGrafico.add(criarPainelLegenda());

        JLabel rodape = new JLabel("Aviso: Monitoramento ativo em Vendas devido ao pico de saídas voluntárias.");
        rodape.setFont(new Font("SansSerif", Font.ITALIC, 11));
        rodape.setForeground(COR_SUBTEXTO);

        card.add(containerGrafico, BorderLayout.CENTER);
        card.add(rodape, BorderLayout.SOUTH);

        return card;
    }

    private JPanel criarPainelLegenda() {
        JPanel legenda = new JPanel();
        legenda.setBackground(COR_FUNDO);
        legenda.setLayout(new BoxLayout(legenda, BoxLayout.Y_AXIS));

        legenda.add(Box.createVerticalGlue());
        legenda.add(criarItemLegenda("■ Voluntário (1.2%)", Color.GRAY));
        legenda.add(Box.createVerticalStrut(15));
        legenda.add(criarItemLegenda("■ Involuntário (2.8%)", Color.DARK_GRAY));
        legenda.add(Box.createVerticalStrut(15));
        legenda.add(criarItemLegenda("■ Estabilidade (96.0%)", Color.LIGHT_GRAY));
        legenda.add(Box.createVerticalGlue());

        return legenda;
    }

    private JLabel criarItemLegenda(String texto, Color corTom) {
        JLabel label = new JLabel(texto);
        label.setForeground(corTom);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        return label;
    }

    private JPanel criarCardDetalhamentoSetores() {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(COR_FUNDO);
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COR_BORDA),
                "Turnover Detalhado por Departamento",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                COR_TEXTO
        ));

        String[] colunas = {"Departamento", "Colaboradores", "Desligamentos", "Taxa Setor"};
        Object[][] dados = {
            {"Comercial / Vendas", "35", "3", "8.5%"},
            {"Tecnologia (TI)", "28", "2", "7.1%"},
            {"Operações / Logística", "50", "1", "2.0%"},
            {"Recursos Humanos", "15", "0", "0.0%"},
            {"Financeiro", "20", "0", "0.0%"}
        };

        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int col) { 
                return false; 
            }
        };

        JTable tabela = new JTable(model);
        funcaoFacilitar.estilizarTabela(tabela);

        // Renderizador centralizado e estilizado em preto e branco
        tabela.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean isSel, boolean hasFocus, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, isSel, hasFocus, r, c);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                l.setForeground(COR_TEXTO);

                String valor = (String) v;
                if (valor != null && (valor.startsWith("8") || valor.startsWith("7"))) {
                    l.setFont(new Font("SansSerif", Font.BOLD, 12));
                } else {
                    l.setFont(new Font("SansSerif", Font.PLAIN, 12));
                }
                return l;
            }
        });

        JScrollPane sp = new JScrollPane(tabela);
        sp.setBackground(COR_FUNDO);
        sp.getViewport().setBackground(COR_FUNDO);
        sp.setBorder(BorderFactory.createLineBorder(COR_BORDA, 1));

        card.add(sp, BorderLayout.CENTER);

        return card;
    }

    // Gráfico de Pizza Monocromático
    private class GraficoPizza extends JPanel {

        private static final long serialVersionUID = 1L;

        public GraficoPizza() {
            setBackground(COR_FUNDO);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int largura = getWidth();
            int altura = getHeight();
            int tamanho = Math.min(largura, altura) - 20;

            int x = (largura - tamanho) / 2;
            int y = (altura - tamanho) / 2;

            if (tamanho <= 0) return;

            // 1. Estabilidade (96.0% -> Tons Claros)
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillArc(x, y, tamanho, tamanho, 90, 346);

            // 2. Involuntário (2.8% -> Tons Escuros)
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillArc(x, y, tamanho, tamanho, 76, 14);

            // 3. Voluntário (1.2% -> Cinza Médio)
            g2d.setColor(Color.GRAY);
            g2d.fillArc(x, y, tamanho, tamanho, 72, 4);

            // Contorno do gráfico para melhor separação das seções
            g2d.setColor(Color.BLACK);
            g2d.drawOval(x, y, tamanho, tamanho);
        }
    }
}