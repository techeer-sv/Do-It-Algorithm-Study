import java.util.*;

public class Topological_sorting {

    public static int v,e; //노드v, 간선e
    public static int[] indegree = new int[100001];
    //각 노드에 연결된 간선 정보를 담기 위한 연결 리스트 초기화
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();


    public static void topologySort(){
        ArrayList<Integer> result = new ArrayList<>(); // 결과 담을 리스트
        Queue<Integer> q = new LinkedList<>();

        // 처음 시작할 때 진입차수가 0인 노드를 큐에 삽입
        for(int i=1; i<=v; i++){
            if(indegree[i] ==0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int now = q.poll();
            result.add(now);
            for(int i=0; i<graph.get(now).size(); i++){
                indegree[graph.get(now).get(i)] -= 1;
                if(indegree[graph.get(now).get(i)] == 0){
                    q.offer(graph.get(now).get(i));
                }
            }
        }

        //위상정렬 출력
        for(int i=0; i<result.size(); i++){
            System.out.print(result.get(i) + " ");
        }
    }
    public static void main(String[] argc){
        Scanner scan = new Scanner(System.in);

        v = scan.nextInt();
        e = scan.nextInt();

        // 그래프 초기화
        for(int i=0; i<= v; i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int i=0; i<e; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            graph.get(a).add(b); //점정 a에서 b로 이동
            //진입차수 1증가
            indegree[b] +=1;
        }
        topologySort();
    }
}