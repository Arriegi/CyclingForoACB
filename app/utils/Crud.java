package utils;

import play.db.jpa.JPA;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by lenovo on 09/02/2015.
 */
public class Crud <T>{

    private String tableName;
    final Class<T> typeParameterClass;

    public Crud(String tableName, Class<T> typeParameterClass) {
        this.tableName = tableName;
        this.typeParameterClass = typeParameterClass;
    }

    public Result list() {
        EntityManager em = JPA.em();
        List<T> models = em.createQuery("select r from " + tableName + " r", typeParameterClass).getResultList();
        return Controller.ok(Json.toJson(models));
    }


    public List<T> all() {
        EntityManager em = JPA.em();
        return em.createQuery("select r from " + tableName + " r", typeParameterClass).getResultList();
    }

    public T getModel(long id) {
        return JPA.em().find(typeParameterClass,id);
    }

    public Result get(Long id) {
        T t = JPA.em().find(typeParameterClass,id);
        if (t == null) {
            return Controller.notFound("element not found");
        }
        return Controller.ok(Json.toJson(t));
    }

    public Result delete(Long id) {
        EntityManager em = JPA.em();
        T r = em.find(typeParameterClass, id);
        if (r != null) {
            em.remove(r);
            em.flush();
            return Controller.ok("Object deleted");
        } else {
            return Controller.notFound("Id not found");
        }
    }

}
