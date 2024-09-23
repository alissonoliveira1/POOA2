package entities;

import java.util.List;


public class Validacao {
    private List<Usuario> usuarios;

  
    public Validacao() {
     
    }

   
    public Validacao(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario validarLogin(String username, String senha) {
        if (usuarios == null) {
            return null;
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getUser().equals(username) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
}