/****************Daimeter of a Generic Tree ****************/
#include <bits/stdc++.h>
using namespace std;
class Node
{
public:
    int data;
    vector<Node *> children;
};
int diameter = 0;
int diameterOfGenericTree(Node *node)
{
    int dn = -1;  //deepest Node
    int sdn = -1; // second deepest Node
    for (Node *child : node->children)
    { // return child height
        int ch = diameterOfGenericTree(child);
        if (ch > dn)
        {
            sdn = dn;
            dn = ch;
        }
        else if (ch > sdn)
        {
            sdn = ch;
        }
    }
    if (dn + sdn + 2 > diameter)
    {
        diameter = dn + sdn + 2;
    }
    dn += 1;
    return dn;
}
Node *constructGenericTree(vector<int> arr)
{
    Node *root = NULL;
    stack<Node *> st;
    for (int i = 0; i < arr.size(); i++)
    {
        if (arr[i] == -1)
            st.pop();
        else
        {
            Node *temp = new Node;
            temp->data = arr[i];
            if (!st.empty())
            {
                st.top()->children.push_back(temp);
            }
            else
            {
                root = temp;
            }
            st.push(temp);
        }
    }
    return root;
}
int main()
{
#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif
    vector<int> arr{10, 20, -50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120,
                    -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};

    Node *root = constructGenericTree(arr);
    diameterOfGenericTree(root);
    cout << "Diameter of a Generic Tree is " << diameter;
    cout << height(root);
}

/*
Output:
Diameter of a Generic Tree is 5
*/
