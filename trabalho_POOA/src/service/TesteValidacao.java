package service;

import java.util.ArrayList;
import java.util.List;

import entities.Usuario;
import entities.Validacao;

public class TesteValidacao {

    public static void main(String[] args) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("user1", "senha1"));
        
        Validacao validacao = new Validacao(listaUsuarios);
        
        Usuario usuario = validacao.validarLogin("user1", "senha1");
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario.getUser());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
}