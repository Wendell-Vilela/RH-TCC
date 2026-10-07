package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseWheelListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.table.DefaultTableModel;

import controller.CadastroFuncionarioController;

public class TelaCadastroFuncionario extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField id =
            new JTextField(25);

    private final JTextField nome =
            new JTextField(30);

    private final JTextField matricula =
            new JTextField(25);

    private final JTextField cargo =
            new JTextField(30);

    private final JTextField departamento =
            new JTextField(30);

    private final JTextField email =
            new JTextField(30);

    private final JTextField telefone =
            new JTextField(25);

    private final JComboBox<String> status =
            new JComboBox<>(new String[] {
                    "Selecione o status",
                    "Ativo",
                    "Afastado",
                    "Ferias",
                    "Desligado"
            });

    private final JFormattedTextField nascimento =
            criarCampoData();

    private final JComboBox<String> estadoCivil =
            new JComboBox<>(new String[] {
                    "Selecione",
                    "Solteiro",
                    "Casado",
                    "Divorciado",
                    "Viuvo"
            });

    private final JTextField naturalidade =
            new JTextField(30);

    private final JComboBox<String> nacionalidade =
            new JComboBox<>(new String[] {
                    "Selecione",
                    "Brasil",
                    "Argentina",
                    "França",
                    "Uruguai"
            });

    private final JTextField endereco =
            new JTextField(35);

    private final JTextField cidade =
            new JTextField(30);

    private final JTextField banco =
            new JTextField(30);

    private final JTextField codigoBanco =
            new JTextField(20);

    private final JTextField agencia =
            new JTextField(20);

    private final JTextField conta =
            new JTextField(25);

    private final JComboBox<String> tipoConta =
            new JComboBox<>(new String[] {
                    "Selecione",
                    "Conta Corrente",
                    "Conta Poupanca",
                    "Conta Salario"
            });

    private final JTextField pix =
            new JTextField(30);

    private final JTextField cpf =
            new JTextField(25);

    private final JTextField rg =
            new JTextField(25);

    private final JTextField passaporte =
            new JTextField(25);

    private final JFormattedTextField validadePassaporte =
            criarCampoData();

    private final JTextField pis =
            new JTextField(25);

    private final JTextField ctps =
            new JTextField(25);

    private final JTextField dependenteNome =
            new JTextField(30);

    private final JComboBox<String> dependenteParentesco =
            new JComboBox<>(new String[] {
                    "Selecione",
                    "Conjuge",
                    "Filho",
                    "Filha",
                    "Pai",
                    "Mae",
                    "Outro"
            });

    private final JFormattedTextField dependenteNascimento =
            criarCampoData();

    private final JTextField dependenteAssistencia =
            new JTextField(30);

    private final DefaultTableModel dependentesModelo =
            new DefaultTableModel(
                    new String[] {
                            "Nome",
                            "Parentesco",
                            "Nascimento",
                            "Assistencia"
                    },
                    0
            ) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(
                        int row,
                        int column
                ) {
                    return false;
                }
            };

    private final JTable tabelaDependentes =
            new JTable(dependentesModelo);

    private final JTextField historicoAno =
            new JTextField(10);

    private final JTextField historicoEvento =
            new JTextField(30);

    private final JTextField historicoCargo =
            new JTextField(30);

    private final JTextArea historicoObservacao =
            new JTextArea(5, 35);

    private final DefaultTableModel historicoModelo =
            new DefaultTableModel(
                    new String[] {
                            "Ano",
                            "Evento",
                            "Cargo",
                            "Observacao"
                    },
                    0
            ) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(
                        int row,
                        int column
                ) {
                    return false;
                }
            };

    private final JTable tabelaHistorico =
            new JTable(historicoModelo);

    private final JButton novo =
            new JButton("Novo");

    private final JButton salvar =
            new JButton("Salvar funcionário");

    private final JButton excluir =
            new JButton("Excluir");

    private final JButton limpar =
            new JButton("Limpar");

    private final JButton adicionarDependente =
            new JButton("Adicionar dependente");

    private final JButton removerDependente =
            new JButton("Remover selecionado");

    private final JButton adicionarHistorico =
            new JButton("Adicionar registro");

    private final JButton removerHistorico =
            new JButton("Remover selecionado");

    private final CadastroFuncionarioController controller;

    public TelaCadastroFuncionario() {

        setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );

        montarTela();

        controller =
                new CadastroFuncionarioController(
                        this
                );
    }

    private JFormattedTextField criarCampoData() {

        try {

            MaskFormatter mascara =
                    new MaskFormatter(
                            "##/##/####"
                    );

            mascara.setPlaceholderCharacter(
                    ' '
            );

            JFormattedTextField campo =
                    new JFormattedTextField(
                            mascara
                    );

            campo.setColumns(10);

            return campo;

        } catch (ParseException e) {

            throw new IllegalStateException(
                    "Erro ao criar campo de data.",
                    e
            );
        }
    }

    private void montarTela() {

        id.setEditable(false);

        historicoObservacao.setLineWrap(true);
        historicoObservacao.setWrapStyleWord(true);

        JTabbedPane abas =
                new JTabbedPane();

        JPanel telaDados =
                criarTelaDados();

        JPanel telaComplementos =
                criarTelaComplementos();

        JScrollPane scrollDados =
                criarScroll(
                        telaDados
                );

        JScrollPane scrollComplementos =
                criarScroll(
                        telaComplementos
                );

        abas.addTab(
                "Dados do Funcionário",
                scrollDados
        );

        abas.addTab(
                "Dependentes e Histórico",
                scrollComplementos
        );

        add(
                criarCabecalho(),
                BorderLayout.NORTH
        );

        add(
                abas,
                BorderLayout.CENTER
        );

        habilitarRolagem(
                telaDados,
                scrollDados
        );

        habilitarRolagem(
                scrollDados,
                scrollDados
        );

        habilitarRolagem(
                telaComplementos,
                scrollComplementos
        );

        habilitarRolagem(
                scrollComplementos,
                scrollComplementos
        );
    }

    private JScrollPane criarScroll(
            JPanel painel
    ) {

        JScrollPane scroll =
                new JScrollPane(
                        painel
                );

        scroll.setBorder(null);

        scroll.setWheelScrollingEnabled(
                true
        );

        scroll.getVerticalScrollBar()
                .setUnitIncrement(20);

        return scroll;
    }

    private JPanel criarCabecalho() {

        JPanel painel =
                new JPanel(
                        new BorderLayout()
                );

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        20,
                        12,
                        20
                )
        );

        JLabel titulo =
                new JLabel(
                        "Gestão de Funcionários"
                );

        JPanel botoes =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        botoes.add(novo);
        botoes.add(salvar);
        botoes.add(excluir);
        botoes.add(limpar);

        painel.add(
                titulo,
                BorderLayout.WEST
        );

        painel.add(
                botoes,
                BorderLayout.EAST
        );

        return painel;
    }

    private JPanel criarTelaDados() {

        JPanel painel =
                new JPanel(
                        new GridBagLayout()
                );

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        40,
                        40,
                        40
                )
        );

        adicionarSecao(
                painel,
                "1. Cadastro de Funcionário",
                new Component[] {
                        criarCampo(
                                "Código",
                                id
                        ),
                        criarCampo(
                                "Nome completo",
                                nome
                        ),
                        criarCampo(
                                "Matrícula",
                                matricula
                        ),
                        criarCampo(
                                "Cargo",
                                cargo
                        ),
                        criarCampo(
                                "Departamento",
                                departamento
                        ),
                        criarCampo(
                                "E-mail",
                                email
                        ),
                        criarCampo(
                                "Telefone",
                                telefone
                        ),
                        criarCampo(
                                "Status",
                                status
                        )
                }
        );

        adicionarSecao(
                painel,
                "2. Dados Pessoais",
                new Component[] {
                        criarCampo(
                                "Nascimento",
                                nascimento
                        ),
                        criarCampo(
                                "Estado civil",
                                estadoCivil
                        ),
                        criarCampo(
                                "Naturalidade",
                                naturalidade
                        ),
                        criarCampo(
                                "Nacionalidade",
                                nacionalidade
                        ),
                        criarCampo(
                                "Endereço",
                                endereco
                        ),
                        criarCampo(
                                "Cidade",
                                cidade
                        )
                }
        );

        adicionarSecao(
                painel,
                "3. Dados Bancários",
                new Component[] {
                        criarCampo(
                                "Banco",
                                banco
                        ),
                        criarCampo(
                                "Código do banco",
                                codigoBanco
                        ),
                        criarCampo(
                                "Agência",
                                agencia
                        ),
                        criarCampo(
                                "Conta",
                                conta
                        ),
                        criarCampo(
                                "Tipo de conta",
                                tipoConta
                        ),
                        criarCampo(
                                "PIX",
                                pix
                        )
                }
        );

        adicionarSecao(
                painel,
                "4. Documentos",
                new Component[] {
                        criarCampo(
                                "CPF",
                                cpf
                        ),
                        criarCampo(
                                "RG",
                                rg
                        ),
                        criarCampo(
                                "Passaporte",
                                passaporte
                        ),
                        criarCampo(
                                "Validade do passaporte",
                                validadePassaporte
                        ),
                        criarCampo(
                                "PIS",
                                pis
                        ),
                        criarCampo(
                                "CTPS",
                                ctps
                        )
                }
        );

        return painel;
    }

    private JPanel criarTelaComplementos() {

        JPanel painel =
                new JPanel(
                        new GridBagLayout()
                );

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        40,
                        40,
                        40
                )
        );

        adicionarSecao(
                painel,
                "5. Dependentes",
                new Component[] {
                        criarCampo(
                                "Nome",
                                dependenteNome
                        ),
                        criarCampo(
                                "Parentesco",
                                dependenteParentesco
                        ),
                        criarCampo(
                                "Nascimento",
                                dependenteNascimento
                        ),
                        criarCampo(
                                "Assistência",
                                dependenteAssistencia
                        )
                }
        );

        adicionarDependentesTabela(
                painel
        );

        adicionarSecao(
                painel,
                "6. Histórico",
                new Component[] {
                        criarCampo(
                                "Ano",
                                historicoAno
                        ),
                        criarCampo(
                                "Evento",
                                historicoEvento
                        ),
                        criarCampo(
                                "Cargo",
                                historicoCargo
                        ),
                        criarCampo(
                                "Observação",
                                new JScrollPane(
                                        historicoObservacao
                                )
                        )
                }
        );

        adicionarHistoricoTabela(
                painel
        );

        return painel;
    }

    private JPanel criarCampo(
            String texto,
            Component campo
    ) {

        JPanel painel =
                new JPanel(
                        new BorderLayout(
                                0,
                                6
                        )
                );

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        15,
                        0
                )
        );

        painel.add(
                new JLabel(texto),
                BorderLayout.NORTH
        );

        painel.add(
                campo,
                BorderLayout.CENTER
        );

        return painel;
    }

    private void adicionarSecao(
            JPanel painel,
            String titulo,
            Component[] campos
    ) {

        GridBagConstraints tituloGbc =
                new GridBagConstraints();

        tituloGbc.gridx = 0;
        tituloGbc.gridy =
                painel.getComponentCount();

        tituloGbc.weightx = 1;
        tituloGbc.fill =
                GridBagConstraints.HORIZONTAL;
        tituloGbc.anchor =
                GridBagConstraints.WEST;

        tituloGbc.insets =
                new Insets(
                        5,
                        0,
                        8,
                        0
                );

        painel.add(
                new JLabel(titulo),
                tituloGbc
        );

        GridBagConstraints separadorGbc =
                new GridBagConstraints();

        separadorGbc.gridx = 0;
        separadorGbc.gridy =
                painel.getComponentCount();

        separadorGbc.weightx = 1;
        separadorGbc.fill =
                GridBagConstraints.HORIZONTAL;

        separadorGbc.insets =
                new Insets(
                        0,
                        0,
                        20,
                        0
                );

        painel.add(
                new JSeparator(),
                separadorGbc
        );

        for (
                Component campo :
                campos
        ) {

            GridBagConstraints campoGbc =
                    new GridBagConstraints();

            campoGbc.gridx = 0;
            campoGbc.gridy =
                    painel.getComponentCount();

            campoGbc.weightx = 1;

            campoGbc.fill =
                    GridBagConstraints.HORIZONTAL;

            campoGbc.anchor =
                    GridBagConstraints.WEST;

            campoGbc.insets =
                    new Insets(
                            0,
                            0,
                            8,
                            0
                    );

            painel.add(
                    campo,
                    campoGbc
            );
        }

        JPanel espaco =
                new JPanel();

        GridBagConstraints espacoGbc =
                new GridBagConstraints();

        espacoGbc.gridx = 0;
        espacoGbc.gridy =
                painel.getComponentCount();

        espacoGbc.weightx = 1;
        espacoGbc.fill =
                GridBagConstraints.HORIZONTAL;

        espacoGbc.insets =
                new Insets(
                        0,
                        0,
                        20,
                        0
                );

        painel.add(
                espaco,
                espacoGbc
        );
    }

    private void adicionarDependentesTabela(
            JPanel painel
    ) {

        JPanel botoes =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                5
                        )
                );

        botoes.add(
                adicionarDependente
        );

        botoes.add(
                removerDependente
        );

        GridBagConstraints botoesGbc =
                new GridBagConstraints();

        botoesGbc.gridx = 0;
        botoesGbc.gridy =
                painel.getComponentCount();

        botoesGbc.weightx = 1;

        botoesGbc.fill =
                GridBagConstraints.HORIZONTAL;

        botoesGbc.anchor =
                GridBagConstraints.WEST;

        botoesGbc.insets =
                new Insets(
                        0,
                        0,
                        12,
                        0
                );

        painel.add(
                botoes,
                botoesGbc
        );

        tabelaDependentes
                .setFillsViewportHeight(
                        true
                );

        tabelaDependentes
                .setRowHeight(24);

        JScrollPane scroll =
                new JScrollPane(
                        tabelaDependentes
                );

        GridBagConstraints tabelaGbc =
                new GridBagConstraints();

        tabelaGbc.gridx = 0;
        tabelaGbc.gridy =
                painel.getComponentCount();

        tabelaGbc.weightx = 1;

        tabelaGbc.weighty = 1;

        tabelaGbc.fill =
                GridBagConstraints.BOTH;

        tabelaGbc.insets =
                new Insets(
                        0,
                        0,
                        30,
                        0
                );

        painel.add(
                scroll,
                tabelaGbc
        );
    }

    private void adicionarHistoricoTabela(
            JPanel painel
    ) {

        JPanel botoes =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                5
                        )
                );

        botoes.add(
                adicionarHistorico
        );

        botoes.add(
                removerHistorico
        );

        GridBagConstraints botoesGbc =
                new GridBagConstraints();

        botoesGbc.gridx = 0;
        botoesGbc.gridy =
                painel.getComponentCount();

        botoesGbc.weightx = 1;

        botoesGbc.fill =
                GridBagConstraints.HORIZONTAL;

        botoesGbc.anchor =
                GridBagConstraints.WEST;

        botoesGbc.insets =
                new Insets(
                        0,
                        0,
                        12,
                        0
                );

        painel.add(
                botoes,
                botoesGbc
        );

        tabelaHistorico
                .setFillsViewportHeight(
                        true
                );

        tabelaHistorico
                .setRowHeight(24);

        JScrollPane scroll =
                new JScrollPane(
                        tabelaHistorico
                );

        GridBagConstraints tabelaGbc =
                new GridBagConstraints();

        tabelaGbc.gridx = 0;
        tabelaGbc.gridy =
                painel.getComponentCount();

        tabelaGbc.weightx = 1;

        tabelaGbc.weighty = 1;

        tabelaGbc.fill =
                GridBagConstraints.BOTH;

        tabelaGbc.insets =
                new Insets(
                        0,
                        0,
                        20,
                        0
                );

        painel.add(
                scroll,
                tabelaGbc
        );
    }

    private void habilitarRolagem(
            Component componente,
            JScrollPane scrollPrincipal
    ) {

        MouseWheelListener listener =
                e -> {

                    JScrollBar barra =
                            scrollPrincipal
                                    .getVerticalScrollBar();

                    if (!barra.isVisible()) {
                        return;
                    }

                    int movimento =
                            e.getWheelRotation()
                                    *
                            barra.getUnitIncrement()
                                    *
                            2;

                    int novoValor =
                            barra.getValue()
                                    +
                            movimento;

                    int maximo =
                            barra.getMaximum()
                                    -
                            barra.getVisibleAmount();

                    novoValor =
                            Math.max(
                                    barra.getMinimum(),
                                    Math.min(
                                            novoValor,
                                            maximo
                                    )
                            );

                    barra.setValue(
                            novoValor
                    );

                    e.consume();
                };

        componente.addMouseWheelListener(
                listener
        );

        if (
                componente instanceof Container
        ) {

            Container container =
                    (Container) componente;

            for (
                    Component filho :
                    container.getComponents()
            ) {

                habilitarRolagem(
                        filho,
                        scrollPrincipal
                );
            }
        }
    }

    public void adicionarDependenteNaTabela() {

        String nomeDependente =
                texto(
                        dependenteNome
                );

        if (
                nomeDependente.isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "Informe o nome do dependente."
            );
        }

        dependentesModelo.addRow(
                new Object[] {
                        nomeDependente,
                        dependenteParentesco
                                .getSelectedItem(),
                        textoData(
                                dependenteNascimento
                        ),
                        texto(
                                dependenteAssistencia
                        )
                }
        );

        limparDependente();
    }

    public void removerDependenteSelecionado() {

        int linha =
                tabelaDependentes
                        .getSelectedRow();

        if (linha >= 0) {

            dependentesModelo
                    .removeRow(linha);
        }
    }

    public void adicionarHistoricoNaTabela() {

        if (
                texto(historicoAno).isEmpty()
                        &&
                texto(historicoEvento).isEmpty()
                        &&
                texto(historicoCargo).isEmpty()
                        &&
                texto(historicoObservacao).isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "Informe pelo menos um dado do histórico."
            );
        }

        historicoModelo.addRow(
                new Object[] {
                        texto(historicoAno),
                        texto(historicoEvento),
                        texto(historicoCargo),
                        texto(historicoObservacao)
                }
        );

        limparHistorico();
    }

    public void removerHistoricoSelecionado() {

        int linha =
                tabelaHistorico
                        .getSelectedRow();

        if (linha >= 0) {

            historicoModelo
                    .removeRow(linha);
        }
    }

    private void limparDependente() {

        dependenteNome.setText("");

        dependenteParentesco
                .setSelectedIndex(0);

        dependenteNascimento
                .setValue(null);

        dependenteAssistencia
                .setText("");
    }

    private void limparHistorico() {

        historicoAno.setText("");

        historicoEvento.setText("");

        historicoCargo.setText("");

        historicoObservacao
                .setText("");
    }

    public void limparFormulario() {

        id.setText("");

        nome.setText("");

        matricula.setText("");

        cargo.setText("");

        departamento.setText("");

        email.setText("");

        telefone.setText("");

        status.setSelectedIndex(0);

        nascimento.setValue(null);

        estadoCivil.setSelectedIndex(0);

        naturalidade.setText("");

        nacionalidade.setSelectedIndex(0);

        endereco.setText("");

        cidade.setText("");

        banco.setText("");

        codigoBanco.setText("");

        agencia.setText("");

        conta.setText("");

        tipoConta.setSelectedIndex(0);

        pix.setText("");

        cpf.setText("");

        rg.setText("");

        passaporte.setText("");

        validadePassaporte
                .setValue(null);

        pis.setText("");

        ctps.setText("");

        dependentesModelo
                .setRowCount(0);

        historicoModelo
                .setRowCount(0);

        limparDependente();

        limparHistorico();
    }

    public List<String[]> obterDependentes() {

        List<String[]> dados =
                new ArrayList<>();

        for (
                int i = 0;
                i < dependentesModelo.getRowCount();
                i++
        ) {

            dados.add(
                    new String[] {
                            valor(
                                    dependentesModelo,
                                    i,
                                    0
                            ),
                            valor(
                                    dependentesModelo,
                                    i,
                                    1
                            ),
                            valor(
                                    dependentesModelo,
                                    i,
                                    2
                            ),
                            valor(
                                    dependentesModelo,
                                    i,
                                    3
                            )
                    }
            );
        }

        return dados;
    }

    public List<String[]> obterHistorico() {

        List<String[]> dados =
                new ArrayList<>();

        for (
                int i = 0;
                i < historicoModelo.getRowCount();
                i++
        ) {

            dados.add(
                    new String[] {
                            valor(
                                    historicoModelo,
                                    i,
                                    0
                            ),
                            valor(
                                    historicoModelo,
                                    i,
                                    1
                            ),
                            valor(
                                    historicoModelo,
                                    i,
                                    2
                            ),
                            valor(
                                    historicoModelo,
                                    i,
                                    3
                            )
                    }
            );
        }

        return dados;
    }

    private String valor(
            DefaultTableModel modelo,
            int linha,
            int coluna
    ) {

        Object valor =
                modelo.getValueAt(
                        linha,
                        coluna
                );

        return valor == null
                ? ""
                : valor.toString();
    }

    private String texto(
            JTextField campo
    ) {

        return campo
                .getText()
                .trim();
    }

    private String texto(
            JTextArea campo
    ) {

        return campo
                .getText()
                .trim();
    }

    private String textoData(
            JTextField campo
    ) {

        String valor =
                campo.getText();

        String numeros =
                valor.replaceAll(
                        "\\D",
                        ""
                );

        if (
                numeros.length() != 8
        ) {

            return "";
        }

        return valor.trim();
    }

    public int getId() {

        return id.getText().isEmpty()
                ? 0
                : Integer.parseInt(
                        id.getText()
                );
    }

    public void setId(
            int valor
    ) {

        id.setText(
                String.valueOf(valor)
        );
    }

    public String getNome() {
        return texto(nome);
    }

    public String getMatricula() {
        return texto(matricula);
    }

    public String getCargo() {
        return texto(cargo);
    }

    public String getDepartamento() {
        return texto(departamento);
    }

    public String getEmail() {
        return texto(email);
    }

    public String getTelefone() {
        return texto(telefone);
    }

    public String getStatus() {
        return String.valueOf(
                status.getSelectedItem()
        );
    }

    public String getNascimento() {
        return textoData(
                nascimento
        );
    }

    public String getEstadoCivil() {
        return String.valueOf(
                estadoCivil.getSelectedItem()
        );
    }

    public String getNaturalidade() {
        return texto(naturalidade);
    }

    public String getNacionalidade() {
        return String.valueOf(
                nacionalidade.getSelectedItem()
        );
    }

    public String getEndereco() {
        return texto(endereco);
    }

    public String getCidade() {
        return texto(cidade);
    }

    public String getBanco() {
        return texto(banco);
    }

    public String getCodigoBanco() {
        return texto(codigoBanco);
    }

    public String getAgencia() {
        return texto(agencia);
    }

    public String getConta() {
        return texto(conta);
    }

    public String getTipoConta() {
        return String.valueOf(
                tipoConta.getSelectedItem()
        );
    }

    public String getPix() {
        return texto(pix);
    }

    public String getCpf() {
        return texto(cpf);
    }

    public String getRg() {
        return texto(rg);
    }

    public String getPassaporte() {
        return texto(passaporte);
    }

    public String getValidadePassaporte() {
        return textoData(
                validadePassaporte
        );
    }

    public String getPis() {
        return texto(pis);
    }

    public String getCtps() {
        return texto(ctps);
    }

    public JButton getNovo() {
        return novo;
    }

    public JButton getSalvar() {
        return salvar;
    }

    public JButton getExcluir() {
        return excluir;
    }

    public JButton getLimpar() {
        return limpar;
    }

    public JButton getAdicionarDependente() {
        return adicionarDependente;
    }

    public JButton getRemoverDependente() {
        return removerDependente;
    }

    public JButton getAdicionarHistorico() {
        return adicionarHistorico;
    }

    public JButton getRemoverHistorico() {
        return removerHistorico;
    }

    public void setIdentificacao(
            String texto
    ) {
    }
}