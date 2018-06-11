package br.com.loov.mycash.model;
import java.util.List;

public class Meta {

	private Integer id;
	private Double valorLimite;
	private List<Categoria> categorias;

	public Meta() {
		
	}
	
	public Meta(Double valorLimite, Categoria categoria) {
		this.valorLimite = valorLimite;
		this.categorias = adicionarCategoria(categoria);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValorLimite() {
		return valorLimite;
	}

	public void setValorLimite(Double valorLimite) {
		this.valorLimite = valorLimite;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	private List<Categoria> adicionarCategoria(Categoria categoria) {
		this.categorias.add(categoria);
		return this.categorias;
	}
}
