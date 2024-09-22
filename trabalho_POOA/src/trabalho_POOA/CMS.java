package trabalho_POOA;

import java.util.List;
import java.util.Scanner;

public class CMS extends AUI {
    private Scanner sc;
    private Validacao validacao;
    private DadosService dadosService;

    public CMS() {
        BancoInsert bancoInsert = new BancoInsert();
        this.sc = new Scanner(System.in);
        this.dadosService = new DadosService(bancoInsert, bancoInsert);
        this.validacao = new Validacao();

        
        List<Usuario> usuarios = dadosService.listarUsuarios();
        validacao.setUsuarios(usuarios);
    }

    public Usuario iniciarMenu() {
        while (true) {
            System.out.println("1. Entrar");
            System.out.println("2. Listar Conteúdo");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o username: ");
                    String username = sc.nextLine();
                    System.out.print("Digite a senha: ");
                    String password = sc.nextLine();
                    Usuario usuario = validacao.validarLogin(username, password);
                    if (usuario != null) {
                        return usuario;
                    }
                    System.out.println("Login inválido.");
                    break;
                case 2:
                    listarConteudo();
                    break;
                case 3:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private void listarConteudo() {
        for (DadosInsert dadosInsert : dadosService.listarConteudos()) {
            System.out.println(dadosInsert);
        }
    }

    private String lerInfo(String label) {
        System.out.print(label + ": ");
        return sc.nextLine().trim();
    }

    public Usuario menulogado(Usuario currentUser) {
        while (true) {
            System.out.println("1. Criar Conteúdo");
            System.out.println("2. Listar Conteúdo");
            System.out.println("3. Atualizar Conteúdo");
            System.out.println("4. Excluir Conteúdo");
            System.out.println("5. Criar Usuário");
            System.out.println("6. Listar Usuários");
            System.out.println("7. Alterar Usuário");
            System.out.println("8. Excluir Usuário");
            System.out.println("9. Alterar Senha");
            System.out.println("10. Logout");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao(); 

            switch (opcao) {
                case 1:
                    String titulo = lerInfo("Digite o Título");
                    String texto = lerInfo("Digite o Texto");
                    DadosInsert conteudo = new DadosInsert(null, titulo, texto, currentUser);
                    dadosService.salvar(conteudo);
                    System.out.println("Conteúdo criado!");
                    break;
                case 2:
                    listarConteudo();
                    break;
                case 3:
                    int id = lerId("Digite o ID do conteúdo para atualizar");
                    titulo = lerInfo("Digite o Título");
                    texto = lerInfo("Digite o Texto");
                    dadosService.atualizarcont(id, titulo, texto, currentUser);
                    System.out.println("Conteúdo atualizado.");
                    break;
                case 4:
                    id = lerId("Digite o ID do conteúdo para excluir");
                    if (dadosService.deletaConteudo(id)) {
                        System.out.println("Conteúdo excluído.");
                    } else {
                        System.out.println("Conteúdo não encontrado.");
                    }
                    break;
                case 5:
                    String novoUser = lerInfo("Digite o novo username");
                    String novaSenha = lerInfo("Digite a nova senha");
                    Usuario novoUsuario = new Usuario(novoUser, novaSenha);
                    dadosService.criarUsuario(novoUsuario);
                    System.out.println("Usuário criado.");
                    break;
                case 6:
                    dadosService.listarUsuarios().forEach(System.out::println);
                    break;
                case 7:
                    int idUsuario = lerId("Digite o ID do usuário para alterar");
                    String username = lerInfo("Digite o novo username");
                    novaSenha = lerInfo("Digite a nova senha");
                    dadosService.atualizarUsuario(idUsuario, username, novaSenha);
                    System.out.println("Usuário alterado.");
                    break;
                case 8:
                    idUsuario = lerId("Digite o ID do usuário para excluir");
                    if (dadosService.deletarUsuario(idUsuario)) {
                        System.out.println("Usuário excluído.");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 9:
                    String senhaAtual = lerInfo("Digite a senha atual");
                    if (currentUser.getSenha().equals(senhaAtual)) {
                        novaSenha = lerInfo("Digite a nova senha");
                        currentUser.setSenha(novaSenha);
                        System.out.println("Senha alterada com sucesso.");
                    } else {
                        System.out.println("Senha atual incorreta.");
                    }
                    break;
                case 10:
                    return null;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private int lerId(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(lerInfo(prompt));
            } catch (NumberFormatException e) {
                System.out.println("ID inválido. Tente novamente.");
            }
        }
    }

    
    private int lerOpcao() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            }
        }
    }
}
