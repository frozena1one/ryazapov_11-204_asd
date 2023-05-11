import java.util.List;

public class SplayTree {
    private Node root;
    public long counter;
    public SplayTree(){
        counter = 0;
    }

    private class Node {
        int key;
        public Node left, right, parent;

        public Node(int key) {
            this.key = key;
            left = right = parent = null;
        }
    }
    private void zig(Node x) {
        Node p = x.parent;
        if (p != null) {
            Node g = p.parent;
            if (g != null) {
                if (g.left == p) {
                    g.left = x;
                } else {
                    g.right = x;
                }
            }
            x.parent = g;
            if (p.left == x) {
                p.left = x.right;
                if (p.left != null) {
                    p.left.parent = p;
                }
                x.right = p;
            } else {
                p.right = x.left;
                if (p.right != null) {
                    p.right.parent = p;
                }
                x.left = p;
            }
            p.parent = x;
        }
        root = x;
    }

    private void zigZig(Node x) {
        Node p = x.parent;
        Node g = p.parent;
        if (g != null) {
            Node gg = g.parent;
            if (gg != null) {
                if (gg.left == g) {
                    gg.left = x;
                } else {
                    gg.right = x;
                }
            }
            x.parent = gg;
            if (g.left == p) {
                g.left = p.right;
                if (g.left != null) {
                    g.left.parent = g;
                }
                p.right = g;
                p.left = x.right;
                if (p.left != null) {
                    p.left.parent = p;
                }
                x.right = p;
            } else {
                g.right = p.left;
                if (g.right != null) {
                    g.right.parent = g;
                }
                p.left = g;
                p.right = x.left;
                if (p.right != null) {
                    p.right.parent = p;
                }
                x.left = p;
            }
            p.parent = x;
            g.parent = p;
        }
        root = x;
    }

    private void zigZag(Node x) {
        Node p = x.parent;
        Node g = p.parent;
        if (g != null) {
            Node gg = g.parent;
            if (gg != null) {
                if (gg.left == g) {
                    gg.left = x;
                } else {
                    gg.right = x;
                }
            }
            x.parent = gg;
            if (g.left == x) {
                g.left = x.right;
                if (g.left != null) {
                    g.left.parent = g;
                }
                p.right = x.left;
                if (p.right != null) {
                    p.right.parent = p;
                }
                x.left = p;
                x.right = g;
            } else {
                g.right = x.left;
                if (g.right != null) {
                    g.right.parent = g;
                }
                p.left = x.right;
                if (p.left != null) {
                    p.left.parent = p;
                }
                x.right = p;
                x.left = g;
            }
            p.parent = x;
            g.parent = x;
        }
        root = x;
    }

    private Node splay(Node node) {
        while (node.parent != null) {
            Node p = node.parent;
            Node g = p.parent;
            if (g == null) {
                zig(node);
            } else if ((g.left == p && p.left == node) || (g.right == p && p.right == node)) {
                zigZig(node);
            } else {
                zigZag(node);
            }
        }
        return node;
    }

    private Node insert(Node node, int key) {
        counter++;
        if (node == null) {
            root = new Node(key);
            return root;
        } else if (node.key > key) {
            node.left = insert(node.left, key);
        } else if (node.key < key) {
            node.right = insert(node.right, key);
        }
        return splay(node);
    }

    public boolean add(int key) {
        root = insert(root, key);
        return true;
    }

    private Node search(Node node, int key) {
        counter++;
        if (node == null) return null;
        if (node.key == key) return node;
        else if (node.key > key) return search(node.left, key);
        else return search(node.right, key);
    }

    public Node find(int key) {
        return search(root, key);
    }
    private Node delete(Node node, int key) {
        counter++;
        if (node == null) {
            return null;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = minimum(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = splay(node);
        }
        return node;
    }

    public Node remove(int key) {
        return delete(root, key);
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    private Node maximum(Node node) {
        if (node.right == null) return node;
        return maximum(node.right);
    }

    public long getIterations() {
        long save = counter;
        counter = 0;
        return save;
    }
    public long[][] addElement(List<Integer> list){
        long[][] data = new long[10000][2];
        for (int i = 0; i < list.size(); i++) {
            long startTime = (long) System.nanoTime();
            add(list.get(i));
            long endTime = (long) System.nanoTime();
            data[i][0] = getIterations();
            data[i][1] = endTime - startTime;
        }
        return data;
    }

    public long[][] findElement(List<Integer> list) {
        long[][] data = new long[100][2];
        for (int i = 0; i < list.size(); i++) {
            long startTime = System.nanoTime();
            find(list.get(i));
            long endTime = System.nanoTime();
            data[i][0] = getIterations();
            data[i][1] = endTime - startTime;
        }
        return data;
    }

    public long[][] deleteElem(List<Integer> list) {
        long[][] data = new long[1000][2];
        for (int i = 0; i < list.size(); i++) {
            long startTime = System.nanoTime();
            remove(list.get(i));
            long endTime = System.nanoTime();
            data[i][0] = getIterations();
            data[i][1] = endTime - startTime;
        }
        return data;
    }
}
