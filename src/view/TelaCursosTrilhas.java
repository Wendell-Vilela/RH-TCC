package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import view.Cores;
import view.funcaoFacilitar;

public class TelaCursosTrilhas extends JPanel {

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

    private final JLabel horasRealizadas = new JLabel("Horas realizadas: 48h");
    private final JLabel cursosConcluidos = new JLabel("Cursos concluídos: 6");
    private final JLabel certificados = new JLabel("Certificados: 5");

    private final JTable tabelaCursosInternos = new JTable();
    private final JTable tabelaCursosExternos = new JTable();

    public TelaCursosTrilhas() {
        setLayout(new BorderLayout());
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        montar();
    }

    private void montar() {
        // 1. Painel de Seleção do Funcionário
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

        funcaoFacilitar.adicionarLinha(selecao, g, 0, "Área:", area);
        funcaoFacilitar.adicionarLinha(selecao, g, 1, "Funcionário:", funcionario);

        g.gridx = 2;
        g.gridy = 1;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;
        selecao.add(selecionar, g);

        // 2. Painel de Resumo
        JPanel resumo = new JPanel(new GridLayout(1, 3, 15, 5));
        resumo.setBackground(Cores.FUNDO);

        TitledBorder bordaResumo = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true), 
            " Resumo de Cursos "
        );
        bordaResumo.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaResumo.setTitleColor(Cores.TEXTO_TITULO);
        resumo.setBorder(BorderFactory.createCompoundBorder(
            bordaResumo, 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        horasRealizadas.setHorizontalAlignment(SwingConstants.CENTER);
        cursosConcluidos.setHorizontalAlignment(SwingConstants.CENTER);
        certificados.setHorizontalAlignment(SwingConstants.CENTER);

        horasRealizadas.setFont(new Font("SansSerif", Font.BOLD, 13));
        cursosConcluidos.setFont(new Font("SansSerif", Font.BOLD, 13));
        certificados.setFont(new Font("SansSerif", Font.BOLD, 13));

        resumo.add(horasRealizadas);
        resumo.add(cursosConcluidos);
        resumo.add(certificados);

        // 3. Tabela de Cursos Internos
        String[] colunas = {
            "Curso",
            "Carga Horária",
            "Fornecedor",
            "Status"
        };

        DefaultTableModel modeloInternos = new DefaultTableModel(colunas, 0);
        modeloInternos.addRow(new Object[]{"Java Básico", "20h", "Empresa", "Concluído"});
        modeloInternos.addRow(new Object[]{"Comunicação", "8h", "Empresa", "Em andamento"});
        modeloInternos.addRow(new Object[]{"Trabalho em Equipe", "10h", "Empresa", "Concluído"});

        tabelaCursosInternos.setModel(modeloInternos);
        tabelaCursosInternos.setRowHeight(25);
        tabelaCursosInternos.getColumnModel().getColumn(0).setPreferredWidth(250);
        tabelaCursosInternos.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabelaCursosInternos.getColumnModel().getColumn(2).setPreferredWidth(180);
        tabelaCursosInternos.getColumnModel().getColumn(3).setPreferredWidth(150);

        JScrollPane scrollInternos = new JScrollPane(tabelaCursosInternos);

        JPanel painelInternos = new JPanel(new BorderLayout());
        painelInternos.setBackground(Cores.FUNDO);

        TitledBorder bordaInternos = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true), 
            " Cursos Internos "
        );
        bordaInternos.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaInternos.setTitleColor(Cores.TEXTO_TITULO);
        painelInternos.setBorder(BorderFactory.createCompoundBorder(
            bordaInternos, 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        painelInternos.add(scrollInternos, BorderLayout.CENTER);

        // 4. Tabela de Cursos Externos
        DefaultTableModel modeloExternos = new DefaultTableModel(colunas, 0);
        modeloExternos.addRow(new Object[]{"Excel Avançado", "16h", "Alura", "Concluído"});
        modeloExternos.addRow(new Object[]{"Gestão de Projetos", "12h", "Udemy", "Concluído"});
        modeloExternos.addRow(new Object[]{"Power BI", "20h", "Coursera", "Em andamento"});

        tabelaCursosExternos.setModel(modeloExternos);
        tabelaCursosExternos.setRowHeight(25);
        tabelaCursosExternos.getColumnModel().getColumn(0).setPreferredWidth(250);
        tabelaCursosExternos.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabelaCursosExternos.getColumnModel().getColumn(2).setPreferredWidth(180);
        tabelaCursosExternos.getColumnModel().getColumn(3).setPreferredWidth(150);

        JScrollPane scrollExternos = new JScrollPane(tabelaCursosExternos);

        JPanel painelExternos = new JPanel(new BorderLayout());
        painelExternos.setBackground(Cores.FUNDO);

        TitledBorder bordaExternos = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true), 
            " Cursos Externos "
        );
        bordaExternos.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaExternos.setTitleColor(Cores.TEXTO_TITULO);
        painelExternos.setBorder(BorderFactory.createCompoundBorder(
            bordaExternos, 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        painelExternos.add(scrollExternos, BorderLayout.CENTER);

        // 5. Agrupamento das Tabelas
        JPanel tabelas = new JPanel(new GridLayout(2, 1, 5, 10));
        tabelas.setBackground(Cores.FUNDO);
        tabelas.add(painelInternos);
        tabelas.add(painelExternos);

        // 6. Estrutura de Conteúdo Principal
        JPanel parteSuperior = new JPanel(new BorderLayout(0, 10));
        parteSuperior.setBackground(Cores.FUNDO);
        parteSuperior.add(selecao, BorderLayout.NORTH);
        parteSuperior.add(resumo, BorderLayout.CENTER);

        JPanel conteudo = new JPanel(new BorderLayout(0, 10));
        conteudo.setBackground(Cores.FUNDO);
        conteudo.add(parteSuperior, BorderLayout.NORTH);
        conteudo.add(tabelas, BorderLayout.CENTER);

        add(conteudo, BorderLayout.CENTER);
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

    public JLabel getHorasRealizadas() {
        return horasRealizadas;
    }

    public JLabel getCursosConcluidos() {
        return cursosConcluidos;
    }

    public JLabel getCertificados() {
        return certificados;
    }

    public JTable getTabelaCursosInternos() {
        return tabelaCursosInternos;
    }

    public JTable getTabelaCursosExternos() {
        return tabelaCursosExternos;
    }
}