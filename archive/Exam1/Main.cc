#include <iostream>
#include <cstring>

#define MAX 500
#define maxof(x, y) ((x) > (y) ? (x) : (y))

#define ONLINE_JUDGE

using namespace std;

int map[MAX + 2][MAX + 1];
long long score[MAX + 2][MAX + 1];

#ifndef ONLINE_JUDGE
void print(int m, int n){
	for(int i = 1; i < n; i++){
		for(int j = 1; j < m; j++){
			cout << score[i][j] << " ";
		}
		cout << endl;
	}
	cout << endl;
}
#endif

int main(){
	int m, n, tmp;
	cin >> n >> m;
	n++;
	m++;
	memset(score, -1, sizeof(score));
	for(int i = 1; i < n; i++){
		for(int j = 1; j < m; j++){
			cin >> map[i][j];
		}
		score[i][0] = 0;
	}
	for(int j = 1; j < m; j++){
		tmp = -1;
		for(int i = 1; i < n; i++){
			if(map[i][j] == -1 || (tmp == -1 && score[i][j - 1] == -1)){
				tmp = -1;
				continue;
			}
			tmp = maxof(tmp, score[i][j - 1]) + map[i][j];
			score[i][j] = tmp;
		}
#ifndef ONLINE_JUDGE
		cout << 1 << endl;
		print(m, n);
#endif
		tmp = -1;
		for(int i = n - 1; i > 0; i--){
			if(map[i][j] == -1 || (tmp == -1 && score[i][j - 1] == -1)){
				tmp = -1;
				continue;
			}
			tmp = maxof(tmp, score[i][j - 1]) + map[i][j];
			score[i][j] = maxof(score[i][j], tmp);
		}
#ifndef ONLINE_JUDGE
		cout << 2 << endl;
		print(m, n);
#endif
		tmp = 0;
		for(int i = 1; i < n - 1; i++){
			if(map[i][n - 1] == -1 || map[i][j] == -1 || score[i + 1][j - 1] == -1){
				break;
			}
			tmp += map[i][j];
			score[i][j] = maxof(score[i][j], tmp);
		}
#ifndef ONLINE_JUDGE
		cout << 3 << endl;
		print(m, n);
#endif
		tmp = 0;
		for(int i = n - 1; i > 1; i--){
			if(map[i][1] == -1 || map[i][j] == -1 || score[i - 1][j - 1] == -1){
				break;
			}
			tmp += map[i][j];
			score[i][j] = maxof(score[i][j], tmp);
		}
#ifndef ONLINE_JUDGE
		cout << 4 << endl;
		print(m, n);
#endif
	}
	int ans = -1;
	for(int i = 1; i < n; i++){
		ans = maxof(ans, score[i][m - 1]);
	}
	cout << ans << endl;
    return 0;
}
