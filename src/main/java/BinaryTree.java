import java.util.ArrayList;

public class BinaryTree {
  Node root;
  ArrayList<String> treeAsText;

  public void insertValue(int value) {
    if(root != null)
      insertValue(value, root);
    else
      root = new Node(value);
  }

  private void insertValue(int value, Node node) {
    if(value > node.getValue()) {
      if(node.getRight() == null)
        node.setRight(new Node(value));
      else
        insertValue(value, node.getRight());
    } else if(value < node.getValue()) {
      if(node.getLeft() == null)
        node.setLeft(new Node(value));
      else
        insertValue(value, node.getLeft());
    } else {
      System.out.println("Duplicate value: " + value);
    }

  }

  public void printTree() {
    if(root != null)
      printTree(root);
    else
      System.out.println("Tree is empty!");
  }

  public void print2DTree() {
    print2DTree(root, 0, 4);
  }
  private void print2DTree(Node node, int spaces, int spread) {
    if(node == null)
      return;

    spaces += spread;

    // print right child
    print2DTree(node.getRight(), spaces, spread);

    for( int i = spread; i < spaces; i++) {
      System.out.print(" ");
    }
    // print the value
    System.out.print(node.getValue() + "\n");

    // print left
    print2DTree(node.getLeft(), spaces, spread);

  }

  public ArrayList<String> get2DTree() {
    treeAsText = new ArrayList<String>();
    get2DTree(root, 0, 4);

    ArrayList<String> tmpArrayList = treeAsText;
    treeAsText = null;
    return tmpArrayList;
  }
  private void get2DTree(Node node, int spaces, int spread) {
    spaces += spread;

    // get right child
    if(node.getRight() != null)
      get2DTree(node.getRight(), spaces, spread);

    StringBuilder spaceStr = new StringBuilder();
    for( int i = spread; i < spaces; i++) {
      spaceStr.append(" ");
    }
    // add to tree text representation
    treeAsText.add(spaceStr.toString() + node.getValue() + "\n");

    // get left
    if(node.getLeft() != null)
      get2DTree(node.getLeft(), spaces, spread);

  }


  private void printTree(Node node) {
    if(node.getLeft() != null)
      printTree(node.getLeft());
    System.out.println(node.getValue());
    if(node.getRight() != null)
      printTree(node.getRight());
  }


}


class Node {
  private int value;
  private Node left;
  private Node right;

  public Node(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public Node getLeft() {
    return left;
  }

  public Node getRight() {
    return right;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public void setRight(Node right) {
    this.right = right;
  }
}