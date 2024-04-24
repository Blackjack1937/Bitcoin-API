package tpbitcoin;

import org.bitcoinj.core.Sha256Hash;

import java.security.MessageDigest;
import java.util.*;
public class HashRateEstimator {
    private final int duration; // Duration of each experiment, in milliseconds
    private final int numberOfTries; // Number of experiments to run

    /**
     * Create a new object for estimating the number of SHA256 hashes the host can perform per second.
     *
     * @param duration      Duration of each experiment in milliseconds.
     * @param numberOfTries Number of experiments to run.
     */
    public HashRateEstimator(int duration, int numberOfTries) {
        this.duration = duration;
        this.numberOfTries = numberOfTries;
    }

    /**
     * Estimate the hashrate (hashes per second).
     *
     * @return The estimated hashrate.
     */
    public double estimate() {
        long numberOfHashes = 0;
        MessageDigest md = Sha256Hash.newDigest();
        byte[] bytes = new byte[32]; // Utilise 32 octets (256 bits) comme input pour SHA-256

        // Initialise des données aléatoires à hasher pour la simulation
        new Random().nextBytes(bytes);

        for (int t = 0; t < numberOfTries; ++t) {
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) < duration) {
                // Mise à jour des données à hasher à chaque itération pour simuler une variation
                bytes = md.digest(bytes);
                numberOfHashes++;
                md.reset(); // Remise à zéro pour la prochaine itération
            }
        }
        double totalDurationInSeconds = (double) duration * numberOfTries / 1000;
        return numberOfHashes / totalDurationInSeconds; // Retour le hashrate moyen par seconde
    }

}
