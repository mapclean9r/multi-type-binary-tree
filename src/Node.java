public class Node {
    private String node;
    private Node left;
    private Node right;
    private final int[] compressedData;

    public Node(int[] nodeData){
        this.left = null;
        this.right = null;

        this.compressedData = nodeData;
    }

    public int[] getCompressedData() {
        return compressedData;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) { this.left = left; }

    public void setRight(Node right) { this.right = right; }

    @Override
    public String toString() {
        //Arrays.toString(compressedData) -> compress testing
        return "[Node] -> "+ this.node +  "]";
    }
}
