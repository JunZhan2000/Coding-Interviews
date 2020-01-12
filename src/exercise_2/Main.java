//题目描述
//请实现一个函数，将一个字符串中的每个空格替换成“%20”。
//例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

package exercise_2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        System.out.println(new Main().replaceSpace(new StringBuffer("I am GOAT.")));
        System.out.println(new Main().replaceSpace1(new StringBuffer("I am GOAT.")));
    }

    public String replaceSpace(StringBuffer str) {
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                str.replace(i, i+1, "%20");
            }
        }

        return str.toString();
    }

    public String replaceSpace1(StringBuffer str) {
        String replaceS = "%20";
        int count = 0, length = str.length();

        //遍历一遍字符串，获取所有空格的位置
        for(int i = 0; i < length; i++){
            if(str.charAt(i) == ' '){
                count++;
            }
        }

        str.setLength(length + count * 2);
        //从后向前遍历
        for(int i = length - 1; i >= 0; i--){
            if(str.charAt(i) == ' '){
                str.replace(i + count * 2 - 2, i + count * 2 + 1, replaceS);  //将空格替换成字符串
                count--;
            } else {
                str.setCharAt(i+count*2, str.charAt(i));
            }
        }

        return str.toString();
    }
}


