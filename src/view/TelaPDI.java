package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import view.Cores;
import view.funcaoFacilitar;

public class TelaPDI extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JComboBox<String> area = new JComboBox<>(
        new String[]{
            "Selecione a área",
            "Tecnologia",
            "Recursos Humanos",
            "Financeiro",
            "Marketing"
        }
    );

    private final JComboBox<String> funcionario = new JComboBox<>(
        new String[]{
            "Selecione o funcionário",
            "João da Silva",
            "Maria Santos",
            "Pedro Oliveira"
        }
    );

    private final JButton selecionar = funcaoFacilitar.criarBotao("Selecionar", Cores.BOTAO_SALVAR);

    private final JProgressBar progresso = new JProgressBar(0, 100);

    private final JTable tabela = new JTable();

    public TelaPDI() {
        setLayout(new BorderLayout());
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        montar();
    }

    private void montar() {
        JPanel selecao = new JPanel(new GridBagLayout());
        selecao.setBackground(Cores.FUNDO);

        TitledBorder bordaSelecao = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Selecionar Funcionário "
        );
        bordaSelecao.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaSelecao.setTitleColor(Cores.TEXTO_TITULO);
        selecao.setBorder(BorderFactory.createCompoundBorder(
            bordaSelecao,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);

        area.setFont(new Font("SansSerif", Font.PLAIN, 12));
        area.setBackground(Cores.FUNDO);

        funcionario.setFont(new Font("SansSerif", Font.PLAIN, 12));
        funcionario.setBackground(Cores.FUNDO);

        componente(selecao, g, 0, "Área:", area);
        componente(selecao, g, 1, "Funcionário:", funcionario);

        g.gridx = 2;
        g.gridy = 1;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.LINE_START;

        selecao.add(selecionar, g);

        JPanel painelProgresso = new JPanel(new BorderLayout(10, 10));
        painelProgresso.setBackground(Cores.FUNDO);

        TitledBorder bordaProgresso = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Progresso do Plano "
        );
        bordaProgresso.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaProgresso.setTitleColor(Cores.TEXTO_TITULO);
        painelProgresso.setBorder(BorderFactory.createCompoundBorder(
            bordaProgresso,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        progresso.setValue(65);
        progresso.setStringPainted(true);
        progresso.setFont(new Font("SansSerif", Font.BOLD, 12));
        progresso.setForeground(Cores.TEXTO_TITULO);
        progresso.setBackground(Cores.FUNDO);
        progresso.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        painelProgresso.add(progresso, BorderLayout.CENTER);

        String[] colunas = {
            "Próxima Ação",
            "Descrição",
            "Prazo",
            "Status"
        };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        modelo.addRow(new Object[]{
            "Curso de Java",
            "Realizar curso de aperfeiçoamento em Java",
            "15/09/2026",
            "Em andamento"
        });

        modelo.addRow(new Object[]{
            "Treinamento de Comunicação",
            "Participar de treinamento para melhorar a comunicação",
            "30/09/2026",
            "Pendente"
        });

        modelo.addRow(new Object[]{
            "Projeto em equipe",
            "Participar de um projeto para desenvolver trabalho em equipe",
            "15/10/2026",
            "Pendente"
        });

        modelo.addRow(new Object[]{
            "Avaliação de desempenho",
            "Realizar nova avaliação com o gestor",
            "30/10/2026",
            "Finalizada"
        });

        tabela.setModel(modelo);
        funcaoFacilitar.estilizarTabela(tabela);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(400);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(120);

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.getViewport().setBackground(Cores.FUNDO);
        scrollTabela.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBackground(Cores.FUNDO);

        TitledBorder bordaTabela = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Ações do Plano de Desenvolvimento "
        );
        bordaTabela.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaTabela.setTitleColor(Cores.TEXTO_TITULO);
        painelTabela.setBorder(BorderFactory.createCompoundBorder(
            bordaTabela,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        painelTabela.add(scrollTabela, BorderLayout.CENTER);

        JPanel parteSuperior = new JPanel(new BorderLayout(0, 10));
        parteSuperior.setBackground(Cores.FUNDO);

        parteSuperior.add(selecao, BorderLayout.NORTH);
        parteSuperior.add(painelProgresso, BorderLayout.CENTER);

        JPanel conteudo = new JPanel(new BorderLayout(0, 15));
        conteudo.setBackground(Cores.FUNDO);

        conteudo.add(parteSuperior, BorderLayout.NORTH);
        conteudo.add(painelTabela, BorderLayout.CENTER);

        add(conteudo, BorderLayout.CENTER);
    }

    private void componente(
        JPanel painel,
        GridBagConstraints g,
        int linha,
        String texto,
        Component campo
    ) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setFont(new Font("SansSerif", Font.BOLD, 12));
        rotulo.setForeground(Cores.TEXTO_TITULO);

        g.gridx = 0;
        g.gridy = linha;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.FIRST_LINE_END;

        painel.add(rotulo, g);

        g.gridx = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.LINE_START;

        painel.add(campo, g);
    }

    // Getters

    public JComboBox<String> getArea() {
        return area;
    }

    public JComboBox<String> getFuncionario() {
        return funcionario;
    }

    public JButton getSelecionar() {
        return selecionar;
    }

    public JProgressBar getProgresso() {
        return progresso;
    }

    public JTable getTabela() {
        return tabela;
    }
}