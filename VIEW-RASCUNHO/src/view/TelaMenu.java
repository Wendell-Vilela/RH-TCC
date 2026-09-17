package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class TelaMenu extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel painelConteudo;
    private CardLayout cardLayout;

    // Estruturas de controle dos grupos e botões do menu
    private final Map<JButton, JPanel> gruposSanfona = new HashMap<>();
    private final Map<JButton, String> titulosBotoesSanfona = new HashMap<>();
    private JButton subItemSelecionado = null;

    public TelaMenu() {
        setTitle("ERP RH - Módulo Recursos Humanos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1024, 600));
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(0, 0));

        painelConteudo = criarPainelConteudo();
        add(criarPainelLateralScroll(), BorderLayout.WEST);

        // Monta o lado DIREITO (Conteúdo)
        JPanel painelDireito = new JPanel(new BorderLayout(0, 0));
        painelDireito.add(painelConteudo, BorderLayout.CENTER);

        add(painelDireito, BorderLayout.CENTER);
    }

    private JScrollPane criarPainelLateralScroll() {
        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(new BoxLayout(menuContainer, BoxLayout.Y_AXIS));
        menuContainer.setBackground(Cores.AZUL_MENU);
        menuContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        // Logo ERP RH
        JPanel panelLogo = new JPanel();
        panelLogo.setLayout(new BoxLayout(panelLogo, BoxLayout.Y_AXIS));
        panelLogo.setOpaque(false);
        panelLogo.setMaximumSize(new Dimension(240, 50));
        panelLogo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 15));

        JLabel logo1 = new JLabel("ERP RH");
        logo1.setFont(new Font("SansSerif", Font.BOLD, 18));
        logo1.setForeground(Cores.BRANCO);

        JLabel logo2 = new JLabel("MÓDULO RECURSOS HUMANOS");
        logo2.setFont(new Font("SansSerif", Font.PLAIN, 9));
        logo2.setForeground(Cores.TEXTO_SUBMENU);

        panelLogo.add(logo1);
        panelLogo.add(Box.createVerticalStrut(2));
        panelLogo.add(logo2);

        menuContainer.add(panelLogo);
        menuContainer.add(Box.createVerticalStrut(15));

        // =========================================================================
        // 1. AVALIAÇÃO E DESENVOLVIMENTO
       
        JPanel subAvaliacao = new JPanel();
        configurarPainelSubmenu(subAvaliacao);
        adicionarSubItem(subAvaliacao, "Avaliações e Feedbacks", "CARD_AVAL_FEED", new TelaAvaliacoesFeedbacks());
        adicionarSubItem(subAvaliacao, "Metas e Competências", "CARD_METAS_COMP", new TelaMetasCompetencias());
        adicionarSubItem(subAvaliacao, "Cursos e Trilhas", "CARD_CURSOS_TRILHAS", new TelaCursosTrilhas());
        adicionarSubItem(subAvaliacao, "PDI", "CARD_PDI", new TelaPDI());
        adicionarGrupoSanfona(menuContainer, "Avaliação/Desenvolvimento", subAvaliacao);

        // =========================================================================
        // 2. RECRUTAMENTO E SELEÇÃO
        // =========================================================================
        JPanel subRecrutamento = new JPanel();
        configurarPainelSubmenu(subRecrutamento);
        adicionarSubItem(subRecrutamento, "Cadastro de Candidato", "CARD_CAD_CAND", new TelaCadastroCandidato());
        adicionarSubItem(subRecrutamento, "Cadastro de Vaga", "CARD_CAD_VAGA", new TelaCadastroVaga());
        adicionarSubItem(subRecrutamento, "Contratação", "CARD_CONTRAT", new TelaContratacao());
        adicionarSubItem(subRecrutamento, "Processo Seletivo", "CARD_PROC_SEL", new TelaProcessoSeletivo());
        adicionarGrupoSanfona(menuContainer, "Recrutamento e Seleção", subRecrutamento);

        // =========================================================================
        // 3. DASHBOARDS
        // =========================================================================
        JPanel subDashboards = new JPanel();
        configurarPainelSubmenu(subDashboards);
        adicionarSubItem(subDashboards, "Custo Mensal", "CARD_DASH_CUSTO_MENSAL", new CustoMensal());
        adicionarSubItem(subDashboards, "Gestão de Férias", "CARD_DASH_GEST_FERIAS", new GestaoFerias());
        adicionarSubItem(subDashboards, "Tendência de Absenteísmo", "CARD_DASH_TEND_ABSENT", new TendenciaAbsenteismo());
        adicionarSubItem(subDashboards, "Funcionários Ativos", "CARD_DASH_FUNC_ATIVOS", new ViewFuncionariosAtivos());
        adicionarSubItem(subDashboards, "Novas Admissões", "CARD_DASH_NOV_ADM", new ViewNovasAdmissoes());
        adicionarSubItem(subDashboards, "Taxa de Turnover", "CARD_DASH_TAXA_TURNOVER", new ViewTaxaTurnover());
        adicionarGrupoSanfona(menuContainer, "Dashboards", subDashboards);

        // =========================================================================
        // 4. GESTÃO DE FUNCIONÁRIOS
        // =========================================================================
        JPanel subGestao = new JPanel();
        configurarPainelSubmenu(subGestao);
        adicionarSubItem(subGestao, "Cadastro", "CARD_CAD_FUNC", new TelaCadastroFuncionario());
        adicionarSubItem(subGestao, "Dados Bancários", "CARD_DADOS_BANC", new TelaDadosBancarios());
        adicionarSubItem(subGestao, "Dados Pessoais", "CARD_DADOS_PESS", new TelaDadosPessoais());
        adicionarSubItem(subGestao, "Dependentes", "CARD_DEP", new TelaDependentes());
        adicionarSubItem(subGestao, "Documentos", "CARD_DOCS", new TelaDocumentos());
        adicionarSubItem(subGestao, "Histórico", "CARD_HIST", new TelaHistorico());
        adicionarGrupoSanfona(menuContainer, "Gestão de Funcionários", subGestao);

        // =========================================================================
        // 5. RELATÓRIOS E KPI'S
        // =========================================================================
        JPanel subRelatorios = new JPanel();
        configurarPainelSubmenu(subRelatorios);
        adicionarSubItem(subRelatorios, "Custo e Projeções", "CARD_CUSTO_PROJ", new TelaCusto());
        adicionarSubItem(subRelatorios, "Desempenho", "CARD_DESEMPENHO", new TelaDesempenho());
        adicionarSubItem(subRelatorios, "Rotatividade", "CARD_ROTATIVIDADE", new TelaRotatividade());
        adicionarSubItem(subRelatorios, "Visão Geral de Indicadores", "CARD_VGI", new TelaVGI());
        adicionarGrupoSanfona(menuContainer, "Relatórios e KPI's", subRelatorios);

        menuContainer.add(Box.createVerticalGlue());

        JScrollPane scroll = new JScrollPane(menuContainer);
        scroll.setPreferredSize(new Dimension(240, 0));
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(10);

        return scroll;
    }

    private JPanel criarPainelConteudo() {
        cardLayout = new CardLayout();
        JPanel painel = new JPanel(cardLayout);
        painel.setBackground(Cores.FUNDO);
        return painel;
    }

    private void configurarPainelSubmenu(JPanel sub) {
        sub.setLayout(new BoxLayout(sub, BoxLayout.Y_AXIS));
        sub.setBackground(Cores.AZUL_SUBMENU);
        sub.setVisible(false);
    }

    private void fecharTodasSanfonasExcepto(JPanel subMenuExcecao) {
        for (Map.Entry<JButton, JPanel> entry : gruposSanfona.entrySet()) {
            JButton btn = entry.getKey();
            JPanel sub = entry.getValue();

            if (sub != subMenuExcecao) {
                sub.setVisible(false);
                String tituloOriginal = titulosBotoesSanfona.get(btn);
                if (tituloOriginal != null) {
                    btn.setText(tituloOriginal + "  ▾");
                }
            }
        }
    }

    private void adicionarGrupoSanfona(JPanel container, String titulo, JPanel subMenu) {
        JButton btnPrincipal = criarBotaoMenu(titulo + "  ▾");
        titulosBotoesSanfona.put(btnPrincipal, titulo);

        btnPrincipal.addActionListener(e -> {
            boolean estaVisivel = subMenu.isVisible();

            fecharTodasSanfonasExcepto(subMenu);

            boolean novoEstado = !estaVisivel;
            subMenu.setVisible(novoEstado);
            btnPrincipal.setText(titulo + (novoEstado ? "  ▴" : "  ▾"));

            container.revalidate();
            container.repaint();
        });

        container.add(btnPrincipal);
        container.add(subMenu);
        gruposSanfona.put(btnPrincipal, subMenu);
    }

    private void adicionarSubItem(JPanel subMenu, String texto, String cardKey, Component tela) {
        JButton btnSub = new JButton("  └  " + texto);
        btnSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnSub.setForeground(Cores.TEXTO_SUBMENU);
        btnSub.setBackground(Cores.AZUL_SUBMENU);
        btnSub.setFocusPainted(false);
        btnSub.setBorderPainted(false);
        btnSub.setHorizontalAlignment(SwingConstants.LEFT);
        btnSub.setMaximumSize(new Dimension(240, 32));
        btnSub.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 10));
        btnSub.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btnSub != subItemSelecionado) {
                    btnSub.setBackground(Cores.AZUL_HOVER);
                    btnSub.setForeground(Cores.BRANCO);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (btnSub != subItemSelecionado) {
                    btnSub.setBackground(Cores.AZUL_SUBMENU);
                    btnSub.setForeground(Cores.TEXTO_SUBMENU);
                }
            }
        });

        btnSub.addActionListener(e -> {
            if (subItemSelecionado != null) {
                subItemSelecionado.setBackground(Cores.AZUL_SUBMENU);
                subItemSelecionado.setForeground(Cores.TEXTO_SUBMENU);
            }
            subItemSelecionado = btnSub;
            btnSub.setBackground(Cores.AZUL_HOVER);
            btnSub.setForeground(Cores.BRANCO);

            cardLayout.show(painelConteudo, cardKey);
        });

        painelConteudo.add(tela, cardKey);
        subMenu.add(btnSub);
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setForeground(Cores.BRANCO);
        btn.setBackground(Cores.AZUL_MENU);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(240, 42));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(Cores.AZUL_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Cores.AZUL_MENU);
            }
        });

        return btn;
    }
}