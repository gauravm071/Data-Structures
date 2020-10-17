/**************** Distance Between nodes in a Generic  Tree ****************/
#include <bits/stdc++.h>
using namespace std;
class Node
{
public:
    int data;
    vector<Node *> children;
};
vector<int> nodeToRootPath(Node *node, int ele)
{
    if (node->data == ele)
    {
        vector<int> arr;
        arr.push_back(node->data);
        return arr;
    }
    for (Node *child : node->children)
    {
        vector<int> path = nodeToRootPath(child, ele);
        if (path.size() > 0)
        {
            path.push_back(node->data);
            return path;
        }
    }
    return {};
}
int distanceBetweenNodes(Node *node, int data1, int data2)
{
    vector<int> path1 = nodeToRootPath(node, data1);
    vector<int> path2 = nodeToRootPath(node, data2);
    int i = path1.size() - 1;
    int j = path2.size() - 1;
    while (i >= 0 && j >= 0 && path1[i] == path2[j])
    {
        i--;
        j--;
    }
    i++, j++;
    return i + j;
}

int main()
{
#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif
    vector<int> arr{10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120,
                    -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
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
    cout << distanceBetweenNodes(root, 20, 40);
}

/*
Output:
2 
*/