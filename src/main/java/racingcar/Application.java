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

    public static void main(String[] args) {
        System.out.println("Input String: ");
        String input = Console.readLine();

        //자동차 생성 및 이름 리스트로 저장
        List<String> carNames = Car.cut(input);

        // 테스트 출력
        System.out.println(carNames);
    }
}
