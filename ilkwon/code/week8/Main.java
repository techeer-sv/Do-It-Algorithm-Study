import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> hashMap = new HashMap<>();
        int len = clothes.length;

        for (int i = 0; i < len; i++) {
            String type = clothes[i][1];
            hashMap.put(type, hashMap.getOrDefault(type, 0) + 1);
        }

        for (int cnt : hashMap.values()) {
            System.out.println(cnt);
            answer *= cnt + 1;
        }

        return answer - 1;
    }
}