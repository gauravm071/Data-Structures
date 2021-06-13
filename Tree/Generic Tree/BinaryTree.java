import java.util.*;
class BinaryTree{
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data= data;
            this.left=null;
            this.right= null;
        }
    }

    static class Pair{
        int state;
        Node node;
        Pair(Node node, int state){
            this.node=node;
            this.state= state;
        }
    }

    public static class BSTPair{
        boolean isBst;
        int max;
        int min;
        Node root;
        int size;
    }

    public  static BSTPair isBST(Node root){
        if(root==null){
            BSTPair bp= new BSTPair();
            bp.min= Integer.MAX_VALUE;
            bp.max= Integer.MIN_VALUE;
            bp.isBst= true;
            return bp;
        }
        
        BSTPair l= isBST(root.left);
        BSTPair r= isBST(root.right);
        BSTPair bp = new BSTPair();
        bp.isBst=l.isBst && r.isBst && (root.data>l.max && root.data<r.min);
        bp.min= Math.min(root.data, Math.min(l.min, r.min));
        bp.max= Math.max(root.data, Math.max(l.max, r.max));
        if(bp.isBst){
            bp.root=root;
            bp.size= l.size + r.size+1;
        }
        else if(l.size>r.size){
            bp.size=l.size;
            bp.root= l.root;
        }
        else{
            bp.size=r.size;
            bp.root=r.root;
        }
        return bp;
   }

    public static Node construct(Integer []ar){
        Stack<Pair> st= new Stack<>();
        Node root = new Node(ar[0]);
        st.add(new Pair(root,0));
        int i=1;
        while(st.size()>0){
            Pair p= st.peek();
            if(p.state==0){
                if(ar[i]!=null){
                    Node n= new Node(ar[i]);
                    p.node.left=n;
                    st.add(new Pair(n,0));
                }
                p.state++;
                i++;
            }
            else if(p.state==1){
                if(ar[i]!=null){
                    Node n= new Node(ar[i]);
                    p.node.right=n;
                    st.add(new Pair(n,0));
                }
                p.state++;
                i++;
            }
            else{
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node root){
        if(root== null) return;
        System.out.print(root.data+" -> ");
        if(root.left!=null){
            System.out.print(root.left.data+", ");
        }
        if(root.right!=null){
            System.out.print(root.right.data+" ");
        }
        System.out.println();
        if(root.left!=null){
            display(root.left);
        }
        if(root.right!=null){
            display(root.right);
        }
    }

    public static int sum(Node root){
       if(root.left==null && root.right==null){
           return root.data;
       }
       int s=root.data;
       if(root.left!=null){
           s+=sum(root.left);
       }
       if(root.right!=null){
           s+=sum(root.right);
       }
       return s;
    }

    public static int height(Node root){
        if(root.left==null && root.right==null) return 1;
        int lh=0;
        int rh=0;
        if(root.left!=null){
            lh+=height(root.left);
        }
        if(root.right!=null){
            rh+=height(root.right);
        }
        return Math.max(lh,rh)+1;
    }

    public static void levelOrderTraversal(Node root){
        Queue<Node> qu= new LinkedList<>();
        qu.add(root);
        while(!qu.isEmpty()){
            int size= qu.size();
            for(int i=0;i<size;i++){
                Node front=qu.peek();
                System.out.print(front.data+" ");
                if(front.left!=null){
                    qu.add(front.left);
                }
                if(front.right!=null){
                    qu.add(front.right);
                }
                qu.remove();
            }
            System.out.println();
        }
    }

    public static void IterativePrePostIn(Node root){
        Stack<Pair> st= new Stack<>();
        st.add(new Pair(root, 0));
        ArrayList<Integer> pre= new ArrayList<>();
        ArrayList<Integer> in= new ArrayList<>();
        ArrayList<Integer> post= new ArrayList<>();
        while(st.size()>0){
            Pair p= st.peek();
            if(p.state==0){
                pre.add(p.node.data);
                p.state++;
                if(p.node.left!=null){
                    st.add(new Pair(p.node.left, 0));
                }
            }
            else if(p.state==1){
                in.add(p.node.data);
                p.state++;
                if(p.node.right!=null){
                    st.add(new Pair(p.node.right, 0));
                }
            }
            else{
                post.add(p.node.data);
                st.pop();
            }
        }

        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);    
    }

    public static Node leftClonedBinaryTree(Node root){
        if(root==null) return null;
        Node lr= leftClonedBinaryTree(root.left);
        Node rr=leftClonedBinaryTree(root.right);
        Node nn= new Node(root.data);
        nn.left= lr;
        nn.right= null;
        root.left=nn;
        root.right= rr;
        return root;
    }
    public boolean isValidBST(Node root) {
        if(root==null){
            return true;
        }
       if(root.left==null && root.right==null) return true;
       Long lr= max(root.left);
       Long rr=min(root.right);
       boolean a1= isValidBST(root.left);
       boolean a2= isValidBST(root.right);
       if(lr<root.data && rr>root.data && a1 && a2) return true;
       return false;
   }
   
   public Long min(Node root){
       if(root==null) return Long.MAX_VALUE;
       Long l= min(root.left);
       Long r= min(root.right);
       return Math.min(l,Math.min(r,root.data));
   }
   public Long max(Node root){
       if(root==null) return Long.MIN_VALUE;
       Long l= max(root.left);
       Long r= max(root.right);
       return Math.max(l,Math.max(r,root.data));
   }

    public static void main(String[] args) {
        Integer []ar= {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root=construct(ar);
        // System.out.println(sum(root));
        // System.out.println(height(root));
        // levelOrderTraversal(root);
        leftClonedBinaryTree(root);
        IterativePrePostIn(root);
    }
}