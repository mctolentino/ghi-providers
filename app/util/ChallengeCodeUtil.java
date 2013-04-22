package util;

import java.util.Random;

/**
 * @author Dino Martin
 */
public abstract class ChallengeCodeUtil {
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static int CHALLENGE_CODE_LENGTH = 6;

    private ChallengeCodeUtil() {
        // non-instantiatable
    }

    public static String generateChallenge() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(CHALLENGE_CODE_LENGTH);
        for (int i = 0, j = 0; i < CHALLENGE_CODE_LENGTH; i++) {
            j = rnd.nextInt(AB.length());
            sb.append(AB.charAt(j));
        }
        return sb.toString();
    }
}
