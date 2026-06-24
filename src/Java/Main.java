import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BEM-VINDO AO TESTE DE ÁRVORE BINÁRIA ===");
        System.out.print("Digite o valor do nó RAIZ para iniciar a árvore: ");
        int valorRaiz = scanner.nextInt();

        Node raizInicial = new Node(valorRaiz);
        Arvore arvore = new Arvore(raizInicial);
        Impresao impressora = new Impresao(arvore);

        // Chamar a impressão logo na criação da raiz para ver o ponto de partida
        System.out.println("\n--- ÁRVORE INICIALIZADA ---");
        impressora.imprimirArvore();

        int opcao = 0;
        while (opcao != 6) {
            System.out.println("\n========================================");
            System.out.println("1. Adicionar Elemento");
            System.out.println("2. Testar: Remover Nó FOLHA");
            System.out.println("3. Testar: Remover Nó com APENAS 1 FILHO");
            System.out.println("4. Testar: Remover Nó com 2 FILHOS");
            System.out.println("5. Exibir Resumo Completo (Estrutura/Métricas)");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("========================================\n");

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor a ser adicionado: ");
                    int valorAdd = scanner.nextInt();
                    arvore.add(valorAdd);

                    // --- IMPRESSÃO AUTOMÁTICA AQUI ---
                    System.out.println("\n--- ESTRUTURA ATUAL DA ÁRVORE ---");
                    impressora.imprimirArvore();
                    break;

                case 2:
                    System.out.print("Digite o valor do nó FOLHA que deseja remover: ");
                    int valorFolha = scanner.nextInt();
                    Node alvoFolha = arvore.searchNo(valorFolha);
                    Node paiFolha = encontrarPaiNaArvore(arvore.getRoot(), valorFolha);

                    boolean rem1 = arvore.removerFolha(paiFolha, alvoFolha);
                    System.out.println("Resultado da remoção de folha: " + (rem1 ? "Sucesso!" : "Falhou."));

                    // Também imprime após remover para você ver o resultado visual imediato
                    System.out.println("\n--- ESTRUTURA APÓS REMOÇÃO ---");
                    impressora.imprimirArvore();
                    break;

                case 3:
                    System.out.print("Digite o valor do nó (com 1 filho) que deseja remover: ");
                    int valorUmFilho = scanner.nextInt();
                    Node alvoUmFilho = arvore.searchNo(valorUmFilho);
                    Node paiUmFilho = encontrarPaiNaArvore(arvore.getRoot(), valorUmFilho);

                    boolean rem2 = arvore.removerUmFilho(paiUmFilho, alvoUmFilho);
                    System.out.println("Resultado da remoção de 1 filho: " + (rem2 ? "Sucesso!" : "Falhou."));

                    System.out.println("\n--- ESTRUTURA APÓS REMOÇÃO ---");
                    impressora.imprimirArvore();
                    break;

                case 4:
                    System.out.print("Digite o valor do nó (com 2 filhos) que deseja remover: ");
                    int valorDoisFilhos = scanner.nextInt();
                    Node alvoDoisFilhos = arvore.searchNo(valorDoisFilhos);

                    boolean rem3 = arvore.removerDoisFilhos(alvoDoisFilhos);
                    System.out.println("Resultado da remoção de 2 filhos: " + (rem3 ? "Sucesso!" : "Falhou."));

                    System.out.println("\n--- ESTRUTURA APÓS REMOÇÃO ---");
                    impressora.imprimirArvore();
                    break;

                case 5:
                    impressora.exibirResumoCompleto();
                    break;

                case 6:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private static Node encontrarPaiNaArvore(Node atual, int valorAlvo) {
        if (atual == null || atual.getElement() == valorAlvo) {
            return null;
        }
        if ((atual.getLeft() != null && atual.getLeft().getElement() == valorAlvo) ||
                (atual.getRigth() != null && atual.getRigth().getElement() == valorAlvo)) {
            return atual;
        }
        if (valorAlvo < atual.getElement()) {
            return encontrarPaiNaArvore(atual.getLeft(), valorAlvo);
        } else {
            return encontrarPaiNaArvore(atual.getRigth(), valorAlvo);
        }
    }
}