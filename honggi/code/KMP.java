public class KMP {

    // 패턴 사이에 겹치는 부분을 찾아내고,
    // 검색을 다시 시작할 위치를 구하여 패턴을 한번에 많이 옮기는 알고리즘
    public static void main(String[] args) {
        String text = "zabcabxaccabcabd";
        String pattern = "abcabd";
        System.out.println(kmpMatch(text, pattern));
    }

    public static int kmpMatch(String text, String pattern) {
        int[] skip = new int[pattern.length()+1];
        int pp = 0, pt = 1;

        while (pt < pattern.length()){
            if (pattern.charAt(pt) == pattern.charAt(pp)){
                skip[++pt] = ++pp;
            } else if (pp == 0)
                skip[++pt] = pp;
            else
                pp = skip[pp];
        }
        pp = pt = 0;

        while (pt < text.length() && pp < pattern.length()) {
            if (text.charAt(pt) == pattern.charAt(pp)){
                pt++;
                pp++;
            } else if (pp == 0){
                pt++;
            } else {
                pp = skip[pp];
            }
        }

        if (pp == pattern.length()) {
            return pt - pp;
        }
        return -1;
    }

}
