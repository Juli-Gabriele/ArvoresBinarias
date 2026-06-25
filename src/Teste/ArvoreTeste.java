import java.util.Scanner;

public class ArvoreTeste {
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
            System.out.println("2. Testar: Remover Nó");
            System.out.println("3. Exibir Resumo Completo (Estrutura/Métricas)");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("========================================\n");

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor a ser adicionado: ");
                    int valorAdd = scanner.nextInt();
                    if(!arvore.searchElement(valorAdd)) {

                        arvore.add(valorAdd);
                    }
                    else{
                        System.out.println(" ❌ Erro, não pode adicionar elemento repetido");
                    }
                    // --- IMPRESSÃO AUTOMÁTICA AQUI ---
                    System.out.println("\n--- ESTRUTURA ATUAL DA ÁRVORE ---");
                    impressora.imprimirArvore();
                    break;

                case 2:
                    System.out.print("Digite o valor do nó Para remover: ");
                    int valorFolha = scanner.nextInt();
                    Node alvoFolha = arvore.searchNo(valorFolha);
                    System.out.println(arvore.returnElement(alvoFolha) );
                    Node paiFolha = arvore.searchNoPai(valorFolha);


                    boolean rem1 = arvore.remover(paiFolha,alvoFolha);
                    System.out.println("Resultado da remoção de folha: " + (rem1 ? "Sucesso!" : "Falhou."));

                    // Também imprime após remover para você ver o resultado visual imediato
                    System.out.println("\n--- ESTRUTURA APÓS REMOÇÃO ---");
                    impressora.imprimirArvore();
                    break;



                case 3:
                    impressora.exibirResumoCompleto();
                    break;

                case 4:
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