package listas;

import modelo.Cliente;

/*
 * Lista linear encadeada simples, mantida sempre em ordem crescente de nota
 * de engajamento (mesma logica da classe ListaCrescenteInt, porem guardando
 * um Cliente em vez de um int e usando o ID do cliente como chave de busca
 * e remocao, em vez do proprio valor armazenado).
 */
public class ListaCrescenteCliente {

	private class No {
		Cliente dado;
		No prox;
	}

	private No lista = null;

	public boolean isEmpty() {
		return (lista == null);
	}

	// Insere o cliente respeitando a ordem crescente de nota de engajamento
	public void add(Cliente elem) {
		No novo = new No();
		novo.dado = elem;
		if (isEmpty()) {
			lista = novo;
			novo.prox = null;
		} else if (novo.dado.getNota() < lista.dado.getNota()) {
			novo.prox = lista;
			lista = novo;
		} else {
			No aux = lista;
			boolean achou = false;
			while (aux.prox != null && !achou) {
				if (aux.prox.dado.getNota() < novo.dado.getNota())
					aux = aux.prox;
				else
					achou = true;
			}
			novo.prox = aux.prox;
			aux.prox = novo;
		}
	}

	// Mostra todos os clientes da lista, na ordem em que estao (crescente de nota)
	public void show() {
		System.out.println("*********** Lista de clientes ************");
		if (isEmpty()) {
			System.out.println("Lista vazia.");
			return;
		}
		No aux = lista;
		while (aux != null) {
			System.out.println(aux.dado);
			aux = aux.prox;
		}
	}

	// Mostra apenas os N primeiros clientes da lista, ou seja, os de pior
	// nota de engajamento, ja que a lista fica em ordem crescente
	public void mostrarPiores(int quantidade) {
		System.out.println("*********** Piores notas de engajamento ************");
		if (quantidade <= 0) {
			System.out.println("Quantidade invalida.");
			return;
		}
		if (isEmpty()) {
			System.out.println("Lista vazia.");
			return;
		}
		No aux = lista;
		int contador = 0;
		while (aux != null && contador < quantidade) {
			System.out.println(aux.dado);
			aux = aux.prox;
			contador++;
		}
		if (contador < quantidade) {
			System.out.println("(a lista possui apenas " + contador + " cliente(s) no total)");
		}
	}

	// Retira o cliente com o ID informado e devolve o objeto removido
	// (retorna null se nao encontrar). Usado na atualizacao de nota, para
	// depois reinserir o mesmo cliente na posicao correta da lista.
	public Cliente remove(int id) {
		Cliente removido = null;
		if (!isEmpty()) {
			if (id == lista.dado.getId()) {
				removido = lista.dado;
				lista = lista.prox;
			} else {
				No aux = lista;
				boolean achou = false;
				while (aux.prox != null && !achou) {
					if (id == aux.prox.dado.getId())
						achou = true;
					else
						aux = aux.prox;
				}
				if (achou) {
					removido = aux.prox.dado;
					aux.prox = aux.prox.prox;
				}
			}
		}
		return removido;
	}
}
