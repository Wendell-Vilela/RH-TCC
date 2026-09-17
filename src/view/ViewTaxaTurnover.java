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

    public ViewTaxaTurnover() {
        setBackground(Cores.FUNDO);
        setLayout(new BorderLayout(20, 20));

        // Borda principal padronizada com título
        TitledBorder bordaPrincipal = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "ANÁLISE DA TAXA DE TURNOVER",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                Cores.TEXTO_TITULO
        );

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                bordaPrincipal
        ));

        // CENTRO - Gráficos, Indicadores e Tabela de Análise por Setor
        add(criarConteudoCentro(), BorderLayout.CENTER);
    }

    private JPanel criarConteudoCentro() {
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(Cores.FUNDO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // Linha 1: Cards Métricos Superiores
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.15;
        gbc.insets = new Insets(0, 0, 15, 0);
        centro.add(criarCardsIndicadores(), gbc);

        // Linha 2 - Esquerda: Gráfico de Pizza + Legenda
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.85;
        gbc.insets = new Insets(0, 0, 0, 10);
        centro.add(criarCardGrafico(), gbc);

        // Linha 2 - Direita: Tabela de Detalhamento por Setor
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(0, 10, 0, 0);
        centro.add(criarCardDetalhamentoSetores(), gbc);

        return centro;
    }

    private JPanel criarCardsIndicadores() {
        JPanel painelCards = new JPanel(new GridLayout(1, 4, 15, 0));
        painelCards.setBackground(Cores.FUNDO);

        painelCards.add(cardMetrica("Taxa Global Turnover", "4.0%", "Dentro da meta (< 5%)", Cores.VERDE));
        painelCards.add(cardMetrica("Saídas Voluntárias", "1.2%", "2 colaboradores este mês", Cores.LARANJA));
        painelCards.add(cardMetrica("Saídas Involuntárias", "2.8%", "4 colaboradores este mês", Cores.VERMELHO));
        painelCards.add(cardMetrica("Índice de Estabilidade", "96.0%", "142 mantidos no quadro",Cores.AZUL_MENU));

        return painelCards;
    }

    private JPanel cardMetrica(String titulo, String valor, String subtitulo, Color corDestaque) {
        JPanel card = new JPanel(new GridLayout(3, 1, 0, 2));
        card.setBackground(Cores.FUNDO);
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                titulo,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.PLAIN, 11),
                Cores.TEXTO_TITULO
        ));

        JLabel v = new JLabel(valor);
        v.setFont(new Font("SansSerif", Font.BOLD, 18));
        v.setForeground(Cores.TEXTO_TITULO);

        JLabel sub = new JLabel(subtitulo);
        sub.setFont(new Font("SansSerif", Font.BOLD, 11));
        sub.setForeground(corDestaque);

        card.add(v);
        card.add(sub);

        return card;
    }

    private JPanel criarCardGrafico() {
        JPanel card = new JPanel(new BorderLayout(15, 15));
        card.setBackground(Cores.FUNDO);
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "Distribuição Percentual de Rotatividade",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                Cores.TEXTO_TITULO
        ));

        // Painel central do gráfico e da legenda
        JPanel containerGrafico = new JPanel(new GridLayout(1, 2, 10, 0));
        containerGrafico.setBackground(Cores.FUNDO);

        containerGrafico.add(new GraficoPizza());
        containerGrafico.add(criarPainelLegenda());

        // Rodapé de Recomendação
        JLabel rodape = new JLabel("<html><span style='color:#F59E0B;'><b>Aviso:</b></span> Monitoramento ativo em Vendas devido ao pico de saídas voluntárias.</html>");
        rodape.setFont(new Font("SansSerif", Font.PLAIN, 11));
        rodape.setForeground(Cores.TEXTO_TITULO);

        card.add(containerGrafico, BorderLayout.CENTER);
        card.add(rodape, BorderLayout.SOUTH);

        return card;
    }

    private JPanel criarPainelLegenda() {
        JPanel legenda = new JPanel();
        legenda.setBackground(Cores.FUNDO);
        legenda.setLayout(new BoxLayout(legenda, BoxLayout.Y_AXIS));

        legenda.add(Box.createVerticalGlue());
        legenda.add(criarItemLegenda("■ Voluntário (1.2%)", Cores.LARANJA));
        legenda.add(Box.createVerticalStrut(15));
        legenda.add(criarItemLegenda("■ Involuntário (2.8%)", Cores.VERMELHO));
        legenda.add(Box.createVerticalStrut(15));
        legenda.add(criarItemLegenda("■ Estabilidade (96.0%)", Cores.VERDE));
        legenda.add(Box.createVerticalGlue());

        return legenda;
    }

    private JLabel criarItemLegenda(String texto, Color cor) {
        JLabel label = new JLabel(texto);
        label.setForeground(cor);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        return label;
    }

    private JPanel criarCardDetalhamentoSetores() {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Cores.FUNDO);
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "Turnover Detalhado por Departamento",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                Cores.TEXTO_TITULO
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

        // Estilização padronizada da tabela
        funcaoFacilitar.estilizarTabela(tabela);

        // Renderizador customizado mantido na coluna de resultado "Taxa Setor"
        tabela.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean isSel, boolean hasFocus, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, isSel, hasFocus, r, c);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                String valor = (String) v;

                if (valor != null && (valor.startsWith("8") || valor.startsWith("7"))) {
                    l.setForeground(Cores.VERMELHO);
                    l.setFont(new Font("SansSerif", Font.BOLD, 12));
                } else if ("0.0%".equals(valor)) {
                    l.setForeground(Cores.VERDE);
                    l.setFont(new Font("SansSerif", Font.PLAIN, 12));
                } else {
                    l.setForeground(Cores.TEXTO_TITULO);
                    l.setFont(new Font("SansSerif", Font.PLAIN, 12));
                }
                return l;
            }
        });

        JScrollPane sp = new JScrollPane(tabela);
        sp.setBackground(Cores.FUNDO);
        sp.getViewport().setBackground(Cores.FUNDO);
        sp.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        card.add(sp, BorderLayout.CENTER);

        return card;
    }

    // Gráfico de Pizza Dinâmico e Responsivo
    private class GraficoPizza extends JPanel {

        private static final long serialVersionUID = 1L;

        public GraficoPizza() {
            setBackground(Cores.FUNDO);
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

            // 1. Estabilidade (96.0% -> ~345.6 graus)
            g2d.setColor(Cores.VERDE);
            g2d.fillArc(x, y, tamanho, tamanho, 90, 346);

            // 2. Involuntário (2.8% -> ~10 graus)
            g2d.setColor(Cores.VERMELHO);
            g2d.fillArc(x, y, tamanho, tamanho, 76, 14);

            // 3. Voluntário (1.2% -> ~4.4 graus)
            g2d.setColor(Cores.LARANJA);
            g2d.fillArc(x, y, tamanho, tamanho, 72, 4);
        }
    }
}