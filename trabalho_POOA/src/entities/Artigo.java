package entities;

public class Artigo {
    private String titulo;
    private String conteudo;
    private Integer id;
    private Usuario autor;

    public Artigo(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + "\nConteúdo: " + conteudo;
    }
}