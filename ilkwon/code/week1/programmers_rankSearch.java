// 문제 읽고 이해하는 데 맨분왔습니다.
// 몇시간을 삽질하다가 강의보고 이해하는 방향으로 학습했습니다...
// 푸신분들 존경합니다.


import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        // info기반 해쉬맵 만들기
        HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();

        for (String i : info) {
            String[] data = i.split(" ");
            String[] languages = {data[0], "-"};
            String[] jobs = {data[1], "-"};
            String[] exps = {data[2], "-"};
            String[] foods = {data[3], "-"};
            Integer value = Integer.parseInt(data[4]);
            for (String lang : languages)
                for (String job : jobs)
                    for (String exp : exps)
                        for (String food : foods) {
                            String[] keyData = {lang, job, exp, food}; // keydata 만들어주기
                            String key = String.join(" ", keyData); // 띄어쓰기로 구분
                            ArrayList<Integer> arr = hashMap.getOrDefault(key, new ArrayList<Integer>()); // 디폴트로 빈 배열 생성
                            arr.add(value); // value(점수) 삽입
                            hashMap.put(key, arr);
                        }
        }
        // 오름차순 정렬 : 해시맵 완성
        for (ArrayList<Integer> scoreList : hashMap.values()) {
            scoreList.sort(null);
        }
        // query 조건에 맞는 지원자 뽑아내기
        int[] answer = new int[query.length];
        int i = 0;

        for (String q : query) {
            String[] data = q.split(" and "); //and로 구분된 query를 split
            int target = Integer.parseInt(data[3].split(" ")[1]);
            data[3] = data[3].split(" ")[0];// 0에서 가져온 것을 3에 담음...
            String key = String.join(" ", data);

            //containskey메소드 사용 (binary search)
            if (hashMap.containsKey(key)) {
                ArrayList<Integer> list = hashMap.get(key);

                int left = 0;
                int right = list.size();
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) >= target)
                        right = mid;
                    else
                        left = mid + 1;
                }

                answer[i] = list.size() - left;
            }
            i++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        System.out.println(solution.solution(info, query));
    }
}