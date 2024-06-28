package org.koreait;

import java.util.Scanner;

public class App {

    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }


    public void run() {
        int id = 0;

        System.out.println("== motivation execution ==");

        while (true) {
            System.out.print("command) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("== motivation 종료 ==");
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어 입력해");
                continue;
            }

            if (cmd.equals("list")) {
                System.out.println("== motivation list ==");
                System.out.println(" id   //    motivation    //   source ");

            }

            if (cmd.equals("add")) {
                System.out.print("motivation : ");
                String motivation = sc.nextLine();
                System.out.print("source : ");
                String source = sc.nextLine();
                System.out.println((++id) + "번 motivation이 등록 되었습니다");

                List list = new List();
                list.list(id, motivation, source);

            }
        }

    }
    static class call {
        int id;
        String motivation;
        String source;


    }

}

class List {
    int id;

     void list(int id, String motivation, String source) {

        System.out.println(id + "   //   " + motivation + "   //   " + source);



    }
}
