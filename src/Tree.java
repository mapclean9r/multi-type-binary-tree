import java.nio.charset.StandardCharsets;

public class Tree {
    private Node node;
    private String stringTmp = "";

    public Tree(String nodeData){
        this.node = new Node(compressBin(convertToBin(nodeData)));
        this.node.setNode(nodeData);
    }

    public void print() {
        print(this.node, "", true);
    }

    public void add(Integer number){
        this.stringTmp = number.toString();
        String text = String.valueOf(number);
        String binConverted = convertToBin(text);
        int[] data = compressBin(binConverted);
        nodeCreate(data);
    }

    public void add(String text){
        this.stringTmp = text;
        String binConverted = convertToBin(text);
        int[] data = compressBin(binConverted);
        nodeCreate(data);
    }

    public Node getNode() {
        return node;
    }

    private void print(Node n, String prefix, boolean isTail) {
        if (n == null) return;
        System.out.println(prefix + (prefix.isEmpty() ? "" : (isTail ? "└── " : "├── ")) + n);
        Node left = n.getLeft();
        Node right = n.getRight();
        if (left != null || right != null) {
            String childPrefix = prefix + (isTail ? "    " : "│   ");
            if (left != null){
                print(left,  childPrefix, right == null);
            }
            if (right != null){
                print(right, childPrefix, true);
            }
        }
    }

    private void nodeCreate(int[] nodeData) {
        Node toInsert = new Node(nodeData);

        if (this.node == null) {
            this.node = toInsert;
            return;
        }

        Node parent = null;
        Node current = this.node;
        boolean wentLeft = false;

        while (current != null) {
            int cmp = compareCompressed(nodeData, current.getCompressedData());
            parent = current;

            if (cmp < 0) {
                // l
                wentLeft = true;
                current = current.getLeft();
            } else if (cmp > 0) {
                // r
                wentLeft = false;
                current = current.getRight();
            } else {
                // eq
                wentLeft = false;
                current = current.getRight();
            }
        }

        if (wentLeft){
            parent.setLeft(toInsert);
            parent.getLeft().setNode(stringTmp);
        }
        else{
            parent.setRight(toInsert);
            parent.getRight().setNode(stringTmp);
        }
    }

    private String convertToBin(String text){
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        StringBuilder bin = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String binary = String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0');
            if (i == bytes.length-1){
                bin.append(binary);
                continue;
            }
            bin.append(binary).append(" ");
        }
        return bin.toString();
    }

    private int[] compressBin(String bits){
        String[] bytes = bits.split(" ");
        int[] arrayData = {0, 0, 0, 0, 0, 0, 0, 0};

        for (String b : bytes) {
            for (int i = 0; i < arrayData.length; i++){
                arrayData[i] += (b.charAt(i) - '0');
            }
        }

        return arrayData;
    }

    private static int compareCompressed(int[] a, int[] b) {
        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) return Integer.compare(a[i], b[i]);
        }

        return Integer.compare(a.length, b.length);
    }
}
