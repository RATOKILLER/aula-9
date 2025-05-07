import java.util.*;

class No {
    int valor;
    No esquerda, direita;

    public No(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

    public int obterValor() {
        return this.valor;
    }

    public No obterEsquerda() {
        return this.esquerda;
    }

    public No obterDireita() {
        return this.direita;
    }

    public void definirEsquerda(No no) {
        this.esquerda = no;
    }

    public void definirDireita(No no) {
        this.direita = no;
    }
}

class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        this.raiz = inserirNo(this.raiz, valor);
    }

    private No inserirNo(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }
        if (valor < no.obterValor()) {
            no.definirEsquerda(inserirNo(no.obterEsquerda(), valor));
        } else if (valor > no.obterValor()) {
            no.definirDireita(inserirNo(no.obterDireita(), valor));
        }
        return no;
    }

    public boolean pesquisar(int valor) {
        return pesquisarNo(this.raiz, valor);
    }

    private boolean pesquisarNo(No no, int valor) {
        if (no == null) return false;
        if (valor == no.obterValor()) return true;
        else if (valor < no.obterValor()) return pesquisarNo(no.obterEsquerda(), valor);
        else return pesquisarNo(no.obterDireita(), valor);
    }

    public void remover(int valor) {
        this.raiz = removerNo(this.raiz, valor);
    }

    private No removerNo(No no, int valor) {
        if (no == null) return no;
        if (valor < no.obterValor()) {
            no.definirEsquerda(removerNo(no.obterEsquerda(), valor));
        } else if (valor > no.obterValor()) {
            no.definirDireita(removerNo(no.obterDireita(), valor));
        } else {
            if (no.obterEsquerda() == null) return no.obterDireita();
            else if (no.obterDireita() == null) return no.obterEsquerda();

            No temp = minimoValorNo(no.obterDireita());
            no.valor = temp.obterValor();
            no.definirDireita(removerNo(no.obterDireita(), temp.obterValor()));
        }
        return no;
    }

    private No minimoValorNo(No no) {
        No atual = no;
        while (atual.obterEsquerda() != null) {
            atual = atual.obterEsquerda();
        }
        return atual;
    }

    public void imprimirPreOrdem() {
        imprimirPreOrdemNo(this.raiz);
        System.out.println();
    }

    private void imprimirPreOrdemNo(No no) {
        if (no != null) {
            System.out.print(no.obterValor() + " ");
            imprimirPreOrdemNo(no.obterEsquerda());
            imprimirPreOrdemNo(no.obterDireita());
        }
    }

    public void imprimirEmOrdem() {
        imprimirEmOrdemNo(this.raiz);
        System.out.println();
    }

    private void imprimirEmOrdemNo(No no) {
        if (no != null) {
            imprimirEmOrdemNo(no.obterEsquerda());
            System.out.print(no.obterValor() + " ");
            imprimirEmOrdemNo(no.obterDireita());
        }
    }

    public void imprimirPosOrdem() {
        imprimirPosOrdemNo(this.raiz);
        System.out.println();
    }

    private void imprimirPosOrdemNo(No no) {
        if (no != null) {
            imprimirPosOrdemNo(no.obterDireita());
            imprimirPosOrdemNo(no.obterEsquerda());
            System.out.print(no.obterValor() + " ");
        }
    }

    public void imprimirEmNivel() {
        if (raiz == null) return;
        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);
        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.print(atual.valor + " ");
            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }
        System.out.println();
    }
}

public class Numero {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        int[] numeros = {45, 12, 67, 23, 89, 3, 37, 15, 56, 78, 5, 90, 30, 33, 1, 8, 11, 19, 70, 99};

        for (int n : numeros) {
            arvore.inserir(n);
        }

        System.out.println("Pré-Ordem:");
        arvore.imprimirPreOrdem();

        System.out.println("Em-Ordem:");
        arvore.imprimirEmOrdem();

        System.out.println("Pós-Ordem:");
        arvore.imprimirPosOrdem();

        System.out.println("Em Nível:");
        arvore.imprimirEmNivel();

        int[] remover = {12, 89, 3, 33, 56};
        for (int r : remover) {
            arvore.remover(r);
        }

        System.out.println("\nApós remoções:");

        System.out.println("Pré-Ordem:");
        arvore.imprimirPreOrdem();

        System.out.println("Em-Ordem:");
        arvore.imprimirEmOrdem();

        System.out.println("Pós-Ordem:");
        arvore.imprimirPosOrdem();

        System.out.println("Em Nível:");
        arvore.imprimirEmNivel();
    }
}
