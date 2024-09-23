package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import db.DadosInsert;
import ui.ForceUI;


public class ListaConteudo implements ForceUI<DadosInsert> {

    private List<DadosInsert> dadosInserts = new ArrayList<>();
    private int count = 1 ;
    
	@Override
	public void inserirDados(DadosInsert dadosInsert) {
    	if(dadosInsert.getId() == null) {
    		dadosInsert.setId(count++);
    	}
    	dadosInserts.add(dadosInsert);		
	}

	@Override
	public void atualizar(DadosInsert entity) {
        for (DadosInsert dadosInsert : dadosInserts) {
            if (dadosInsert.getId() == entity.getId()) {
            	dadosInsert.setTitulo(entity.getTitulo());
            	dadosInsert.setTexto(entity.getTexto());
                break;
            }
        }		
	}

	@Override
	public List<DadosInsert> listar() {
        return Collections.unmodifiableList(dadosInserts);
	}

	@Override
	public boolean deleta(int id) {
        return dadosInserts.removeIf(conteudo -> conteudo.getId() == id);
	}
	

}