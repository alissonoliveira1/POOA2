package trabalho_POOA;
import java.util.ArrayList;
import java.util.List;

public class Validacao {

    private List<Usuario> usuarios;

 
    public Validacao() {
        this.usuarios = new ArrayList<>();
    }

  
    public Validacao(List<Usuario> usuarios) {
        this.usuarios = usuarios != null ? usuarios : new ArrayList<>();
    }

    
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios != null ? usuarios : new ArrayList<>();
    }

    
    public Usuario validarLogin(String user, String senha) {
        if (user != null && senha != null) {
            for (Usuario usuario : usuarios) {
                if (usuario.getUser().equals(user) && usuario.getSenha().equals(senha)) {
                    return usuario;
                }
            }
        }
        return null; 
    }
}