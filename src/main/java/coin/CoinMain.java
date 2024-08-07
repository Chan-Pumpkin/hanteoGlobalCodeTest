package coin;

import java.io.*;

public class CoinMain {
    public static void main(String[] args) {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            //합계 입력
            bw.write("합계를 입력하세요 : ");
            bw.flush();
            int sum = Integer.parseInt(br.readLine());
            if (sum < 0) {
                throw new NumberFormatException("합계는 음수일 수 없습니다.");
            }

            //동전 입력
            bw.write("동전의 종류를 쉼표로 구분하여 입력하세요 : ");
            bw.flush();
            String[] coinsString = br.readLine().split(",");

            //동전 배열 생성
            int[] coins = new int[coinsString.length];
            for (int i = 0; i < coinsString.length; i++) {
                coins[i] = Integer.parseInt(coinsString[i].trim());

                if (coins[i] < 0) {
                    throw new NumberFormatException("동전의 종류는 음수일 수 없습니다.");
                }
            }

            //DP
            int[] dp = new int[sum + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int i = coin; i <= sum; i++) {
                    dp[i] += dp[i - coin];
                }
            }

            //출력
            String result = String.valueOf(dp[sum]);
            bw.write(result+"가지의 솔루션이 있습니다.");
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            System.err.println("입력 또는 출력 중 오류가 발생했습니다.");
            e.printStackTrace();
        }   catch (NumberFormatException e) {
            System.err.println("잘못된 입력 형식입니다. 숫자를 입력해 주세요.");
            e.printStackTrace();
        }
    }
}
