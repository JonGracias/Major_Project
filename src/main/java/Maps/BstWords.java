package Maps;

import java.util.ArrayList;

public class BstWords {
    private Pair data;
    private BstWords left;
    private BstWords right;

    public BstWords() {
        this.data = null;
        this.left = null;
        this.right = null;
    }

    public BstWords(Pair data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public static BstWords createTree(ArrayList<Pair> content ) {
        BstWords bstree = new BstWords();
        if( content != null ) {

            bstree = new BstWords();
            for( int i = 0; i < content.size(); i++ ) {
                bstree.addNode(content.get(i));
            }
        }
        return bstree;
    }


    // As a convention, if the key to be inserted is less than the key of root
    // node, then key is inserted in
    // left sub-tree; If key is greater, it is inserted in right sub-tree. If it
    // is equal, as a convention, it
    // is inserted in right sub-tree
    public void addNode(Pair data) {
        if (this.data == null) {
            this.data = data;
        } else {
            if (this.data.getWord().compareTo(data.getWord()) < 0) {
                if (this.left != null) {
                    this.left.addNode(data);
                } else {
                    this.left = new BstWords(data);
                }

            } else {
                if (this.right != null) {
                    this.right.addNode(data);
                } else {
                    this.right = new BstWords(data);
                }

            }
        }
    }

    public void traversePreOrder() {
        System.out.println(this.data);
        if (this.left != null) {
            this.left.traversePreOrder();
        }
        if (this.right != null) {
            this.right.traversePreOrder();
        }
    }

    public void traverseInOrder() {
        if (this.left != null) {
            this.left.traverseInOrder();
        }
        System.out.println(this.data);
        if (this.right != null) {
            this.right.traverseInOrder();
        }
    }


    public void traversePostOrder() {
        if (this.left != null) {
            this.left.traversePostOrder();
        }
        if (this.right != null) {
            this.right.traversePostOrder();
        }
        System.out.println(this.data);
    }

}