package db;

import java.util.Objects;

import entities.Usuario;



public class DadosInsert {
    private Integer id;
    private String titulo;
    private String texto;
    private Usuario autor; 
    
    public DadosInsert() {
    }

  
    public DadosInsert(Integer id, String titulo, String texto, Usuario autor) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    
    @Override
    public String toString() {
        return "Conteudo [id: " + id + ", titulo: " + Objects.toString(titulo, "N/A") +
               ", texto: " + Objects.toString(texto, "N/A") + ", autor: " + Objects.toString(autor, "N/A") + "]";
    }
}