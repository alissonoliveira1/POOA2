package trabalho_POOA;

import java.util.List;

public interface ForceUIUsuario<A> {

    void criarUsuario(A usuario);

    void atualizarUsuario(int id, String username, String senha);

    List<A> listarUsuarios();

    boolean deletarUsuario(int id);

    void alterarSenha(int id, String novaSenha);
}