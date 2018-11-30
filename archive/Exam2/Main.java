import java.util.Scanner;

public class Main{
	//int MAX = 100001;
	int MAX = 1000;
	boolean isfesible[] = new boolean[MAX];
	
	int height[] = new int[MAX];
	int parent[] = new int[MAX];
	boolean visit[] = new boolean[MAX];
	boolean adj[][] = new boolean[MAX][MAX];
	int lca[][] = new int[MAX][MAX];
	int n, m;
	
	int find(int x){
	    return x == parent[x] ? x : (parent[x] = find(parent[x]));
	}
	
	void dfs(int x, int h){
	    visit[x] = true;
	    height[x] = h;
	    h++;
	    
	    for(int y = 1; y <= n; y++){
	    	if(visit[y]){
	    		lca[x][y] = lca[y][x] = find(y);
	    	}
	    }
	    for(int y = 1; y <= n; y++){
	        if(adj[x][y] && !visit[y]){
	            dfs(y, h);
	            parent[y] = x;
	        }
	    }
	}
	
	void go(){
		for(int i = 1; i < MAX; i++){
			for(int j = 1; j < MAX; j++){
				adj[i][j] = false;
				lca[i][j] = 0;
			}
			height[i] = 0;
			parent[i] = i;
			visit[i] = isfesible[i] = false;
		}
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i = 0; i < n - 1; i++){
			int ai = in.nextInt();
			int bi = in.nextInt();
			adj[ai][bi] = true;
			adj[bi][ai] = true;
		}
		isfesible[1] = true;
		dfs(1, 0);
		for(int i = 0; i < m; i++){
			int qi = in.nextInt();
			int ci = in.nextInt();
			if(qi == 1){
				isfesible[ci] = true;
				continue;
			}
			int tmp, ans = -1;
			for(int j = 1; j <= n; j++){
				if(isfesible[j]){
					tmp = height[j] - height[lca[j][ci]] + height[ci] - height[lca[j][ci]];
					if(ans == -1 || ans > tmp){
						ans = tmp;
					}
				}
			}
			System.out.println(ans);
		}
		in.close();
	}
	
    public static void main(String[] args) {
    	Main travel = new Main();
		travel.go();
    }
}