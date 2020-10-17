import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;
class rough{
    static class Node{
         int data;
         ArrayList<Node>children= new ArrayList<>();
     }
     static Node root;
     public static void construct(int[]ar){
         Stack<Node>st= new Stack<>();
         for(int i=0;i<ar.length;i++){
             if(ar[i]==-1){
                 st.pop();
             }else{

                Node node=new Node();
                node.data=ar[i];
                if(st.size()>0){
                    st.peek().children.add(node);
                }
                else{
                    root=node;
                }
                st.push(node);
             }
         }
     }
     public static void display(Node node){
         String str=node.data+"-> ";
         for(Node child:node.children){
             str+=child.data+", ";
         }
         str+=".";
         System.out.println(str);
         for(Node child:node.children){
             display(child);
         }
     }
     public static int height(Node node){
         int count=1;
         for(Node n:node.children){
             count+=height(n);
         }
         return count;
     }
     static int max=-1;
     public static int maximum(Node node){
          max=Math.max(node.data,max);
         for(Node n:node.children){
             maximum(n);
         }
         return max;
     }
     public static void prepost(Node node){
         System.out.println("NodePre "+node.data);
         for(Node n:node.children){
             prepost(n);
         }
         System.out.println("NodePost "+node.data);
     }
     public static void LVT(Node node){
        Queue<Node>q=new LinkedList<>();
        q.add(node);
        Node a=new Node();
        a.data=-1;
        q.add(a);
        while(q.size()>0){
            Node d=q.peek();
            if(d.data==-1){
                System.out.println();
                q.remove();
            }
            else{
                q.remove();
                System.out.print(d.data+" ");
                boolean flag=true;
                for(Node n:d.children){
                    q.add(n);
                    flag=false;
                }
                Node x=new Node();
                x.data=-1;
                if(!flag)q.add(x);
           }
        }

     }
     public static void LVTZZ(Node node){
         Stack<Node>ms= new Stack<>();
         ms.push(node);
         Stack<Node>cs= new Stack<>(); 
         int level=1;
         while(ms.size()>0){
             node=ms.pop();
             System.out.print(node.data+" ");
             if(level%2==1){
                for(int i=0;i<node.children.size();i++){
                    Node cn=node.children.get(i);
                    cs.push(cn);
                }
             }
             else{
                for(int i=node.children.size()-1;i>=0;i--){
                    Node cn=node.children.get(i);
                    cs.push(cn);
                }
             }
             if(ms.size()==0){
                 ms=cs;
                 cs=new Stack<>();
                 System.out.println();
                 level++;
             }
         }
     }
     public static void mirror (Node node){

        for(Node n:node.children){
            mirror(n);
        }
        Collections.reverse(node.children);
     }
     public static void deletenode(Node node){
         for(int i=node.children.size()-1;i>=0;i--){
             Node c=node.children.get(i);
             if(c.children.size()==0){
                 node.children.remove(c);
             }
         }


         for(Node n:node.children){
             deletenode(n);
         }
     }
     public static void Linearize(Node node){
         for (Node child:node.children){
             Linearize(child);
         }
         while(node.children.size()>1){
             Node lc=node.children.remove(node.children.size()-1);
             Node sl=node.children.get(node.children.size()-1);
             Node slt=getTail(sl);
             slt.children.add(lc);
         }
     }
     public static Node getTail(Node node){
         while(node.children.size()==1){
             node =node.children.get(0);
         }
         return node;
     }
     public static Node Linearize2(Node node){
         if(node.children.size()==0){
             return node;
         }
         Node lkt= Linearize2(node.children.get(node.children.size()-1));
         while(node.children.size()>1){
             Node last=node.children.remove(node.children.size()-1);
             Node sl= node.children.get(node.children.size()-1);
             Node slkt=Linearize2(sl);
             slkt.children.add(last);
         }
         return lkt;
     }
     public static boolean findElement(Node node, int ele){
         if(node.data==ele){
             return true;
         }
         for(Node child:node.children){
             boolean ans=findElement(child,ele);
             if(ans){
                 return true;
             }
         }
         return false;
     }
     public static ArrayList<Integer> findPath(Node node,int data){
        if(node.data==data){
            ArrayList<Integer> ar= new ArrayList<>();
            ar.add(node.data);
            return ar;
        }
        for(Node child:node.children){
            ArrayList<Integer>ptc = findPath(child,data);
            if(ptc.size()>0){
                ptc.add(node.data);
                return ptc;
            }
        }
        return new ArrayList<>();
     }
     public static int lca(Node node, int d1, int d2){
        ArrayList<Integer> ar1= findPath(node,d1);
        ArrayList<Integer> ar2 =findPath(node,d2);
        int i=ar1.size()-1;
        int j=ar2.size()-1;
        while(i>=0 && j>=0 && ar1.get(i)==ar2.get(j)){
            i--;
            j--;
        }
        i++;
        j++;
        return ar1.get(i);
     }
     public static int distancebetweenNodes(Node node,int d1, int d2){
        ArrayList<Integer> ar1= findPath(node,d1);
        ArrayList<Integer> ar2 =findPath(node,d2);
        int i=ar1.size()-1;
        int j=ar2.size()-1;
        while(i>=0 && j>=0 && ar1.get(i)==ar2.get(j)){
            i--;
            j--;
        }
        i++;
        j++;
        return i+j;
     }
     static int size=0;
     static int min=Integer.MAX_VALUE;
     static int Max= Integer.MIN_VALUE;
     static int height=0;
     public static void multitraversal(Node node, int depth){
        height=Math.max(height,depth);
        min=Math.min(min,node.data);
        Max=Math.max(max,node.data);
        size++;
        for(Node child:node.children){
            multitraversal(child,depth+1);
        }
     }
     static int predecessor;
     static int successor;
     static int s=0;
     public static void PS(Node node, int data){    // PS-> Predecessor and successor
       if(s==0){
           if(node.data==data){
               s=1;
           }
           else{
               predecessor=node.data;
           }
       }
       else if(s==1){
           successor=node.data;
           s=2;
       }
       for(Node child:node.children){
           PS(child,data);
       }
     }
     static int ciel=Integer.MAX_VALUE;
     static int floor=Integer.MIN_VALUE;
     static int x=0;
     static int y=0;
     public static void CielFloor(Node node, int data){

        if(x==0){
            if(node.data>data){
                x=1;
                ciel=node.data;
            }
        }
        else{
            if((ciel>node. data) && (node.data>data)){
                ciel=node.data;
            }
        }
        if(y==0){
            if(node.data<data){
                y=1;
                floor=node.data;
            }
        }
        else{
            if((floor<node. data) && (node.data<data)){
                floor=node.data;
            }
        }
        for(Node child: node.children){
            CielFloor(child,data);
        }
     }
     public static int kthlargest(Node node, int k){
        floor=Integer.MIN_VALUE;
        int factor=Integer.MAX_VALUE;
        for(int i=0;i<k;i++){
            CielFloor(node,factor);
            factor=floor;
            floor=Integer.MIN_VALUE;
        }
        return factor;
     }
     static int maxsmn=Integer.MIN_VALUE;
     static int mscn=0;
     public static int MaxSumNode(Node node){
        int sum=0;
        for(Node child:node.children){
            sum+=MaxSumNode(child);
        }
        sum+=node.data;
        if(maxsmn<sum){
            maxsmn=sum;
            mscn=node.data;
        }
        return sum;
     }
     static int dia=0;
     public static int MaxDia(Node node){
        int dch=-1;
        int sdch=-1;
        for(Node child: node.children){
            int h=MaxDia(child);
            if(h>dch){
                sdch=dch;
                dch=h;
            }
            else if(h>sdch){
                sdch=h;
            }
        }
        if(dch+ sdch+2>dia){
            dia= dch + sdch + 2;
        }
        dch+=1;
        return dch;
     }
     public static void main(String[]args) {
        int []ar={10,20,50,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        construct(ar);
        // display(root);
        // System.out.println("\n-------\n");
        // System.out.println(maximum(root));
        // prepost(root);
          LVT(root);
          LVTZZ(root);
        // mirror(root);
        // deletenode(root);
        // Linearize(root);
        // Node ans=Linearize2(root);
        // System.out.println(findElement(root,400));
        // ArrayList<Integer>ans= findPath(root,60);
        for(int i:ans){
            System.out.print(i+" ");
        }
        // System.out.println(lca(root,60,50));
        // System.out.print(distancebetweenNodes(root,60,50));
        // multitraversal(root,0);
        // System.out.println("Size-> "+size);
        // System.out.println("Height-> "+height);
        // System.out.println("Min-> "+min);
        // System.out.println("Max-> "+Max);
        // PS(root,80);
        // System.out.println(predecessor);
        // System.out.println(successor);
        // CielFloor(root,10);
        // System.out.println("Ciel-> "+ ciel);
        // System.out.println("Floor-> "+ floor);
        // System.out.println(kthlargest(root,10));
        // System.out.println(MaxSumNode(root));
        // System.out.println(mscn);
        int ans= MaxDia(root);
        System.out.println(dia);
        // display(root);
     }
}
