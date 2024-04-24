package tpbitcoin;

import org.bitcoinj.core.*;
import org.bitcoinj.script.ScriptBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Miner {
    private static int  txCounter;
    private final NetworkParameters params;
    public static final long EASY_DIFFICULTY_TARGET = Utils.encodeCompactBits(Utils.decodeCompactBits(Block.EASIEST_DIFFICULTY_TARGET).divide(new BigInteger("1024")));
    public static final long SOMEWHAT_HARDER_DIFFICULTY_TARGET = Utils.encodeCompactBits(Utils.decodeCompactBits(Block.EASIEST_DIFFICULTY_TARGET).divide(new BigInteger("65536")));

    public Miner(NetworkParameters params){
        this.params = params;
    }

    /**
     * find a valid a nonce, e.g such that hash of block header is smaller than the block's target
     *
     * @param block: the block on which to set the nonce
     */
    // TODO
    private static void setValidNonce(Block block) throws NoSuchAlgorithmException {
        long nonce = 0;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        while (true) {
            block.setNonce(nonce++);
            byte[] header = block.cloneAsHeader().bitcoinSerialize();
            byte[] hash = digest.digest(header);
            BigInteger hashInt = new BigInteger(1, hash);
            if (hashInt.compareTo(Utils.decodeCompactBits(block.getDifficultyTarget())) < 0) {
                return;
            }
        }
    }



    /* borrowed from bitcoinj.core, not the real thing, for testing only
    * needed for creating a fake coinbase that pass bitcoinj basic verification
    */
    private  static  Transaction generateCoinbase(NetworkParameters params, byte[] pubKey){
        Transaction coinbase = new Transaction(params);
        final ScriptBuilder inputBuilder = new ScriptBuilder();
        inputBuilder.data(new byte[]{(byte) txCounter, (byte) (txCounter++ >>8)});
        coinbase.addInput(new TransactionInput(params, coinbase,
                inputBuilder.build().getProgram()));
        coinbase.addOutput(new TransactionOutput(params, coinbase, Coin.parseCoin("6.25"),
                ScriptBuilder.createP2PKOutputScript(ECKey.fromPublicOnly(pubKey)).getProgram()));
        return coinbase;
    }

    /**
     * Create a new block, predecessor of lastBlock. Difficulty of the new bloc kis set to EASY_DIFFICULTY
     * @param lastBlock: the last block of the blockchain
     * @param txs: a list of transactions (may be empty)
     * @param pubKey: the public key of the miner
     */
    // TODO
    public Block mine(Block lastBlock, List<Transaction> txs, byte[] pubKey) throws NoSuchAlgorithmException {
        // Clonez l'entête du dernier bloc pour créer un nouveau bloc
        Block newBlock = lastBlock.cloneAsHeader();

        // Ajoutez une transaction coinbase au nouveau bloc
        Transaction coinbaseTx = generateCoinbase(this.params, pubKey); // Assurez-vous que le montant est correct et formaté correctement
        newBlock.addTransaction(coinbaseTx);

        // Ajoutez les transactions fournies à la liste des transactions du nouveau bloc
        for(Transaction tx : txs) {
            newBlock.addTransaction(tx);
        }

        // Définissez la difficulté du nouveau bloc (pour les besoins de la simulation)
        newBlock.setDifficultyTarget(EASY_DIFFICULTY_TARGET);

        // Trouvez un nonce valide pour le nouveau bloc
        setValidNonce(newBlock);

        return newBlock; // Retournez le nouveau bloc miné
    }

}





