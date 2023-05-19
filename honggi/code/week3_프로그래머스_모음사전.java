class 프로그래머스_모음사전 {
    /*
        재귀 함수를 사용한 풀이
        A부터 UUUUU까지의 알파벳을 만들면서, 주어진 단어 word와
        같은 알파벳을 찾을 때 까지 반복
    */
    
    char[] alpa = new char[]{'A','E','I','O','U'};
    int wordL;
    int[] dict;
    public int solution(String word) {
        wordL = word.length();
        dict = new int[]{-1,-1,-1,-1,-1};
        int count = 0;
                
        count = countAlpabet(count, word);
        return count;
    }
    
    public int countAlpabet(int count, String word){
        String s = dictToString().substring(0, wordL);
        if (word.equals(s)){
            return count;
        }
        else{
            nextDictionary();
            return countAlpabet(count+1, word);
        }
    }
    
    public void nextDictionary(){
        for (int i = 0; i<alpa.length; i++)
            if (dict[i]<0){
                dict[i]++;
                break;
            } else if (i == alpa.length-1){
                if (dict[4] == 4){
                    dict[4] = -1;
                    if (dict[3] == 4){
                        dict[3] = -1;
                        if (dict[2] == 4){
                            dict[2] = -1;
                            if (dict[1] == 4){
                                dict[0]++;
                                dict[1] = -1;
                            } else{
                                dict[1]++;
                            }
                        } else
                            dict[2]++;
                    } else
                        dict[3]++;
                } else
                    dict[4]++;
            }
    }
    
    public String dictToString(){
        String s = "";
        for (int i = 0; i< dict.length; i++){
            if (dict[i] != -1){
                s += alpa[dict[i]];
            }else
                s += " ";
        }
        return s;
    }
    
}