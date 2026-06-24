public class Arvore {
    private Node root;

    public Arvore(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //Adicionar a esquerda
    public void addLeft(Node parent, int element) {
        Node newNode = new Node(element);
        parent.setLeft(newNode);
    }

    //Adicionar a direita
    public void addRigth(Node parent, int element) {
        Node newNode = new Node(element);
        parent.setRigth(newNode);
    }

    public void add(int element) {
        Node temp = root;
        while (true) {
            if (temp.getElement() < element) {
                if (VerificarDireito(temp)) {
                    addRigth(temp, element);
                    break;
                } else {
                    temp = temp.getRigth();
                }
            } else {
                if (VerificarEsquerdo(temp)) {
                    addLeft(temp, element);
                    break;
                } else {
                    temp = temp.getLeft();
                }
            }
        }
        System.out.println("Elemento adicionado com sucesso");
    }

    //Remover a Direita
    public void removeRigth(Node parent) {
        parent.setRigth(null);
    }

    //Remover a Esquerda
    public void removeLeft(Node parent) {
        parent.setLeft(null);
    }

    public void remove(int element) {
        if (element == root.getElement()) {
            root = null;
            System.out.println("Elemento removido com sucesso {ROOT}");
            return;
        }
        Node temp = root;
        while (true) {
            if (temp.getElement() < element) {
                if (!VerificarDireito(temp)) {
                    if (temp.getRigth().getElement() == element) {
                        removeRigth(temp);
                        System.out.println("Elemento removido com sucesso");
                        break;
                    } else {
                        temp = temp.getRigth();
                    }
                } else {
                    System.out.println("Elemento não encontrado");
                    break;
                }

            } else {
                if (!VerificarEsquerdo(temp)) {
                    if (temp.getLeft().getElement() == element) {
                        removeLeft(temp);
                        System.out.println("Elemento removido com sucesso");
                        break;
                    } else {
                        temp = temp.getLeft();
                    }
                } else {
                    System.out.println("Elemento não encontrado");
                    break;
                }

            }
        }
    }

    //Método de Busca de Nó
    public Node searchNo(int element) {
        if (element == root.getElement()) {
            return root;
        }
        Node temp = root;
        while (true) {
            if (temp.getElement() < element) {
                if (!VerificarDireito(temp)) {
                    if (temp.getRigth().getElement() == element) {
                        return temp;
                    } else {
                        temp = temp.getRigth();
                    }
                } else {
                    System.out.println("Elemento não encontrado");
                    return null;
                }
            } else {
                if (!VerificarEsquerdo(temp)) {
                    if (temp.getLeft().getElement() == element) {
                        return temp.getLeft();
                    } else {
                        temp = temp.getLeft();
                    }
                } else {
                    System.out.println("Elemento não encontrado");
                    return null;
                }
            }
        }
    }

    //Busca de elemento retorno booleano
    public boolean searchElement(int element) {
        return searchNo(element) != null;
    }

    //Retorno de elemento
    public int returnElement(Node parent) {
        return parent.getElement();
    }

    //Verifica se os dois braços são nulos
    public boolean verificarDescendentes(Node parent) {
        return parent.getRigth() == null && parent.getLeft() == null;
    }

    //Verificar Apenas Direito
    public boolean VerificarDireito(Node parent) {
        return parent.getRigth() == null;
    }

    //Verificar Apenas Esquerdo
    public boolean VerificarEsquerdo(Node parent) {
        return parent.getLeft() == null;
    }


    //  Remover folha:
    public boolean removerFolha(Node parent, Node alvo) {

        if (alvo == null) {
            System.out.println("Nó não encontrado");
            return false;
        }

        if (alvo.getLeft() != null || alvo.getRigth() != null) {
            System.out.println("O nó não é folha");
            return false;
        }

        // Remoção da raiz
        if (alvo == root) {
            root = null;
            return true;
        }

        if (parent == null) {
            System.out.println("parent inválido");
            return false;
        }

        if (parent.getLeft() == alvo) {
            parent.setLeft(null);
        } else if (parent.getRigth() == alvo) {
            parent.setRigth(null);
        } else {
            System.out.println("parent não referencia o nó informado");
            return false;
        }

        return true;
    }

    // Remover no apenas um filho:
    public boolean removerUmFilho(Node parent, Node alvo) {

        if (alvo == null) {
            System.out.println("Nó não encontrado");
            return false;
        }

        int filhos = 0;

        if (alvo.getLeft() != null) filhos++;
        if (alvo.getRigth() != null) filhos++;

        if (filhos != 1) {
            System.out.println("O nó não possui exatamente um filho");
            return false;
        }

        Node filho;

        if (alvo.getLeft() != null) {
            filho = alvo.getLeft();
        } else {
            filho = alvo.getRigth();
        }

        // Remoção da raiz
        if (alvo == root) {
            root = filho;
            return true;
        }

        if (parent == null) {
            System.out.println("parent inválido");
            return false;
        }

        if (parent.getLeft() == alvo) {
            parent.setLeft(filho);
        } else if (parent.getRigth() == alvo) {
            parent.setRigth(filho);
        } else {
            System.out.println("parent não referencia o nó informado");
            return false;
        }

        return true;
    }

    // Remover no com dois filhos:
    public boolean removerDoisFilhos(Node alvo) {

        if (alvo == null) {
            System.out.println("Nó não encontrado");
            return false;
        }

        if (alvo.getLeft() == null || alvo.getRigth() == null) {
            System.out.println("O nó não possui dois filhos");
            return false;
        }

        Node sucessor = encontrarSucessor(alvo);

        if (sucessor == null) {
            System.out.println("Sucessor não encontrado");
            return false;
        }

        Node parentSucessor = encontrarparentSucessor(alvo);

        // Copia o valor do sucessor
        alvo.setElement(sucessor.getElement());

        // Remove o sucessor da posição antiga
        if (parentSucessor == alvo) {
            parentSucessor.setRigth(sucessor.getRigth());
        } else {
            parentSucessor.setLeft(sucessor.getRigth());
        }

        return true;
    }

    //  Método Auxiliar - Encontrar Sucessor
    public Node encontrarSucessor(Node no) {

        if (no == null || no.getRigth() == null) {
            return null;
        }

        Node atual = no.getRigth();

        while (atual.getLeft() != null) {
            atual = atual.getLeft();
        }

        return atual;
    }

    //  Método Auxiliar - Encontrar parent do Sucessor
    public Node encontrarparentSucessor(Node no) {

        if (no == null || no.getRigth() == null) {
            return null;
        }

        Node parent = no;
        Node atual = no.getRigth();

        while (atual.getLeft() != null) {
            parent = atual;
            atual = atual.getLeft();
        }

        return parent;
    }

    // Método Auxiliar - Contar Filhos
    public int quantidadeFilhos(Node no) {

        if (no == null) {
            return -1;
        }

        int qtd = 0;

        if (no.getLeft() != null) {
            qtd++;
        }

        if (no.getRigth() != null) {
            qtd++;
        }

        return qtd;
    }
    //  Método Geral de Remoção


    public boolean remover(Node parent, Node alvo) {

        if (alvo == null) {
            System.out.println("Elemento não encontrado");
            return false;
        }

        int filhos = quantidadeFilhos(alvo);

        switch (filhos) {

            case 0:
                return removerFolha(parent, alvo);

            case 1:
                return removerUmFilho(parent, alvo);

            case 2:
                return removerDoisFilhos(alvo);

            default:
                return false;
        }
    }
}