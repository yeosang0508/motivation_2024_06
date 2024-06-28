package org.koreait;

import org.koreait.motivation.entity.Motivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== motivation execution ==");

        int lastId = 0; // 몇 번까지 썼더라?
        List<Motivation> motivations = new ArrayList<>(); // motivation 저장소

        while (true) {
            System.out.print("command) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("== motivation end ==");
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어 입력해");
                continue;
            }

            if (cmd.equals("add")) {
                int id = lastId + 1;
                System.out.print("body : ");
                String body = sc.nextLine();
                System.out.print("source : ");
                String source = sc.nextLine();

                Motivation motivation = new Motivation(id, body, source);

                motivations.add(motivation);

                System.out.printf("%d번 motivation이 등록 되었습니다\n", id);
                lastId++; // 마지막 번호 증가
            } else if (cmd.equals("list")) {
                if (motivations.size() == 0) {
                    System.out.println("등록된 motivation 없음");
                    continue;
                }
                System.out.println("== motivation list ==");
                System.out.printf("  id   //   source   //   body  \n");
                System.out.println("=".repeat(35));

                for (int i = motivations.size() - 1; i >= 0; i--) {
                    Motivation motivation = motivations.get(i);

                    if (motivation.getSource().length() > 7) {
                        System.out.printf("   %d  //    %s    //    %s  \n", motivation.getId(), motivation.getSource().substring(0, 5) + "...", motivation.getBody());
                        continue;
                    }

                    System.out.printf("   %d  //    %s     //    %s  \n", motivation.getId(), motivation.getSource(), motivation.getBody());
                }
            }
        }
    }
}