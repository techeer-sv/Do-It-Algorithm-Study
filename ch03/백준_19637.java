package ch03;

import java.io.*;
import java.util.*;


public class 백준_19637 {
    static String [] arr = new String[100001];
    static int [] arr2 = new int[100001];

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine());
            arr[i] = st.nextToken();
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<m;i++)
        {
            long num = Long.parseLong(br.readLine());
            int l = 0;
            int r = n-1;
            while(l<=r)
            {
                int mid = (l+r) / 2;
                if(arr2[mid] < num)
                {
                    l= mid + 1;
                }
                else {
                    r = mid - 1;
                }
            }
            bw.write(arr[l]+"\n");
        }
        bw.flush();

    }

}