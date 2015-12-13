/**
 * Created by Aet on 13.12.2015.
 */
public class Result {
        private int score;
        private String name;

        public int getScore() {
            return score;
        }

        public Result(String name, int score) {
            this.score = score;
            this.name = name;
        }

        public String getNameString() {
            return name;
        }
}
