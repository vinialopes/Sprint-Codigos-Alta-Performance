package aplicacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import listas.ListaCrescenteCliente;
import modelo.Cliente;

public class EngajaUnimed {
	public static Scanner le = new Scanner(System.in);
	public static ListaCrescenteCliente lista = new ListaCrescenteCliente();

	public static void main(String[] args) {

		geraLista();

		int opcao;
		do {
			System.out.println("1 - Obter clientes com piores notas de engajamento presentes na lista");
			System.out.println("2 - Inserir novo cliente");
			System.out.println("3 - Atualizar nota de um cliente");
			System.out.println("0 - Encerrar atendimento");

			System.out.println("Opcao: ");
			opcao = le.nextInt();
			switch (opcao) {
			case 0:
				lista.show();
				break;
			case 1:
				obterPiores();
				break;
			case 2:
				inserirCliente();
				break;
			case 3:
				atualizarNota();
				break;
			default:
				System.out.println("Opcao Invalida");
			}

		} while (opcao != 0);

		le.close();

	}

	public static void geraLista() {
		String caminhoDoArquivo = "src/arquivos/clientesUnimed.csv";

		try {
			// Criar um objeto File com o caminho do arquivo
			File arquivo = new File(caminhoDoArquivo);

			// Criar um Scanner para ler o arquivo (UTF-8 preserva os acentos)
			Scanner leArq = new Scanner(arquivo, "UTF-8");

			// Loop para ler linha por linha até o final do arquivo
			while (leArq.hasNextLine()) {
				// Ler a próxima linha
				String linha = leArq.nextLine();
				String[] partes = linha.split(";");
				int id = Integer.parseInt(partes[0].trim());
				String nome = partes[1].trim();
				int idade = Integer.parseInt(partes[2].trim());
				String cidade = partes[3].trim();
				int nota = Integer.parseInt(partes[4].trim());

				// Instanciar objeto da classe Cliente e inserir na lista
				Cliente cliente = new Cliente(id, nome, idade, cidade, nota);
				lista.add(cliente);
			}
			// Fechar o objeto da classe Scanner leArq
			leArq.close();
		} catch (FileNotFoundException e) {
			// Caso o arquivo não seja encontrado
			System.out.println("Arquivo não encontrado: " + e.getMessage());
		}
	}

	// Opcao 1: le quantos clientes o gestor quer visualizar e mostra os
	// clientes com as piores notas (inicio da lista, que fica em ordem crescente)
	public static void obterPiores() {
		System.out.println("Quantos clientes com piores notas deseja visualizar? ");
		int quantidade = le.nextInt();
		lista.mostrarPiores(quantidade);
	}

	// Opcao 2: retira o cliente da lista pelo ID, le a nova nota e reinsere
	// o cliente na lista usando o criterio de ordenacao por nota de engajamento
	public static void atualizarNota() {
		System.out.println("Informe o ID do cliente que deseja atualizar: ");
		int id = le.nextInt();

		Cliente cliente = lista.remove(id);

		if (cliente == null) {
			System.out.println("Cliente com ID " + id + " nao encontrado na lista.");
		} else {
			System.out.println("Informe a nova nota de engajamento de " + cliente.getNome() + ": ");
			int novaNota = le.nextInt();
			cliente.setNota(novaNota);
			lista.add(cliente);
			System.out.println("Nota atualizada com sucesso.");
		}
	}

	// Opcao 3: le os dados de um novo cliente pelo teclado; a nota comeca em
	// -1 pois o cliente ainda nao pode ser reavaliado
	public static void inserirCliente() {
		le.nextLine(); // limpa o "\n" deixado pelo nextInt() da opcao do menu

		System.out.println("Informe o ID do cliente: ");
		int id = le.nextInt();
		le.nextLine();

		System.out.println("Informe o nome do cliente: ");
		String nome = le.nextLine();

		System.out.println("Informe a idade do cliente: ");
		int idade = le.nextInt();
		le.nextLine();

		System.out.println("Informe a cidade do cliente: ");
		String cidade = le.nextLine();

		int nota = -1;

		Cliente cliente = new Cliente(id, nome, idade, cidade, nota);
		lista.add(cliente);

		System.out.println("Cliente inserido com sucesso (nota de engajamento ainda nao avaliada).");
	}

}
