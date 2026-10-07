package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ViewFuncionariosAtivos extends JPanel {

    private static final long serialVersionUID = 1L;

    // Componentes para Injeção Dinâmica de Dados pelo Controller
    private JLabel lblTotalAtivos;
    private JLabel lblCustoMensalTotal;
    private JLabel lblUltimaAtualizacao;
    private JLabel lblMetaGeral;

    private DefaultTableModel modelTabela;
    private JTable tabelaSetores;

    public ViewFuncionariosAtivos() {
        setLayout(new BorderLayout(15, 15));

        // Borda principal padronizada
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "QUADRO GERAL DE FUNCIONÁRIOS ATIVOS",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14)
        );

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                borda
        ));

        // CONTEÚDO PRINCIPAL
        add(criarConteudoCentro(), BorderLayout.CENTER);

        // RODAPÉ INFORMATIVO
        add(criarRodape(), BorderLayout.SOUTH);
    }

    private JPanel criarConteudoCentro() {
        JPanel centro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // 1. CARDS SUPERIORES DE DESTAQUE (Headcount Geral + Custo Mensal)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.20;
        gbc.insets = new Insets(5, 5, 10, 5);
        centro.add(criarPainelCardsSuperiores(), gbc);

        // 2. TABELA CENTRAL DE DEPARTAMENTOS (Detalhamento Operacional)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.80;
        gbc.insets = new Insets(0, 5, 5, 5);
        centro.add(criarPainelTabelaDepartamentos(), gbc);

        return centro;
    }

    private JPanel criarPainelCardsSuperiores() {
        JPanel painelCards = new JPanel(new GridLayout(1, 2, 15, 0));

        lblTotalAtivos = new JLabel("0", SwingConstants.CENTER);
        lblCustoMensalTotal = new JLabel("R$ 0,00", SwingConstants.CENTER);

        painelCards.add(criarCardMetrica("TOTAL DE FUNCIONÁRIOS ATIVOS", lblTotalAtivos, "Colaboradores com contrato ativo"));
        painelCards.add(criarCardMetrica("CUSTO OPERACIONAL MENSAL ESTIMADO", lblCustoMensalTotal, "Soma salarial da folha vigente"));

        return painelCards;
    }

    private JPanel criarCardMetrica(String titulo, JLabel valorLabel, String subtexto) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                titulo,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.PLAIN, 11)
        ));

        valorLabel.setFont(new Font("SansSerif", Font.BOLD, 26));

        JLabel sub = new JLabel(subtexto, SwingConstants.CENTER);
        sub.setFont(new Font("SansSerif", Font.ITALIC, 11));

        card.add(valorLabel, BorderLayout.CENTER);
        card.add(sub, BorderLayout.SOUTH);

        return card;
    }

    private JPanel criarPainelTabelaDepartamentos() {
        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Detalhamento por Departamento",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12)
        ));

        // Colunas exigidas pela documentação de integração
        String[] colunas = {
            "Departamento / Setor", 
            "Ativos no Setor", 
            "Representação (%)", 
            "Custo Mensal Somado"
        };

        modelTabela = new DefaultTableModel(colunas, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // Tabela somente leitura
            }
        };

        tabelaSetores = new JTable(modelTabela);
        tabelaSetores.setFillsViewportHeight(true);
        tabelaSetores.setRowHeight(24);

        // Centralização do texto nas colunas numéricas
        DefaultTableCellRenderer rendererCentralizado = new DefaultTableCellRenderer();
        rendererCentralizado.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 1; i < colunas.length; i++) {
            tabelaSetores.getColumnModel().getColumn(i).setCellRenderer(rendererCentralizado);
        }

        JScrollPane sp = new JScrollPane(tabelaSetores);
        sp.setBorder(BorderFactory.createEtchedBorder());

        painelTabela.add(sp, BorderLayout.CENTER);

        return painelTabela;
    }

    private JPanel criarRodape() {
        JPanel rodape = new JPanel(new GridLayout(1, 2, 10, 0));
        rodape.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        lblUltimaAtualizacao = new JLabel("Status: Aguardando carregamento...");
        lblUltimaAtualizacao.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblUltimaAtualizacao.setHorizontalAlignment(SwingConstants.LEFT);

        lblMetaGeral = new JLabel("Acompanhamento Operacional em Tempo Real");
        lblMetaGeral.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblMetaGeral.setHorizontalAlignment(SwingConstants.RIGHT);

        rodape.add(lblUltimaAtualizacao);
        rodape.add(lblMetaGeral);

        return rodape;
    }

    //Atualiza os cards superiores de totais.

    public void atualizarCards(String totalAtivos, String custoMensalFormatado) {
        lblTotalAtivos.setText(totalAtivos);
        lblCustoMensalTotal.setText(custoMensalFormatado);
    }

    //Limpa todas as linhas da tabela de departamentos.
    
    public void limparTabela() {
        modelTabela.setRowCount(0);
    }

 
    public void adicionarLinhaTabela(Object[] linha) {
        modelTabela.addRow(linha);
    }
    
    public void adicionarLinhaTabela(String nomeSetor, int qtdAtivos, String percentualRepresentacao, String custoSetorFormatado) {
        modelTabela.addRow(new Object[]{
            nomeSetor,
            qtdAtivos,
            percentualRepresentacao,
            custoSetorFormatado
        });
    }

    public void atualizarStatusRodape(String textoStatus) {
        lblUltimaAtualizacao.setText(textoStatus);
    }

    // Getters dos componentes
    public JLabel getLabelTotalAtivos() {
        return lblTotalAtivos;
    }

    public JLabel getLabelCustoTotalFolha() {
        return lblCustoMensalTotal;
    }

    public JLabel getLblCustoMensalTotal() {
        return lblCustoMensalTotal;
    }

    public JTable getTabelaSetores() {
        return tabelaSetores;
    }}
