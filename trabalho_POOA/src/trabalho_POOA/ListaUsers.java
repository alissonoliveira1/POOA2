package trabalho_POOA;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ListaUsers implements ForceUIUsuario<Usuario> {




	    private List<Usuario> usuarios = new ArrayList<>();
	  
	    private int count = 1;
	
	    @Override
	    public void criarUsuario(Usuario user) {
	        if (user.getUser() == null) {
	            user.setUser("user" + count++);
	        }
	        usuarios.add(user);
	    }

	    @Override
	    public void atualizarUsuario(int idUser, String username, String senha) {
	        Usuario usuario = usuarios.stream()
	            .filter(u -> u.getIdUser() == idUser)
	            .findFirst()
	            .orElse(null);

	        if (usuario != null) {
	            usuario.setUser(username);
	            usuario.setSenha(senha);
	        } else {
	            System.out.println("Usuário não encontrado.");
	        }
	    }

	    @Override
	    public List<Usuario> listarUsuarios() {
	        return Collections.unmodifiableList(usuarios);
	    }

	    @Override
	    public boolean deletarUsuario(int idUser) {
	        return usuarios.removeIf(usuario -> usuario.getIdUser() == idUser);
	    }

	    @Override
	    public void alterarSenha(int idUser, String novaSenha) {
	        Usuario usuario = usuarios.stream()
	            .filter(u -> u.getIdUser() == idUser)
	            .findFirst()
	            .orElse(null);

	        if (usuario != null) {
	            usuario.setSenha(novaSenha);
	            System.out.println("Senha alterada com sucesso.");
	        } else {
	            System.out.println("Usuário não encontrado.");
	        }
	    }
	}

