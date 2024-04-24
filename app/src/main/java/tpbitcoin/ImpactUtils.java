package tpbitcoin;

import java.math.BigInteger;

public class ImpactUtils {

    /**
     * computes the expected time (in seconds) for mining a block
     * @param hashrate: miner hashing capacity (hash/s)
     * @param difficultyAsInteger: block difficulty, as BigInteger (256 bits integer)
     * @return expected time in seconds before finding a correct block
     */
    // TODO
    public static long expectedMiningTime(long hashrate, BigInteger difficultyAsInteger){
        BigInteger hashrateBI = BigInteger.valueOf(hashrate);
        BigInteger seconds = difficultyAsInteger.divide(hashrateBI);
        return seconds.longValue();
    }

    public static double expectedYearsToMineABlock(long hashrate, BigInteger difficultyAsInteger) {
        long seconds = expectedMiningTime(hashrate, difficultyAsInteger);
        return seconds / (60.0 * 60 * 24 * 365.25);
    }

    /**
     * Compute the total hashrate of the network given current difficulty level
     * @param difficultyAsInteger: difficulty level as 256bits integer
     * @return hashrate of the network in GH/s
     */
    public static double globalHashRate(BigInteger difficultyAsInteger) {
        // La constante représente la difficulté pour un hashrate de 1 H/s sur la période de réajustement de 2016 blocs
        BigInteger constant = new BigInteger("2").pow(32).multiply(BigInteger.valueOf(2016)).multiply(BigInteger.valueOf(600));
        BigInteger hashrate = constant.divide(difficultyAsInteger);

        // Convertir le hashrate en hashes par seconde, puis en GH/s
        return hashrate.doubleValue() / 1e9;
    }



    /**
     * Compute the total energy consumption of the network
     * assuming each miner has the same hashrate, and consume the same power
     * @param minerHashrate: the hashrate of each miner, in GH/s
     * @param minerPower: the power consumption of each miner, in Watts
     * @param networkHashrate : the global hashrate of the network
     * @param duration : in second
     * @return energy consumed during duration, in kWh
     */
    public static double globalEnergyConsumption(long minerHashrate, long minerPower, long networkHashrate, long duration){
        double numberOfMiners = (double) networkHashrate / minerHashrate;
        double totalPowerWattSeconds = numberOfMiners * minerPower * duration;
        return totalPowerWattSeconds / (1000 * 3600); // Convertir en kWh
    }

    /*** Calcule le hashrate global du réseau Bitcoin en GH/s à partir de la difficulté actuelle.
     * @param difficulty La difficulté actuelle du réseau, en tant que BigInteger.
     * @return Le hashrate global du réseau en GH/s.
     */
    public static double calculateNetworkHashrate(BigInteger difficulty) {
        BigInteger hashrate = difficulty.multiply(BigInteger.valueOf(2).pow(32)).divide(BigInteger.valueOf(600));
        return hashrate.doubleValue() / 1e9; // Convertit le hashrate en GH/s.
    }

    /**
     * Calcule l'énergie consommée par le réseau Bitcoin sur 24 heures.
     * @param c La capacité de hashage d'un équipement en TH/s.
     * @param p La puissance consommée par l'équipement en Watts.
     * @return L'énergie consommée par le réseau en kWh sur les dernières 24 heures.
     */
    public static double calculateEnergyConsumed(double c, double p) {
        // Convertir la capacité de hashage en GH/s depuis TH/s
        double capacityInGHs = c * 1000;

        // Calculer le hashrate total du réseau en GH/s en supposant que tous les équipements
        // ont la même capacité et consommation d'énergie spécifiées

        // Calculer la consommation d'énergie totale en Watts
        double totalPowerInWatts = capacityInGHs * (p / capacityInGHs);

        // Convertir la consommation d'énergie en kWh pour 24 heures

        return (totalPowerInWatts * 24) / 1000;
    }
}
