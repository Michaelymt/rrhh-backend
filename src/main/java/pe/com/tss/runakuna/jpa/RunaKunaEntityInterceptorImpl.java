package pe.com.tss.runakuna.jpa;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.tss.runakuna.domain.model.base.BaseEntity;
import pe.com.tss.runakuna.service.intf.DateTimeRetriever;
import pe.com.tss.runakuna.support.LogContext;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by josediaz on 28/10/2016.
 */
@Component
public class RunaKunaEntityInterceptorImpl extends EmptyInterceptor implements RunaKunaEntityInterceptor {
    @Autowired
    DateTimeRetriever dateTimeRetriever;


    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

        if (entity instanceof BaseEntity) {

            for (int i = 0; i < propertyNames.length; i++) {
                String propertyName = propertyNames[i];

                if (propertyName.equals("creador")) {
                    state[i] = LogContext.getUserIdSafe();
                } else if (propertyName.equals("fechaCreacion")) {
                    state[i] = currentTime();
                }
            }
        }
        return true; //super.onSave(entity, id, state, propertyNames, types);
    }

    private Date currentTime() {
        if (dateTimeRetriever == null) return new Timestamp(System.currentTimeMillis());
        Date date = dateTimeRetriever.currentTime();
        return date;
    }


    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof BaseEntity) {


            for (int i = 0; i < propertyNames.length; i++) {
                String propertyName = propertyNames[i];

                if (propertyName.equals("actualizador")) {
                    currentState[i] = LogContext.getUserIdSafe();
                } else if (propertyName.equals("fechaActualizacion")) {
                    currentState[i] = currentTime();
                }
            }

        }


        return true;
    }
}