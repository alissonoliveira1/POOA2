package trabalho_POOA;

public class Usuario {
    private String user;
    private String senha;
    private Integer idUser;

   
    public Usuario() {
    }

 
    public Usuario(String user, String senha) {
        this.user = user;
        this.senha = senha;
    }

 
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public boolean autenticar(String user, String senha) {
        return this.user.equals(user) && this.senha.equals(senha);
    }
}