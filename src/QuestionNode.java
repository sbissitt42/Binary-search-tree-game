public class QuestionNode {
    private String data;
    private QuestionNode left;
    private QuestionNode right;

    // Constructor for an answer node (leaf)
    public QuestionNode(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Constructor for a question node (branch)
    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    // Getters and setters for data, left, and right nodes
    public String getData() {
        return data;
    }

    public QuestionNode getLeft() {
        return left;
    }

    public QuestionNode getRight() {
        return right;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLeft(QuestionNode left) {
        this.left = left;
    }

    public void setRight(QuestionNode right) {
        this.right = right;
    }

    // Method to check if the node is a leaf (answer node)
    public boolean isAnswer() {
        return left == null && right == null;
    }
}
