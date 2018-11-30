import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main{
	int MAX = 500;
	int direct_x[] = {0, 1, -1};
	int direct_y[] = {1, 0, 0};
	
	int map[][] = new int[MAX][MAX];
	
	int m, n;
	long ans = -1;
	
	class Point{
		int x, y;
		public Point(int x, int y){
	        this.x = x;
	        this.y = y;
	    }
	}
	
	class Path{  
		int x, y, score;
		ArrayList<Point> record;
		public Path(int x, int y, int score, ArrayList<Point> record){
	        this.x = x;
	        this.y = y;
	        this.score = score;
	        this.record = new ArrayList<Point>(record);
	    }
	};	
	
	/*void print(ArrayList<Point> record){
		for (int i = 0; i < record.size(); i++) {
			System.out.print(record.get(i).x + "," + record.get(i).y + " > "); 
        }
	}*/
	
	boolean visited(int x, int y, ArrayList<Point> record){
		for (int i = 0; i < record.size(); i++) {
            if(x == record.get(i).x && y == record.get(i).y){
            	return true;
            }
        }
		return false;
	}
	
	void bfs(int x, int y){
		Queue<Path> que = new LinkedList<Path>();
		ArrayList<Point> record = new ArrayList<Point>();
		Path pre = new Path(x, y, map[x][y], record);
		que.offer(pre);
		
		while(que.peek() != null){
			pre = new Path(que.peek().x, que.peek().y, que.peek().score, que.peek().record);
			//print(que.peek().record);
			//System.out.println(que.peek().x + "," + que.peek().y + ": " + que.peek().score);
			que.poll();
			for(int i = 0; i < 3; i++){
				int new_x = pre.x + direct_x[i];
				int new_y = pre.y + direct_y[i];
				if(new_y >= m){
					continue;
				}
				int pre_score = pre.score;
				if(new_x < 0){
					new_x = n - 1;
					pre_score = 0;
				}else if(new_x >= n){
					new_x = 0;
					pre_score = 0;
				}
				if(map[new_x][new_y] == -1 || visited(new_x, new_y, pre.record)){
					continue;
				}
				int new_score = pre_score + map[new_x][new_y];
				if(new_score > ans && new_y == m - 1){
					ans = new_score;
				}
				record = new ArrayList<Point>(pre.record);
				record.add(new Point(pre.x, pre.y));
				que.offer(new Path(new_x, new_y, new_score, record));
			} 
		}
	}
	
	void go(){
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				map[i][j] = in.nextInt();
			}
		}
		in.close();
		for(int i = 0; i < n; i++){
			if(map[i][0] != -1){
				bfs(i, 0);
			}
		}
		System.out.println(ans);
	}
	
	public static void main(String[] args) {
		Main game = new Main();
		game.go();
	}
}