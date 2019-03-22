
import java.util.*;


public class PlayerCompare {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Comparator<Player> checker = (Player a, Player b) -> {
            int comScore = 0;
            if (a.score>b.score){
                comScore -=1;
            } else if (a.score<b.score) {
                comScore +=1;
            }
            int comName = a.name.compareTo(b.name);
            if(comScore == 0){
                return comName;
            } else return comScore;
        };//new Checker();

        for(int i = 0; i < n; i++){
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }

    static class Player {
        String name;
        int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

//    static class Checker implements Comparator<Player> {
//        // complete this method
//
//        public int compare(Player a, Player b) {
//            int comScore = 0;
//            if (a.score>b.score){
//                comScore -=1;
//            } else if (a.score<b.score) {
//                comScore +=1;
//            }
//            int comName = a.name.compareTo(b.name);
//            if(comScore == 0){
//                return comName;
//            } else return comScore;
//        }
//    }
}