package hello;

import hello.UserData;
import cn.bubi.baas.base.BlockchainAddress;
import cn.bubi.baas.base.security.SecureIdentity;
import cn.bubi.baas.data.DataService;
import cn.bubi.baas.sdk.*;

public class BlockchainService {
    private static final String CHANNEL_ADDRESS = "";
    private static final String CHANNEL_PRIVATE_KEY = "";
    private static final String USER_ADDRESS = "";
    private static final String USER_PRIVATE_KEY = "";

    public static void main(String[] args) {

        String host = "localhost";
        int port = 8080;
        BlockchainServiceFactory serviceFactory = new BlockchainServiceFactory(host, port);

        SecureIdentity channel = new SecureIdentity(CHANNEL_ADDRESS, CHANNEL_PRIVATE_KEY);
        SecureIdentity user = new SecureIdentity(USER_ADDRESS, USER_PRIVATE_KEY);

        BlockchainSession session = serviceFactory.createSession(channel, user);
        BlockchainAddress dataBlockchainAddress = session.getSecureKeyGenerator().generateBubiAddress();

        TransactionTemplate tx = session.beginTransaction();
        DataService dataService = tx.forService(DataService.class);

        UserData data = new UserData();
        data.setId("12342312412312");
        data.setName("Jacky");


        ActionResultHolder<String> insertResultHolder = tx.prepare(dataService.insert(dataBlockchainAddress, data), String.class);

        PreparedTransaction ptx = tx.complete();

        String txHash = ptx.getHash();
        String dataAddress = insertResultHolder.getResult();

        ptx.sign(USER_ADDRESS, USER_PRIVATE_KEY);

        ptx.commit();







    }
}
