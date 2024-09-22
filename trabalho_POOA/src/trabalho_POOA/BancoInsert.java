package trabalho_POOA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BancoInsert implements ForceUI<DadosInsert>, ForceUIUsuario<Usuario> {

    private static final String URL = "jdbc:hsqldb:file:BancoDeDados/";
    private static final String NOMEDOBANCO = "Cms;hsqldb.lock_file=false";
    private static final String USER = "SA";
    private static final String PASSWORD = "";
    private Connection connection = null;

    public BancoInsert() {
        try {
            
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
          
            criarTabelaConteudo();
            criarTabelaUsuario();
            adicionarUsuarioInicial(); 
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL + NOMEDOBANCO, USER, PASSWORD);
        }
        return connection;
    }

    @Override
    public void inserirDados(DadosInsert dadosInsert) {
        String sql = "INSERT INTO CONTEUDO (titulo, texto, autor) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, dadosInsert.getTitulo());
            stmt.setString(2, dadosInsert.getTexto());
            stmt.setString(3, dadosInsert.getAutor().getUser());
            stmt.executeUpdate();
            System.out.println("Conteúdo inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir o conteúdo: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(DadosInsert dadosInsert) {
        String sql = "UPDATE CONTEUDO SET titulo = ?, texto = ?, autor = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, dadosInsert.getTitulo());
            stmt.setString(2, dadosInsert.getTexto());
            stmt.setString(3, dadosInsert.getAutor().getUser());
            stmt.setInt(4, dadosInsert.getId());
            stmt.executeUpdate();
            System.out.println("Conteúdo atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o conteúdo: " + e.getMessage());
        }
    }

    @Override
    public List<DadosInsert> listar() {
        List<DadosInsert> conteudos = new ArrayList<>();
        String sql = "SELECT * FROM CONTEUDO";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DadosInsert dadosInsert = new DadosInsert();
                dadosInsert.setId(rs.getInt("id"));
                dadosInsert.setTitulo(rs.getString("titulo"));
                dadosInsert.setTexto(rs.getString("texto"));
                Usuario autor = new Usuario();
                autor.setUser(rs.getString("autor"));
                dadosInsert.setAutor(autor);
                conteudos.add(dadosInsert);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar os conteúdos: " + e.getMessage());
        }
        return conteudos;
    }

    @Override
    public boolean deleta(int id) {
        String sql = "DELETE FROM CONTEUDO WHERE id = ?";
        boolean delete = false;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            delete = (rowsAffected > 0);
            if (delete) {
                System.out.println("Conteúdo " + id + " deletado com sucesso!");
            } else {
                System.out.println("Conteúdo " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar o conteúdo: " + e.getMessage());
        }
        return delete;
    }

    @Override
    public void criarUsuario(Usuario usuario) {
     
        if (usuarioJaExiste(usuario.getUser())) {
            System.err.println("Erro: O username já está em uso.");
            return;
        }

        String sql = "INSERT INTO USUARIO (username, senha) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUser());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();
            System.out.println("Usuário criado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar o usuário: " + e.getMessage());
        }
    }

    private boolean usuarioJaExiste(String username) {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar se o usuário já existe: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void atualizarUsuario(int idUser, String username, String senha) {
        String sql = "UPDATE USUARIO SET username = ?, senha = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, senha);
            stmt.setInt(3, idUser);
            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o usuário: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUser(rs.getInt("id"));
                usuario.setUser(rs.getString("username"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os usuários: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public boolean deletarUsuario(int idUser) {
        String sql = "DELETE FROM USUARIO WHERE id = ?";
        boolean delete = false;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            int rowsAffected = stmt.executeUpdate();
            delete = (rowsAffected > 0);
            if (delete) {
                System.out.println("Usuário " + idUser + " deletado com sucesso!");
            } else {
                System.out.println("Usuário " + idUser + " não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o usuário: " + e.getMessage());
        }
        return delete;
    }

    @Override
    public void alterarSenha(int idUser, String novaSenha) {
        String sql = "UPDATE USUARIO SET senha = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, novaSenha);
            stmt.setInt(2, idUser);
            stmt.executeUpdate();
            System.out.println("Senha alterada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao alterar a senha do usuário: " + e.getMessage());
        }
    }

    private void criarTabelaUsuario() {
        String sql = "CREATE TABLE IF NOT EXISTS USUARIO (" +
                "id IDENTITY PRIMARY KEY, " +
                "username VARCHAR(150) UNIQUE NOT NULL, " +
                "senha VARCHAR(150) NOT NULL)";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de usuários criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela de usuários: " + e.getMessage());
        }
    }

    private void criarTabelaConteudo() {
        String sql = "CREATE TABLE IF NOT EXISTS CONTEUDO (" +
                "id IDENTITY PRIMARY KEY, " +
                "titulo VARCHAR(150), " +
                "texto VARCHAR(10000), " +
                "autor VARCHAR(150))";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de conteúdos criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela de conteúdos: " + e.getMessage());
        }
    }

    private void adicionarUsuarioInicial() {
        Usuario admin = new Usuario("admin", "12345");
        criarUsuario(admin);
    }
}