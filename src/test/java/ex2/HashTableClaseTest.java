package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

class HashTableTest {
    //COMPROBAR PUT
    // Inserir un element que no col·lisiona dins una taula vuida (sense elements).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void combprobarputtablavacia(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5]",m.toString());
    }
    // Inserir un element que no col·lisiona dins una taula no vuida (sense elements).
    @ParameterizedTest
    @CsvSource({"5,5,6,7"})
    void combprobarputtablanovacia(String key, String valor,String key2,String valor2) {
        HashTable m = new HashTable();
            m.put(key,valor);
            m.put(key2,valor2);
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5]\n" +
                " bucket[6] = [6, 7]",m.toString());
    }
    // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void combprobarputcolisiontablavacia(String key, String valor) {
        HashTable m = new HashTable();
            m.put(key,valor);
            m.put(m.getCollisionsForKey(key),"66");
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5] -> [05, 66]",m.toString());
    }
    // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void combprobarputcolisiontablanovacia(String key, String valor) {
        HashTable m = new HashTable();
            m.put(key,valor);
            ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5] -> [05, pepito] -> [16, pizza]",m.toString());
    }
    // Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void combprobarputupdatetablavacia(String key, String valor) {
        HashTable m = new HashTable();
            m.put(key,valor);
            m.put(key,"pepito");
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, pepito]",m.toString());
    }
    //Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (1a posició) dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarputupdate1posicion(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.put(key,"manzana");
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, manzana] -> [05, pepito] -> [16, pizza]",m.toString());
    }
    // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarputupdate2posicion(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.put(keys.get(0),"manzana");
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5] -> [05, manzana] -> [16, pizza]",m.toString());
    }
    // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarputupdate3posicion(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key, valor);
        ArrayList<String> keys = m.getCollisionsForKey(key, 2);
        m.put(keys.get(0), "pepito");
        m.put(keys.get(1), "pizza");
        m.put(keys.get(1), "manzana");
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5] -> [05, pepito] -> [16, manzana]", m.toString());
    }

    //Comprobar GET
    //Obtenir un element que no col·lisiona dins una taula vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobargetvacio(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        Assertions.assertEquals(valor,m.get(key));
    }
    //Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobargetcolisiona1(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        Assertions.assertEquals(valor,m.get(key));
    }
    //Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobargetcolisiona2(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        Assertions.assertEquals("pepito",m.get(keys.get(0)));
    }
    //Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobargetcolisiona3(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        Assertions.assertEquals("pizza",m.get(keys.get(1)));
    }
    // Obtenir un elements que no existeix perquè la seva posició està buida (no hi ha cap element dins el bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobargethastablevacio(String key, String valor) {
        HashTable m = new HashTable();
        Assertions.assertNull(m.get(key));
    }
    // Obtenir un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobargethastablenovacionocolision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key, valor);

        Assertions.assertNull(m.get(m.getCollisionsForKey(key)));
    }
    //  Obtenir un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobargethastablenovaciocolision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        Assertions.assertNull(m.get(keys.get(2)));
    }

    //Drop
    //Esborrar un element que no col·lisiona dins una taula.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobardropnocolision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
       m.drop(key);
        Assertions.assertEquals("",m.toString());
    }
    // Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobardropcolision1(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(key);
        Assertions.assertEquals("\n" +
                " bucket[5] = [05, pepito] -> [16, pizza]",m.toString());
    }
    // Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobardropcolision2(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(keys.get(0));
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5] -> [16, pizza]",m.toString());
    }
    // Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobardropcolision3(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(keys.get(1));
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5] -> [05, pepito]",m.toString());
    }
    //  Eliminar un elements que no existeix perquè la seva posició està buida (no hi ha cap element dins el bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobardropvacio(String key, String valor) {
       HashTable m = new HashTable();
        m.drop(key);
        Assertions.assertEquals("",m.toString());
    }
    //            Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.

    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobardropnoexistec(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.drop(keys.get(0));
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5]",m.toString());
    }
    //            Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.

    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobardropnoexiste(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(keys.get(2));
        Assertions.assertEquals("\n" +
                " bucket[5] = [5, 5] -> [05, pepito] -> [16, pizza]",m.toString());
    }

    //Count

    // Inserir un element que no col·lisiona dins una taula vuida (sense elements).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountvacio(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        Assertions.assertEquals(1,m.count());
    }
    // Inserir un element que no col·lisiona dins una taula no vuida (sense elements).
    @ParameterizedTest
    @CsvSource({"5,5,6,7"})
    void comprobarcountnovacio(String key, String valor,String key2,String valor2) {
        HashTable m = new HashTable();
        m.put(key,valor);
        m.put(key2,valor2);
        Assertions.assertEquals(2,m.count());
    }
    // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountnovaciocolisiona(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        m.put(m.getCollisionsForKey(key),"66");
        Assertions.assertEquals(2,m.count());
    }
    // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountvaciocolion(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        Assertions.assertEquals(3,m.count());
    }
    // Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountvacioupdate(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        m.put(key,"pepito");
        Assertions.assertEquals(1,m.count());
    }
    //Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (1a posició) dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountvaciocolision1(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.put(key,"manzana");
        Assertions.assertEquals(3,m.count());
    }
    // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountvaciocolision2(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.put(keys.get(0),"manzana");
        Assertions.assertEquals(3,m.count());
    }
    // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountvaciocolision3(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key, valor);
        ArrayList<String> keys = m.getCollisionsForKey(key, 2);
        m.put(keys.get(0), "pepito");
        m.put(keys.get(1), "pizza");
        m.put(keys.get(1), "manzana");
        Assertions.assertEquals(3, m.count());
    }
    //Esborrar un element que no col·lisiona dins una taula.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountdropvacioo(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        m.drop(key);
        Assertions.assertEquals(0,m.count());
    }
    // Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountdrop1colision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(key);
        Assertions.assertEquals(2,m.count());
    }
    // Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountdrop2colision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(keys.get(0));
        Assertions.assertEquals(2,m.count());
    }
    // Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountdrop3colision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(keys.get(1));
        Assertions.assertEquals(2,m.count());
    }
    //  Eliminar un elements que no existeix perquè la seva posició està buida (no hi ha cap element dins el bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountdropvacio(String key, String valor) {
        HashTable m = new HashTable();
        m.drop(key);
        Assertions.assertEquals(0,m.count());
    }
    //            Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.

    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountdropnoexisteix(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.drop(keys.get(0));
        Assertions.assertEquals(1,m.count());
    }
    //            Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.

    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarcountdrop3nocolision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(keys.get(2));
        Assertions.assertEquals(3,m.count());
    }
    //size
    // Inserir un element que no col·lisiona dins una taula vuida (sense elements).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizevacio(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        Assertions.assertEquals(16,m.size());
    }
    // Inserir un element que no col·lisiona dins una taula no vuida (sense elements).
    @ParameterizedTest
    @CsvSource({"5,5,6,7"})
    void comprobarsizenovacio(String key, String valor,String key2,String valor2) {
        HashTable m = new HashTable();
        m.put(key,valor);
        m.put(key2,valor2);
        Assertions.assertEquals(16,m.size());
    }
    // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizenovaciocolision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        m.put(m.getCollisionsForKey(key),"66");
        Assertions.assertEquals(16,m.size());
    }
    // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizevaciocolisionnovacio(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        Assertions.assertEquals(16,m.size());
    }
    // Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizeexistentenocolison(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        m.put(key,"pepito");
        Assertions.assertEquals(16,m.size());
    }
    //Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (1a posició) dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizevacioexisteix1(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.put(key,"manzana");
        Assertions.assertEquals(16,m.size());
    }
    // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizevacioexisteix2(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,2);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.put(keys.get(0),"manzana");
        Assertions.assertEquals(16,m.size());
    }
    // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no vuida.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizevacioexisteix13(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key, valor);
        ArrayList<String> keys = m.getCollisionsForKey(key, 2);
        m.put(keys.get(0), "pepito");
        m.put(keys.get(1), "pizza");
        m.put(keys.get(1), "manzana");
        Assertions.assertEquals(16, m.size());
    }
    //Esborrar un element que no col·lisiona dins una taula.
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizedrop(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        m.drop(key);
        Assertions.assertEquals(16,m.size());
    }
    // Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizedropsicolisiona(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(key);
        Assertions.assertEquals(16,m.size());
    }
    // Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizedropsicolision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(keys.get(0));
        Assertions.assertEquals(16,m.size());
    }
    // Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizedrop3colision(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(keys.get(1));
        Assertions.assertEquals(16,m.size());
    }
    //  Eliminar un elements que no existeix perquè la seva posició està buida (no hi ha cap element dins el bucket).
    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizedropvacio(String key, String valor) {
        HashTable m = new HashTable();
        m.drop(key);
        Assertions.assertEquals(16,m.size());
    }
    //            Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.

    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizedropnoexisteix(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.drop(keys.get(0));
        Assertions.assertEquals(16,m.size());
    }
    //            Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.

    @ParameterizedTest
    @CsvSource({"5,5"})
    void comprobarsizedropnoexisteixxxx(String key, String valor) {
        HashTable m = new HashTable();
        m.put(key,valor);
        ArrayList<String> keys = m.getCollisionsForKey(key,3);
        m.put(keys.get(0),"pepito");
        m.put(keys.get(1),"pizza");
        m.drop(keys.get(2));
        Assertions.assertEquals(16,m.size());
    }




}