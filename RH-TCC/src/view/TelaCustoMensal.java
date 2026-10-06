package view;

import model.CustoMensal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class TelaCustoMensal extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel investimentoTotallbl;
    private JLabel investimentoTotaltxt;

    private JLabel salarioTotallbl;
    private JLabel salarioTotaltxt;

    private JLabel encargoTotallbl;
    private JLabel encargoTotaltxt;

    private JLabel beneficioTotallbl;
    private JLabel beneficioTotaltxt;

    private JTable tabela;
    private DefaultTableModel tableModel;

    public TelaCustoMensal() {

        setLayout(new BorderLayout());

        setBorder(
            BorderFactory.createEmptyBorder(
                15,
                15,
                15,
                15
            )
        );

        setBackground(Color.WHITE);

        montar();
    }

    public void AtualizarDados(CustoMensal custo) {

        if (custo == null) {
            return;
        }

        if (custo.getInvestimentoTotal() != null) {

            investimentoTotaltxt.setText(
                custo.getInvestimentoTotal().toString()
            );
        }

        if (custo.getSalarioTotal() != null) {

            salarioTotaltxt.setText(
                custo.getSalarioTotal().toString()
            );
        }

        if (custo.getEncargoTotal() != null) {

            encargoTotaltxt.setText(
                custo.getEncargoTotal().toString()
            );
        }

        if (custo.getBeneficioTotal() != null) {

            beneficioTotaltxt.setText(
                custo.getBeneficioTotal().toString()
            );
        }

        limparTabela();

        if (custo.getItens() != null) {

            for (CustoMensal.Item item : custo.getItens()) {

                Object[] linha = {

                    item.getId(),
                    item.getNome(),
                    item.getSalario(),
                    item.getEncargos(),
                    item.getBeneficios()
                };

                adicionarLinhaTabela(linha);
            }
        }

        revalidate();
        repaint();
    }

    public void adicionarLinhaTabela(Object[] dados) {

        tableModel.addRow(dados);
    }

    public void limparTabela() {

        tableModel.setRowCount(0);
    }

    private void montar() {

        /*
         * PAINEL PRINCIPAL
         *
         * Este painel possui a borda que envolve
         * os investimentos e a tabela.
         */

        JPanel formulario =
            new JPanel(
                new BorderLayout(
                    0,
                    15
                )
            );

        formulario.setBackground(
            Color.WHITE
        );

        Border borda =
            BorderFactory.createCompoundBorder(

                BorderFactory.createLineBorder(
                    Color.BLACK
                ),

                BorderFactory.createEmptyBorder(
                    20,
                    20,
                    20,
                    20
                )
            );

        formulario.setBorder(borda);

        /*
         * PAINEL DOS VALORES
         */

        JPanel valores =
            new JPanel(
                new GridBagLayout()
            );

        valores.setBackground(
            Color.WHITE
        );

        GridBagConstraints g =
            new GridBagConstraints();

        g.insets =
            new Insets(
                8,
                20,
                8,
                20
            );

        g.anchor =
            GridBagConstraints.WEST;

        /*
         * INVESTIMENTO TOTAL
         */

        investimentoTotallbl =
            new JLabel(
                "Investimento total:"
            );

        investimentoTotaltxt =
            new JLabel(
                "0.0"
            );

        investimentoTotallbl.setFont(
            investimentoTotallbl
                .getFont()
                .deriveFont(20f)
        );

        investimentoTotaltxt.setFont(
            investimentoTotaltxt
                .getFont()
                .deriveFont(20f)
        );

        investimentoTotallbl.setForeground(
            Color.BLACK
        );

        investimentoTotaltxt.setForeground(
            Color.BLACK
        );

        g.gridx = 0;
        g.gridy = 0;

        valores.add(
            investimentoTotallbl,
            g
        );

        g.gridx = 1;

        valores.add(
            investimentoTotaltxt,
            g
        );

        /*
         * SALÁRIOS
         */

        salarioTotallbl =
            new JLabel(
                "Salários:"
            );

        salarioTotaltxt =
            new JLabel(
                "0.0"
            );

        salarioTotallbl.setFont(
            salarioTotallbl
                .getFont()
                .deriveFont(12f)
        );

        salarioTotaltxt.setFont(
            salarioTotaltxt
                .getFont()
                .deriveFont(12f)
        );

        salarioTotallbl.setForeground(
            Color.BLACK
        );

        salarioTotaltxt.setForeground(
            Color.BLACK
        );

        g.gridx = 0;
        g.gridy = 1;

        valores.add(
            salarioTotallbl,
            g
        );

        g.gridx = 1;

        valores.add(
            salarioTotaltxt,
            g
        );

        /*
         * ENCARGOS
         */

        encargoTotallbl =
            new JLabel(
                "Encargos:"
            );

        encargoTotaltxt =
            new JLabel(
                "0.0"
            );

        encargoTotallbl.setFont(
            encargoTotallbl
                .getFont()
                .deriveFont(12f)
        );

        encargoTotaltxt.setFont(
            encargoTotaltxt
                .getFont()
                .deriveFont(12f)
        );

        encargoTotallbl.setForeground(
            Color.BLACK
        );

        encargoTotaltxt.setForeground(
            Color.BLACK
        );

        g.gridx = 0;
        g.gridy = 2;

        valores.add(
            encargoTotallbl,
            g
        );

        g.gridx = 1;

        valores.add(
            encargoTotaltxt,
            g
        );

        /*
         * BENEFÍCIOS
         */

        beneficioTotallbl =
            new JLabel(
                "Benefícios:"
            );

        beneficioTotaltxt =
            new JLabel(
                "0.0"
            );

        beneficioTotallbl.setFont(
            beneficioTotallbl
                .getFont()
                .deriveFont(12f)
        );

        beneficioTotaltxt.setFont(
            beneficioTotaltxt
                .getFont()
                .deriveFont(12f)
        );

        beneficioTotallbl.setForeground(
            Color.BLACK
        );

        beneficioTotaltxt.setForeground(
            Color.BLACK
        );

        g.gridx = 0;
        g.gridy = 3;

        valores.add(
            beneficioTotallbl,
            g
        );

        g.gridx = 1;

        valores.add(
            beneficioTotaltxt,
            g
        );

        /*
         * TABELA
         */

        String[] colunas = {

            "ID",
            "Nome",
            "Salário",
            "Encargos",
            "Benefícios"
        };

        tableModel =
            new DefaultTableModel(
                colunas,
                0
            );

        tabela =
            new JTable(
                tableModel
            );

        tabela.setRowHeight(
            30
        );

        tabela.getTableHeader()
              .setReorderingAllowed(
                  false
              );

        tabela.setFillsViewportHeight(
            true
        );

        /*
         * SCROLL DA TABELA
         */

        JScrollPane scrollPane =
            new JScrollPane(
                tabela
            );

        /*
         * COLOCA OS VALORES E A TABELA
         * DENTRO DA MESMA BORDA.
         */

        formulario.add(
            valores,
            BorderLayout.NORTH
        );

        formulario.add(
            scrollPane,
            BorderLayout.CENTER
        );

        /*
         * ADICIONA O PAINEL COMPLETO
         * À TELA DE CUSTO MENSAL.
         */

        add(
            formulario,
            BorderLayout.CENTER
        );
    }

    /*
     * TESTE DA TELA
     */

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> {

                JFrame frame =
                    new JFrame(
                        "Teste Tela Custo Mensal"
                    );

                frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
                );

                frame.setSize(
                    800,
                    600
                );

                frame.setLocationRelativeTo(
                    null
                );

                TelaCustoMensal tela =
                    new TelaCustoMensal();

                /*
                 * DADOS DE TESTE
                 */

                CustoMensal custoMock =
                    new CustoMensal();

                custoMock.setInvestimentoTotal(
                    new BigDecimal(
                        "15000.00"
                    )
                );

                custoMock.setSalarioTotal(
                    new BigDecimal(
                        "10000.00"
                    )
                );

                custoMock.setEncargoTotal(
                    new BigDecimal(
                        "3000.00"
                    )
                );

                custoMock.setBeneficioTotal(
                    new BigDecimal(
                        "2000.00"
                    )
                );

                /*
                 * FUNCIONÁRIOS DE TESTE
                 */

                List<CustoMensal.Item> itensMock =
                    new ArrayList<>();

                itensMock.add(
                    new CustoMensal.Item(

                        1L,

                        "Ana Silva",

                        new BigDecimal(
                            "5000.00"
                        ),

                        new BigDecimal(
                            "1500.00"
                        ),

                        new BigDecimal(
                            "1000.00"
                        )
                    )
                );

                itensMock.add(
                    new CustoMensal.Item(

                        2L,

                        "Carlos Souza",

                        new BigDecimal(
                            "5000.00"
                        ),

                        new BigDecimal(
                            "1500.00"
                        ),

                        new BigDecimal(
                            "1000.00"
                        )
                    )
                );

                custoMock.setItens(
                    itensMock
                );

                /*
                 * ATUALIZA A TELA
                 */

                tela.AtualizarDados(
                    custoMock
                );

                frame.add(
                    tela
                );

                frame.setVisible(
                    true
                );
            }
        );
    }
}
