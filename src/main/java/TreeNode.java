import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    static HashMap<Integer, Integer> inorderMap;
    static int[] preorderArr;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    TreeNode(Integer[] levelOrder) {
        if (levelOrder.length > 0) {
            this.val = levelOrder[0];
            boolean hasLeftChild = levelOrder.length > 1;
            boolean hasRightChild = levelOrder.length > 2;
            if (hasLeftChild) {
                this.left = new TreeNode();
                buildByLevelOrder(this.left, levelOrder, 1);
            }
            if (hasRightChild) {
                this.right = new TreeNode();
                buildByLevelOrder(this.right, levelOrder, 2);
            }
        }
    }


    public static TreeNode buildTree(Integer[] levelOrder) {
        if (levelOrder.length == 0) return null;
        return buildByLevelOrder(new TreeNode(), levelOrder, 0);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode();
        if (preorder.length == 0) return root;
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        preorderArr = preorder;
        int[] preorderValidRange = {0, preorderArr.length - 1};
        int[] inorderValidRange = {0, inorder.length - 1};
        return buildByPreorderAndInorder(preorderValidRange, inorderValidRange, root);
    }

    public static TreeNode buildHeightBalanceBSTree(int[] nums){
        int[] validRange = {0, nums.length-1};
        return buildHeightBalanceBSTree(nums, validRange);
    }

    public static void printPreorder(TreeNode node) {
        List<Integer> treeExpress;
        treeExpress = new ArrayList<>();
        traversalPreorder(node, treeExpress);
        StringJoiner sj = new StringJoiner(",", "[", "]");

        for (Integer express : treeExpress) {
            sj.add(String.valueOf(express));
        }
        System.out.println(sj.toString());
    }


    public static void traversalPreorder(TreeNode node, List<Integer> stored) {
        if (node == null) {
//            stored.add(null);
            return;
        }
        stored.add(node.val);
        traversalPreorder(node.left, stored);
        traversalPreorder(node.right, stored);
    }

    private static TreeNode buildByLevelOrder(TreeNode currentNode, Integer[] levelOrder, int currentIndex) {
        currentNode.val = levelOrder[currentIndex];
        boolean hasLeftSubTree = (currentIndex * 2 + 1 < levelOrder.length) && levelOrder[currentIndex * 2 + 1] != null;
        boolean hasRightTree = (currentIndex * 2 + 2 < levelOrder.length) && levelOrder[currentIndex * 2 + 2] != null;

        if (hasLeftSubTree) {
            currentNode.left = new TreeNode();
            buildByLevelOrder(currentNode.left, levelOrder, currentIndex * 2 + 1);
        }
        if (hasRightTree) {
            currentNode.right = new TreeNode();
            buildByLevelOrder(currentNode.right, levelOrder, currentIndex * 2 + 2);
        }
        return currentNode;
    }

    private static TreeNode buildByPreorderAndInorder(int[] preorderValidRange, int[] inorderValidRange, TreeNode root) {
        int rootValue = preorderArr[preorderValidRange[0]];

        int rootAtInorderIndex = inorderMap.get(rootValue);

        int leftChildPreorderLength = rootAtInorderIndex - inorderValidRange[0];
        int RightChildPreorderLength = inorderValidRange[1] - rootAtInorderIndex;
        root.val = rootValue;
        if (leftChildPreorderLength != 0) {
            root.left = new TreeNode();
            int[] newPreValidRange = {preorderValidRange[0] + 1, preorderValidRange[0] + leftChildPreorderLength - 1};
            int[] newInValidRange = {inorderValidRange[0], rootAtInorderIndex - 1};
            buildByPreorderAndInorder(newPreValidRange, newInValidRange, root.left);
        }
        if (RightChildPreorderLength != 0) {
            root.right = new TreeNode();
            int[] newPreValidRange = {preorderValidRange[0] + leftChildPreorderLength + 1, preorderValidRange[1]};
            int[] newInValidRange = {rootAtInorderIndex + 1, inorderValidRange[1]};
            buildByPreorderAndInorder(newPreValidRange, newInValidRange, root.right);
        }
        return root;
    }

    private static TreeNode buildHeightBalanceBSTree(int[] nums, int[] validRange) {
        if (nums.length == 0) return null;
        int midIndex = (validRange[0] + validRange[1]) / 2;
        int midValue = nums[midIndex];
        boolean hasLeftChild = midIndex - validRange[0] > 0;
        boolean hasRightChild = validRange[1] - midIndex > 0;
        TreeNode node = new TreeNode(midValue);

        if (hasLeftChild) {
            int[] leftValidRange = {validRange[0], midIndex - 1};
            node.left = buildHeightBalanceBSTree(nums, leftValidRange);
        }

        if (hasRightChild) {
            int[] rightValidRange = {midIndex + 1, validRange[1]};
            node.right = buildHeightBalanceBSTree(nums, rightValidRange);
        }
        return node;
    }


}