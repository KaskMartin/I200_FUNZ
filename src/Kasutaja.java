import java.util.Comparator;
public class Kasutaja {

    //kasutaja andmete salvestamine
    public class ScoreComparator implements Comparator<Edetabel.Score> {
        public int compare(Edetabel.Score score1, Edetabel.Score score2) {

            int sc1 = score1.getScore();
            int sc2 = score2.getScore();

            if (sc1 > sc2){
                return -1;
            }else if (sc1 < sc2){
                return +1;
            }else{
                return 0;
            }
        }
    }
}
