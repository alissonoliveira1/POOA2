package trabalho_POOA;
	import java.util.List;

	public interface ForceUI<A> {

		void inserirDados(A entity);

		void atualizar(A entity);

		List<A> listar();

		boolean deleta(int id);

		
	
}
