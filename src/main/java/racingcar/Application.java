package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static class Car{
        //1. 자동차 이름을 검수하고 쉼표를 기준으로 나누는 함수
        public static List<String> cut(String input){
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("error");
            }

            List<String> carList = new ArrayList<>(Arrays.asList(input.split(",")));

            for (String carName : carList) {
                carName = carName.trim(); // 공백 제거
                if (carName.isEmpty()) {
                    throw new IllegalArgumentException("error");
                }
                if (carName.length() > 5) {
                    throw new IllegalArgumentException("error");
                }
            }
            return carList;
        }
    }

    public static class Play {
        // 자동차 이동 여부 결정
        public static boolean isMovable() {
            return Randoms.pickNumberInRange(0, 9) >= 4;
        }

        // 이동 거리 출력
        public static String getDistanceString(int distance) {
            return "-".repeat(distance);
        }

        // 자동차 이동 및 출력
        public static void print(List<String> carList, int[] carDistances) {
            for (int i = 0; i < carList.size(); i++) {
                if (isMovable()) {
                    carDistances[i]++;
                }

                System.out.println(carList.get(i) + " : " + getDistanceString(carDistances[i]));
            }
            System.out.println();
        }
    }

    public static void printWinners(List<String> carNames, int[] carDistances) {
        int maxDistance = Arrays.stream(carDistances).max().orElse(0);
        List<String> winners = new ArrayList<>();

        for (int i = 0; i < carNames.size(); i++) {
            if (carDistances[i] == maxDistance) {
                winners.add(carNames.get(i));
            }
        }

        // 우승자 출력
        System.out.println("final winner : " + String.join(", ", winners));
    }


    public static void main(String[] args) {
        System.out.println("Input String: ");
        String input = Console.readLine();

        //자동차 생성 및 이름 리스트로 저장 및 배열 크기 구하기
        List<String> carNames = Car.cut(input);
        int carNamesSize = carNames.size();

        //시도할 횟수 받기
        System.out.println("Input number of times: ");
        int tryNumber = Integer.parseInt(Console.readLine());
        if(tryNumber<1){ //횟수가 0이거나 음수일 수 없음
            throw new IllegalArgumentException("error");
        }

        //자동차가 움직이는 횟수 저장하는 int형 배열 생성
        int[] carDistances = new int[carNamesSize];

        //경주하기
        for (int i = 0; i < tryNumber; i++) {
            Play.print(carNames, carDistances);
        }

        //우승자 출력
        printWinners(carNames, carDistances);
    }
}
