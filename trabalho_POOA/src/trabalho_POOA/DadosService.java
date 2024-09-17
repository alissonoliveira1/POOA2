package trabalho_POOA;

import java.util.List;

public class DadosService {

    private ForceUI<DadosInsert> forceUI;
    private ForceUIUsuario<Usuario> forceUIUsuario;

    public DadosService(ForceUI<DadosInsert> forceUI, ForceUIUsuario<Usuario> forceUIUsuario) {
        this.forceUI = forceUI;
        this.forceUIUsuario = forceUIUsuario;
    }
    public DadosService(ForceUI<DadosInsert> forceUI) {
        this.forceUI = forceUI;
    }

    
    public void salvar(DadosInsert dadosInsert) {
    	forceUI.inserirDados(dadosInsert);
    }

    public void atualizarcont(int id, String titulo, String texto, Usuario autor) {
        DadosInsert conteudoExistente = forceUI.listar().stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElse(null);

        if (conteudoExistente != null) {
            conteudoExistente.setTitulo(titulo);
            conteudoExistente.setTexto(texto);
            conteudoExistente.setAutor(autor);
            forceUI.atualizar(conteudoExistente);
        } else {
            System.out.println("Conteúdo não encontrado.");
        }
    }

    public List<DadosInsert> listarConteudos() {
        return forceUI.listar();
    }

    public boolean deletaConteudo(int id) {
        return forceUI.deleta(id);
    }


    public void criarUsuario(Usuario usuario) {
    	forceUIUsuario.criarUsuario(usuario);
    }

    public void atualizarUsuario(int idUser, String username, String senha) {
    	forceUIUsuario.atualizarUsuario(idUser, username, senha);
    }

    public List<Usuario> listarUsuarios() {
        return forceUIUsuario.listarUsuarios();
    }

    public boolean deletarUsuario(int idUser) {
        return forceUIUsuario.deletarUsuario(idUser);
    }

    public void alterarSenhaUsuario(int idUser, String novaSenha) {
    	forceUIUsuario.alterarSenha(idUser, novaSenha);
    }
}