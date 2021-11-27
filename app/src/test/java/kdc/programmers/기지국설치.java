package kdc.programmers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/*
# Tips
1. Loop 개선
2. 적절한 데이터 구조 사용
3. 불필요한 Wrapper 타입 제거
 */
@DisplayName("기지국 설치 문제")
class 기지국설치 {

    private int n = 11;
    private int stations[] = new int[]{4, 11};
    private int w = 1;

    /*
    ## 풀이방법
    - 왼쪽부터 기지국을 설치한다.
    - 만약 기지국이 설치되어 있는 곳이 있다면 대역폭을 확인하고 다음으로 넘어간다.
    ## 문제점
    - 시간초과가 발생한다.
     */
    @DisplayName("solution1")
    @Test
    public void solution1(){
        int answer  = 0;

        Queue<Integer> sq = new LinkedList<>();
        for (int s : stations){
            sq.offer(s);
        }

        int position = 1;
        while(position <= n){
            if (!sq.isEmpty() && sq.peek() - w <= position){
                position = sq.poll() + w + 1;
            }else{
                answer += 1;
                position = position + w + w + 1;
            }
        }

        assertThat(answer).isEqualTo(3);
    }

    /*
    ## 개선점
    - Primitive 타입을 사용할 수 있는곳에서 Wrapper 타입를 사용하는 코드를 의심한다.
     */
    @DisplayName("solution2")
    @Test
    public void solution2(){
        int answer  = 0;
        int si = 0;
        int position = 1;
        int range = (w * 2) + 1;

        while(position <= n){
            if (si < stations.length && stations[si] - w <= position){
                position = stations[si] + w + 1;
                si += 1;
            }else{
                answer += 1;
                position += range;
            }
        }

        assertThat(answer).isEqualTo(3);
    }
}
