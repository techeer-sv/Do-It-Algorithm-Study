//https://maetdori.tistory.com/entry/알고리즘-Kruskal-Algorithm-Union-Find-크루스칼-알고리즘-유니온-파인드?category=857970
//https://maetdori.tistory.com/entry/프로그래머스-섬-연결하기

import java.util.*;

/**
 * 크루스칼 알고리즘: 음수 가중치가 없는 무방향 그래프에서 Minimum Spanning Tree를 찾는 알고리즘
 * MST를 찾을때는 사이클이 발생하면 안된다. -> 사이클이 발생하는 경우는 같은 그래프에 속한 두 노드를 연결했을 때 -> 내가 고른 두 노드가 같은 그래프에 속하는지 아닌지는 Union Find 개념을 이용
 * 
 * Union Find: 합집합을 찾는 알고리즘
 * 두 임의의 원소를 선택했을때  두원소가 같은 집하에 속하는지 판별하는 방법
 * 
 */

class Solution{
    static int[] parent; // union find 알고리즘을 사용하기 위해

    public int solution (int n, int[][] costs){
        Arrays.sort(costs,(int[] c1, int[] c2) -> c1[2]-c2[2]); // 크루스칼 알고리즘을 위해서 가중치로 오름차순 정렬
        
        //union find 알고리즘 
        parent = new int[n]; 

        for(int i = 0; i < n; i++){
            parent[i] = 1;
        }

        int total = 0;
        for(int[] edge : costs){
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            int fromParent = findParent(from);
            int toParent = findParent(to);

            //부모노드가 같으면(두 노드가 같은 그래프에 속하면)
            //해당 간선르 선택하지 않는다.

            if(fromParent == toParent) continue;

            total += cost;
            parent[toParent] = fromParent; //간성을 연결했을 떄 두 노드가 같은 그래프에 속하게 되었으므로 부모 노드를 갱신
        }
        return total;
    }    

    // 부모노드가 자기 자신과 같은 노드를 찾을 때 까지 재귀호출

    private int findParent(int node){
        if(parent[node] == node) return node;
        return parent[node] = findParent(parent[node]);
    }
}
