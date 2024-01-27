import datastructures.symboltable.AVLBinarySearchTree;

public class BinaryTreeMaximumPathSum {

    private static int maxPathSum = 0;
    private static int getMaxPathSum(AVLBinarySearchTree<Integer, String>.Node root) {
        if (root == null) {
            return 0;
        }
        int leftMax = getMaxPathSum(root.left);
        int rightMax = getMaxPathSum(root.right);

        leftMax = Math.max(leftMax, 0);
        rightMax = Math.max(rightMax, 0);

        // Compute maxPath Sum at the node level. Return only sum in one direction (max(left, right))
        maxPathSum = Math.max(maxPathSum, root.key + leftMax + rightMax);

        return root.key + Math.max(leftMax, rightMax);
    }


    public static void main(String[] args) {
        AVLBinarySearchTree<Integer, String> bst = new AVLBinarySearchTree<>();
        bst.put(-10, "-Ten");
        bst.put(9, "Nine");
        bst.put(20, "Twenty");
        bst.put(15, "Fifteen");
        bst.put(7, "Seven");
//        System.out.println(bst.printTreeValues());
        System.out.println(bst);
        BinaryTreeMaximumPathSum.getMaxPathSum(bst.root);
        System.out.println(BinaryTreeMaximumPathSum.maxPathSum);
    }
}
