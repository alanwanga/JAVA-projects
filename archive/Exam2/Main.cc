#include <iostream>
#include <vector>
#include <queue>
  
using namespace std;

const int MAX = 100000;

struct Node{
	int idx, pre, ctr;
	vector<int> children;
	bool isfes;
};
Node node[MAX + 1];

int bfs(int x){
	Node pre, now;
	queue<Node> que;
	que.push(node[x]);
  
    while(!que.empty()){  
        pre = que.front();
        que.pop();
        if(node[pre.idx].isfes){
			return pre.ctr;
		}
		pre.ctr++;
        for(vector<int>::iterator it = pre.children.begin(); it != pre.children.end(); it++){
            if(pre.pre == (*it)){
				continue;
            }
            now = node[(*it)];
			now.pre = pre.idx;
			now.ctr = pre.ctr;
            que.push(now);
        }
    }
}

int main(){
	int n, m, ai, bi, qi, ci;
	cin >> n >> m;
	for(int i = 0; i < n - 1; i++){
		cin >> ai >> bi;
		node[ai].idx = ai;
		node[bi].idx = bi;
		node[ai].pre = node[bi].pre = -1;
		node[ai].ctr = node[bi].ctr = 0;
		node[ai].children.push_back(bi);
		node[bi].children.push_back(ai);
		node[ai].isfes = node[bi].isfes = false;
	}
	node[1].isfes = true;
	for(int i = 0; i < m; i++){
		cin >> qi >> ci;
		if(qi == 1){
			node[ci].isfes = true;
		}
		else{
			cout << bfs(ci) << endl;
		}
	}
    return 0;  
} 
