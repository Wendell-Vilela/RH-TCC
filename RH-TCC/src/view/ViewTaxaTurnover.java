package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
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

    // Componentes para Injeção Dinâmica de Dados pelo Controller
    private JLabel lblTurnoverGlobal;
    private JLabel lblSaidasVoluntarias;
    private JLabel lblSaidasInvoluntarias;
    private JLabel lblEstabilidade;
    private JLabel lblAvisoRodape;

    private DefaultTableModel modelTabela;
    private JTable tabelaSetores;
    private GraficoPizza graficoPizza;

    public ViewTaxaTurnover() {
        setLayout(new BorderLayout(15, 15));

        // Borda principal padronizada com Look & Feel do sistema
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "ANÁLISE DA TAXA DE TURNOVER",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14)
        );

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                borda
        ));

        add(criarConteudoCentro(), BorderLayout.CENTER);
    }

    private JPanel criarConteudoCentro() {
        JPanel centro = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // 1. CARDS SUPERIORES (Métricas Globais)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2; gbc.weighty = 0.15;
        gbc.insets = new Insets(5, 5, 10, 5);
        centro.add(criarCardsIndicadores(), gbc);

        // 2. GRÁFICO DE ROSCA / PIZZA (Lado Esquerdo)
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1; gbc.weightx = 0.4;
        gbc.weighty = 0.85;
        gbc.insets = new Insets(0, 5, 5, 5);
        centro.add(criarCardGrafico(), gbc);

        // 3. TABELA DETALHADA POR SETOR (Lado Direito)
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 0.6;
        gbc.insets = new Insets(0, 5, 5, 5);
        centro.add(criarCardDetalhamentoSetores(), gbc);

        return centro;
    }

    private JPanel criarCardsIndicadores() {
        JPanel painelCards = new JPanel(new GridLayout(1, 4, 10, 0));

        lblTurnoverGlobal = new JLabel("0.0%", SwingConstants.LEFT);
        lblSaidasVoluntarias = new JLabel("0", SwingConstants.LEFT);
        lblSaidasInvoluntarias = new JLabel("0", SwingConstants.LEFT);
        lblEstabilidade = new JLabel("0.0%", SwingConstants.LEFT);

        painelCards.add(cardMetrica("Taxa Global Turnover", lblTurnoverGlobal, "No período selecionado"));
        painelCards.add(cardMetrica("Saídas Voluntárias", lblSaidasVoluntarias, "Iniciativa do colaborador"));
        painelCards.add(cardMetrica("Saídas Involuntárias", lblSaidasInvoluntarias, "Iniciativa da empresa"));
        painelCards.add(cardMetrica("Índice de Estabilidade", lblEstabilidade, "Retenção no período"));

        return painelCards;
    }

    private JPanel cardMetrica(String titulo, JLabel valorLabel, String subtitulo) {
        JPanel card = new JPanel(new GridLayout(2, 1, 0, 2));
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                titulo,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.PLAIN, 11)
        ));

        valorLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel sub = new JLabel(subtitulo);
        sub.setFont(new Font("SansSerif", Font.ITALIC, 10));

        card.add(valorLabel);
        card.add(sub);
        return card;
    }

    private JPanel criarCardGrafico() {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Distribuição de Saídas e Retenção",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12)
        ));

        graficoPizza = new GraficoPizza();
        lblAvisoRodape = new JLabel("Aguardando carregamento de dados...", SwingConstants.CENTER);
        lblAvisoRodape.setFont(new Font("SansSerif", Font.ITALIC, 11));

        card.add(graficoPizza, BorderLayout.CENTER);
        card.add(lblAvisoRodape, BorderLayout.SOUTH);

        return card;
    }

    private JPanel criarCardDetalhamentoSetores() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Turnover Detalhado por Departamento",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12)
        ));

        // Cobertura completa de colunas para auditoria do setor
        String[] colunas = {"Departamento", "Colaboradores", "Voluntárias", "Involuntárias", "Taxa Setor (%)"};
        
        modelTabela = new DefaultTableModel(colunas, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // Somente leitura
            }
        };

        tabelaSetores = new JTable(modelTabela);
        tabelaSetores.setFillsViewportHeight(true);
        tabelaSetores.setRowHeight(24);
        tabelaSetores.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 11));
        
        // Renderizador centralizado para valores numéricos
        DefaultTableCellRenderer rendererCentralizado = new DefaultTableCellRenderer();
        rendererCentralizado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < colunas.length; i++) {
            tabelaSetores.getColumnModel().getColumn(i).setCellRenderer(rendererCentralizado);
        }

        JScrollPane sp = new JScrollPane(tabelaSetores);
        sp.setBorder(BorderFactory.createEtchedBorder());

        card.add(sp, BorderLayout.CENTER);
        return card;
    }

    // --- MÉTODOS PÚBLICOS PARA O CONTROLLER INJETAR DADOS REAIS ---

    public void atualizarMetrics(String turnoverGlobal, String voluntarias, String involuntarias, String estabilidade) {
        lblTurnoverGlobal.setText(turnoverGlobal);
        lblSaidasVoluntarias.setText(voluntarias);
        lblSaidasInvoluntarias.setText(involuntarias);
        lblEstabilidade.setText(estabilidade);
    }

    public void atualizarAvisoRodape(String texto) {
        lblAvisoRodape.setText(texto);
    }

    public void limparTabela() {
        modelTabela.setRowCount(0);
    }

    public void adicionarLinhaTabela(Object[] linha) {
        modelTabela.addRow(linha);
    }

    public void atualizarGrafico(double pctVoluntarias, double pctInvoluntarias, double pctEstabilidade) {
        graficoPizza.setDados(pctVoluntarias, pctInvoluntarias, pctEstabilidade);
    }

    // --- GETTERS MANTIDOS PARA O CONTROLLER ---

    public JLabel getLabelTurnoverGlobal() {
        return lblTurnoverGlobal;
    }

    public JLabel getLabelSaidasVoluntarias() {
        return lblSaidasVoluntarias;
    }

    public JLabel getLabelSaidasInvoluntarias() {
        return lblSaidasInvoluntarias;
    }

    public JLabel getLabelEstabilidade() {
        return lblEstabilidade;
    }

    public GraficoPizza getGraficoRosca() {
        return graficoPizza;
    }

    public JTable getTabelaSetores() {
        return tabelaSetores;
    }

    public DefaultTableModel getModelTabela() {
        return modelTabela;
    }

    public void exibirMensagemErro(String mensagem) {
        lblAvisoRodape.setText(mensagem);
    }

    // --- COMPONENTE DO GRÁFICO DINÂMICO ---

    public class GraficoPizza extends JPanel {
        private static final long serialVersionUID = 1L;

        private double pctVoluntarias = 0.0;
        private double pctInvoluntarias = 0.0;
        private double pctEstabilidade = 100.0;

        public GraficoPizza() {
            setOpaque(false);
        }

        public void setPercentualTurnover(double percentual) {
            this.pctVoluntarias = percentual;
            this.pctEstabilidade = 100.0 - percentual;
            repaint();
        }

        public void setDados(double pctVol, double pctInvol, double pctEst) {
            this.pctVoluntarias = pctVol;
            this.pctInvoluntarias = pctInvol;
            this.pctEstabilidade = pctEst;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int tamanho = Math.min(getWidth(), getHeight()) - 20;
            if (tamanho <= 0) return;

            int x = (getWidth() - tamanho) / 2;
            int y = (getHeight() - tamanho) / 2;

            int anguloVol = (int) Math.round((pctVoluntarias / 100.0) * 360);
            int anguloInvol = (int) Math.round((pctInvoluntarias / 100.0) * 360);
            int anguloEst = 360 - (anguloVol + anguloInvol);

            int anguloAtual = 90;

            // Usa cores adaptáveis do componente/plataforma para renderizar os arcos
            g2d.setColor(getForeground().brighter());
            g2d.fillArc(x, y, tamanho, tamanho, anguloAtual, anguloEst);
            anguloAtual += anguloEst;

            g2d.setColor(getForeground().darker());
            g2d.fillArc(x, y, tamanho, tamanho, anguloAtual, anguloInvol);
            anguloAtual += anguloInvol;

            g2d.setColor(getForeground());
            g2d.fillArc(x, y, tamanho, tamanho, anguloAtual, anguloVol);

            // Borda suave do gráfico
            g2d.drawOval(x, y, tamanho, tamanho);
        }
    }
}
