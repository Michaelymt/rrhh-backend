package pe.com.tss.runakuna.service.impl;

import org.springframework.stereotype.Service;
import pe.com.tss.runakuna.service.intf.DateTimeRetriever;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by josediaz on 28/10/2016.
 */
@Service
public class DateTimeRetrieverImpl implements DateTimeRetriever {

    @Override
    public Date currentTime() {
        return new Timestamp(System.currentTimeMillis()); //TODO JCM implement ofr time sync betwen servers
    }
}
