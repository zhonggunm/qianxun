package com.qianxun.qxtag.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class TransactionService {

    @Autowired
    private SessionFactory sessionFactory;

    public Response handle(BaseHandler qxBaseHandler) {
        Response response = new Response();
        try{
            Session session = sessionFactory.getCurrentSession();
            qxBaseHandler.authonticate(session, response);
            qxBaseHandler.validate(session,response);
            qxBaseHandler.prepare(session, response);

            if (qxBaseHandler.isTransactionEnabled()) {
                runTransaction(qxBaseHandler, session, response);
            } else {
                qxBaseHandler.exec(session, response);
            }

            qxBaseHandler.finish(session, response);
        }catch (QianxunException e){
            e.printStackTrace();
            response = e.toResponse();
        }catch (Exception e){
            e.printStackTrace();
            response.setResponse(ResultCode.DatabaseCommonError, e.getCause().toString());
        }
        return response;
    }

    // Reuse exec() within a transaction
    @Transactional(propagation = Propagation.REQUIRED)
    protected void runTransaction(BaseHandler baseHandler,
                                  Session session, Response response) throws QianxunException {
        Transaction tx = session.beginTransaction();
        try {
            baseHandler.exec(session, response);
            tx.commit();
        } catch (QianxunException e) {
            tx.rollback();
            throw e;
        } catch (Exception e) {
            tx.rollback();
            throw new QianxunException(ResultCode.DatabaseCommonError, e.getCause().toString());
        }
    }
}
