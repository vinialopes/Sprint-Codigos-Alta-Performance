package modelo;

public class Cliente {

	private int id;
	private String nome;
	private int idade;
	private String cidade;
	private int nota;

	public Cliente(int id, String nome, int idade, String cidade, int nota) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.cidade = cidade;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public String getCidade() {
		return cidade;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | Nome: " + nome + " | Idade: " + idade
				+ " | Cidade: " + cidade + " | Nota de engajamento: " + nota;
	}
}
