import java.util.LinkedList;
import java.util.Queue;

public class Impresao {

    // Mantemos uma referência para a árvore que queremos imprimir
    private Arvore arvore;

    // Construtor que recebe a árvore da Main
    public Impresao(Arvore arvore) {
        this.arvore = arvore;
    }

    public void emOrdem() {
        Node root = arvore.getRoot(); // Busca a raiz atualizada da árvore
        if (root == null) {
            System.out.println("Árvore vazia");
            return;
        }
        StringBuilder sb = new StringBuilder();
        emOrdem(root, sb);
        System.out.println(sb.toString().trim());
    }

    private void emOrdem(Node node, StringBuilder sb) {
        if (node == null) return;
        emOrdem(node.getLeft(), sb);
        sb.append(node.getElement()).append(" ");
        emOrdem(node.getRigth(), sb);
    }

    public void preOrdem() {
        Node root = arvore.getRoot(); // Busca a raiz atualizada
        if (root == null) {
            System.out.println("Árvore vazia");
            return;
        }
        StringBuilder sb = new StringBuilder();
        preOrdem(root, sb);
        System.out.println(sb.toString().trim());
    }

    private void preOrdem(Node node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.getElement()).append(" ");
        preOrdem(node.getLeft(), sb);
        preOrdem(node.getRigth(), sb);
    }

    public void posOrdem() {
        Node root = arvore.getRoot(); // Busca a raiz atualizada
        if (root == null) {
            System.out.println("Árvore vazia");
            return;
        }
        StringBuilder sb = new StringBuilder();
        posOrdem(root, sb);
        System.out.println(sb.toString().trim());
    }

    private void posOrdem(Node node, StringBuilder sb) {
        if (node == null) return;
        posOrdem(node.getLeft(), sb);
        posOrdem(node.getRigth(), sb);
        sb.append(node.getElement()).append(" ");
    }

    public void imprimirArvore() {
        Node root = arvore.getRoot(); // Busca a raiz atualizada
        if (root == null) {
            System.out.println("Árvore vazia");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.getElement()).append("\n");
        imprimirArvore(root, sb, "");
        System.out.print(sb);
    }

    private void imprimirArvore(Node node, StringBuilder sb, String prefixoFilhos) {
        if (node == null) return;

        boolean temEsquerda = node.getLeft() != null;
        boolean temDireita = node.getRigth() != null;

        if (temEsquerda) {
            boolean eUltimoFilho = !temDireita;
            String conector = eUltimoFilho ? "└── " : "├── ";
            sb.append(prefixoFilhos).append(conector).append(node.getLeft().getElement()).append("\n");
            String proximoPrefixo = prefixoFilhos + (eUltimoFilho ? "    " : "│   ");
            imprimirArvore(node.getLeft(), sb, proximoPrefixo);
        }

        if (temDireita) {
            String conector = "└── ";
            sb.append(prefixoFilhos).append(conector).append(node.getRigth().getElement()).append("\n");
            String proximoPrefixo = prefixoFilhos + "    ";
            imprimirArvore(node.getRigth(), sb, proximoPrefixo);
        }
    }

    public void imprimirPorNiveis() {
        Node root = arvore.getRoot(); // Busca a raiz atualizada
        if (root == null) {
            System.out.println("Árvore vazia");
            return;
        }

        Queue<Node> fila = new LinkedList<>();
        fila.add(root);
        int nivel = 0;

        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();
            StringBuilder sb = new StringBuilder("Nível " + nivel + ": ");

            for (int i = 0; i < tamanhoNivel; i++) {
                Node atual = fila.poll();
                sb.append(atual.getElement()).append(" ");

                if (atual.getLeft() != null) fila.add(atual.getLeft());
                if (atual.getRigth() != null) fila.add(atual.getRigth());
            }

            System.out.println(sb.toString().trim());
            nivel++;
        }
    }

    public int altura() {
        return altura(arvore.getRoot());
    }

    private int altura(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(altura(node.getLeft()), altura(node.getRigth()));
    }

    public int contarNos() {
        return contarNos(arvore.getRoot());
    }

    private int contarNos(Node node) {
        if (node == null) return 0;
        return 1 + contarNos(node.getLeft()) + contarNos(node.getRigth());
    }

    public int contarFolhas() {
        return contarFolhas(arvore.getRoot());
    }

    private int contarFolhas(Node node) {
        if (node == null) return 0;
        if (node.getLeft() == null && node.getRigth() == null) return 1;
        return contarFolhas(node.getLeft()) + contarFolhas(node.getRigth());
    }

    public void exibirResumoCompleto() {
        System.out.println("===== ESTRUTURA DA ÁRVORE =====");
        imprimirArvore();
        System.out.println();
        System.out.println("===== POR NÍVEIS =====");
        imprimirPorNiveis();
        System.out.println();
        System.out.println("===== PERCURSOS =====");
        System.out.print("Em ordem:  ");
        emOrdem();
        System.out.print("Pré-ordem: ");
        preOrdem();
        System.out.print("Pós-ordem: ");
        posOrdem();
        System.out.println();
        System.out.println("===== MÉTRICAS =====");
        System.out.println("Altura: " + altura());
        System.out.println("Total de nós: " + contarNos());
        System.out.println("Total de folhas: " + contarFolhas());
    }
}