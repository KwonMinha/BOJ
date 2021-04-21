/**
 * @author Minha Gwon
 * @date 2021. 4. 20.
 * 이차원 배열과 연산 
 * https://www.acmicpc.net/problem/17140
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int r = sc.nextInt()-1;
		int c = sc.nextInt()-1;
		int k = sc.nextInt();

		arr = new int[101][101];

		// 초기 행과 열의 개수 
		int colCnt = 3;
		int rowCnt = 3;

		for(int i = 0; i < colCnt; i++) {
			for(int j = 0; j < rowCnt; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		// 늘어난 행과 열의 가장 큰 인덱스 
		int maxCnt = 0;
		
		int time = 0;
		while(arr[r][c] != k && time < 101) {
			time++;
			
			if(colCnt >= rowCnt) { // R 연산 - 모든 행 정렬 
				maxCnt = rowCnt; // 열이 늘어날 수 있음 - 가장 크게 늘어난 열이 max

				for(int i = 0; i < colCnt; i++) {
					HashMap<Integer, Integer> hashMap = new HashMap<>();

					for(int j = 0; j < rowCnt; j++) {
						if(arr[i][j] != 0) { // 0인 경우 제외 
							hashMap.put(arr[i][j], hashMap.getOrDefault(arr[i][j], 0) + 1);
							arr[i][j] = 0; // map에 넣었다면 0으로 초기화 
						}
					}
					maxCnt = Math.max(maxCnt, sortHashMap(hashMap, 'R', i));
				}

				rowCnt = maxCnt;
			} else { // C 연산 - 모든 열 정렬 
				maxCnt = colCnt; // 행이 늘어날 수 있음 - 가장 크게 늘어난 행이 max

				for(int i = 0; i < rowCnt; i++) {
					HashMap<Integer, Integer> hashMap = new HashMap<>();

					for(int j = 0; j < colCnt; j++) {
						if(arr[j][i] != 0) {
							hashMap.put(arr[j][i], hashMap.getOrDefault(arr[j][i], 0) + 1);
							arr[j][i] = 0;
						}
					}
			
					maxCnt = Math.max(maxCnt, sortHashMap(hashMap, 'C', i));
				}

				colCnt = maxCnt;
			}
		}
		
		// print time 
		if(time < 101) System.out.println(time);
		else System.out.println(-1);
	}

	// 정렬된 결과 배열에 삽입 
	public static int insertMapToArray(LinkedList<Map.Entry<Integer, Integer>> list, char oper, int idx) {
		int cnt = 0;

		Iterator<Map.Entry<Integer, Integer>> iterator = list.iterator();
		while(iterator.hasNext()) {
			Map.Entry<Integer, Integer> entry = iterator.next();

			if(cnt == 100) { // 행 또는 열의 크기가 100을 넘어가는 경우, 처음 100개를 제외한 나머지는 버림 
				return 100;
			}

			if(oper == 'R') { // 행에 삽입 
				arr[idx][cnt++] = entry.getKey();
				arr[idx][cnt++] = entry.getValue();
			} else { // 열에 삽입 
				arr[cnt++][idx] = entry.getKey();
				arr[cnt++][idx] = entry.getValue();
			}

		}

		return cnt; // 행 또는 열의 마지막 방의 인덱스 반환 
	}

	// 수와 등장횟수를 기반으로 정렬 
	public static int sortHashMap(HashMap<Integer, Integer> map, char oper, int idx) {
		// value기준 오름차순 정렬하고, value가 같으면 key 기준 오름차순으로 정렬
		LinkedList<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				int comparision = o1.getValue() - o2.getValue();
				return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
			}
		});

		return insertMapToArray(list, oper, idx);

		/*
        // 순서유지를 위해 LinkedHashMap을 사용
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>(); 
        for(Iterator<Map.Entry<Integer, Integer>> iter = list.iterator(); iter.hasNext();){
            Map.Entry<Integer, Integer> entry = iter.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        System.out.println(sortedMap);
		 */
	}
	
}
