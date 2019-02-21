package com.util.tree;

public class OrderUtil {

    /**
     * 先序遍历
     *
     * @param node
     */
    public static void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.value);//先中间，直接输出
            preOrder(node.left);//再左边
            preOrder(node.right);//最后右边
        }
    }

    /**
     * 中序遍历
     * @param node
     */
    public static void middleOrder(Node node) {
        if (node != null) {
            middleOrder(node.left);//先左边
            System.out.println(node.value);//再中间输出
            middleOrder(node.right);//最后右边输出
        }
    }

    /**
     * 后续遍历
     * @param node
     */
    public static void lastOrder(Node node) {
        if (node != null) {
            lastOrder(node.left);//先左边
            lastOrder(node.right);//再右边
            System.out.println(node.value);//最后输出中间
        }
    }
}
