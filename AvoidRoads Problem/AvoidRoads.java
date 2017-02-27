public class AvoidRoads {
	//2-D Array "dp" stores the number of paths from (0,0) to (i,j)
	//while satisfying all the given conditions
    private long[][] dp;

	/*
	 *This method checks if path from (x1,y1) to (x2,y2) is blocked or not
	 */
    private boolean isBlocked(int x1, int y1, int x2, int y2, String[] blocked) {
        String str = x1 + " " + y1 + " " + x2 + " " + y2;
        for (String s : blocked) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

	/*
	 *The algo works as dp[i][j] = dp[i-1][j]+dp[i][j-1] if paths from (i-1,j) to (i,j)
	 *and (i,j-1) to (i,j) is not blocked
	 */
    private long calculatePaths(int x, int y, String[] blocked, long[][] map) {
        if ((x == 1 && y == 0 && !isBlocked(x - 1, y, x, y, blocked)) ||
            (y == 1 && x == 0 && !isBlocked(x, y - 1, x, y, blocked))) {
            return 1;
        }
        int lowX = x - 1;
        int lowY = y - 1;
        long result = 0;
        if (lowX >= 0 && !isBlocked(lowX, y, x, y, blocked)
                && !isBlocked(x, y, lowX, y, blocked)) {
           result += dp[y][lowX];
        }
        if (lowY >= 0 && !isBlocked(x, lowY, x, y, blocked)
                && !isBlocked(x, y, x, lowY, blocked)) {
           result += dp[lowY][x];
        }
        return result;
    }

    public long numWays(int x, int y, String[] blocked) {
        dp = new long[110][110]; //large enough to include all cases
        dp[0][0] = 0;
        int levelX = 0;
        int levelY = 0;
        while (levelX <= x && levelY <= y) {
            for (int i = 0; i <= y && levelX <= x; i += 1) {
               dp[i][levelX] = calculatePaths(levelX, i, blocked, dp);
            }
            levelX += 1;
            for (int i = 0; i <= x && levelY <= y; i += 1) {
               dp[levelY][i] = calculatePaths(i, levelY, blocked, dp);
            }
            levelY += 1;
        }
        return dp[y][x];
    }

    public long[][] getdp() {
        return this.dp;
    }

	//Main method
    public static void main(String[] args) {
        AvoidRoads avoidRoads = new AvoidRoads();
        //put value of width here
        int x = 32;
        //put value of height here
        int y = 22;
        //put the blocked paths string here
        String[] blocked = {"0 0 1 0", "0 0 0 1"};
        System.out.println(avoidRoads.numWays(x,y, blocked));
    }
}