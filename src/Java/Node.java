public class Node {
    private int  element;
    private Node right;
    private  Node left;

    public Node(int element) {
        this.element = element;
        this.right = null;
        this.left = null;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }
    public Node getRigth() {
        return right;
    }
    public void setRigth(Node rigth) {
        this.right = rigth;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
}
