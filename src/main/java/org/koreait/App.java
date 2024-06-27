package org.koreait;

import java.util.Scanner;

public class App {

    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }



    public void run() {
        System.out.println("== motivation 실행 ==");

        int count = 0;

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("== motivation 종료 ==");
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어 입력해");
                continue;
            }

            if (cmd.equals("add")) {
                System.out.print("motivation : ");
                String motivation = sc.nextLine();
                System.out.print("source : ");
                String source = sc.nextLine();
                System.out.println( (++count) + "번 motivation이 등록 되었습니다");
            }
        }


    }
}