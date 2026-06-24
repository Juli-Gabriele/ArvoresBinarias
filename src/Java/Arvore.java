public class Arvore{
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
    public void addLeft(Node parent,int element){
        Node newNode = new Node(element);
        parent.setLeft(newNode);
    }
    //Adicionar a direita
    public void addRigth(Node parent,int element){
        Node newNode = new Node(element);
        parent.setRigth(newNode);
    }

    public void add(int element){
        Node temp = root;
        while (true){
            if (temp.getElement() < element) {
                if (VerificarDireito(temp)) {
                    addRigth(temp, element);
                    break;
                }
                else{
                    temp = temp.getRigth();
                }
            } else{
                if (VerificarEsquerdo(temp)) {
                    addLeft(temp, element);
                    break;
                }
                else{
                    temp = temp.getLeft();
                }
            }
        }
        System.out.println("Elemento adicionado com sucesso");
    }

    //Remover a Direita
    public void removeRigth(Node parent){
        parent.setRigth(null);
    }
    //Remover a Esquerda
    public void removeLeft(Node parent){
        parent.setLeft(null);
    }

    public void remove(int element){
        if (element == root.getElement()){
            root = null;
            System.out.println("Elemento removido com sucesso {ROOT}");
            return;
        }
        Node temp = root;
        while (true){
            if (temp.getElement() < element) {
                if (!VerificarDireito(temp)) {
                    if (temp.getRigth().getElement() == element) {
                        removeRigth(temp);
                        System.out.println("Elemento removido com sucesso");
                        break;
                    }
                    else {
                        temp = temp.getRigth();
                    }
                }
                else{
                    System.out.println("Elemento não encontrado");
                    break;
                }

            } else{
                if (!VerificarEsquerdo(temp)) {
                    if (temp.getLeft().getElement() == element) {
                        removeLeft(temp);
                        System.out.println("Elemento removido com sucesso");
                        break;
                    }
                    else{
                        temp = temp.getLeft();
                    }
                } else{
                    System.out.println("Elemento não encontrado");
                    break;
                }

            }
        }
    }

    //Método de Busca de Nó
    public Node searchNo(int element){
        if (element == root.getElement()){
            return root;
        }
        Node temp = root;
        while (true){
            if (temp.getElement() < element) {
                if (!VerificarDireito(temp)) {
                    if (temp.getRigth().getElement() == element) {
                        return temp;
                    } else {
                        temp = temp.getRigth();
                    }
                }else{
                    System.out.println("Elemento não encontrado");
                    return null;
                }
            }
            else{
                if (!VerificarEsquerdo(temp)) {
                    if (temp.getLeft().getElement() == element) {
                        return temp.getLeft();
                    }
                    else{
                        temp = temp.getLeft();
                    }
                }else {
                    System.out.println("Elemento não encontrado");
                    return null;
                }
            }
        }
    }

    //Busca de elemento retorno booleano
    public boolean searchElement(int element){
        return searchNo(element) != null;
    }

    //Retorno de elemento
    public int returnElement(Node parent){
        return parent.getElement();
    }

    //Verifica se os dois braços são nulos
    public boolean verificarDescendentes(Node parent){
        return parent.getRigth() == null && parent.getLeft() == null;
    }

    //Verificar Apenas Direito
    public boolean VerificarDireito(Node parent){
        return parent.getRigth() == null;
    }

    //Verificar Apenas Esquerdo
    public  boolean VerificarEsquerdo(Node parent){
        return parent.getLeft() == null;
    }
}
