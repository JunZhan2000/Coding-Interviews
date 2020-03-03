//题目描述
//

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(1);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(1);
        answer.add(arrayList1);
        answer.add(arrayList2);

        for(ArrayList<Integer> list : answer){
            list.add(0, 9);
        }

        System.out.println(answer);
    }
}