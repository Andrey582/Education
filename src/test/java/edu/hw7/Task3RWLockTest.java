package edu.hw7;

import edu.hw7.Task3.DatabaseReedWrite;
import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3RWLockTest {

    private static PersonDatabase db;

    @BeforeEach
    void createDBRW() {
        db = new DatabaseReedWrite();
    }

    @Test
    void addUserRWLock() {
        Person person = new Person(5, "Misha", "Volgograd", "8259");
        try (ExecutorService service = Executors.newFixedThreadPool(4)) {

            service.submit(addPersonToDB()).get();

            Future<List<Person>> findByName = service.submit(() -> db.findByName(person.name()));
            Future<List<Person>> findByAddress = service.submit(() -> db.findByAddress(person.address()));
            Future<List<Person>> findByPhoneNumber = service.submit(() -> db.findByPhone(person.phoneNumber()));

            assertThat(findByName.get().get(0)).isEqualTo(person);
            assertThat(findByAddress.get().get(0)).isEqualTo(person);
            assertThat(findByPhoneNumber.get().get(0)).isEqualTo(person);

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteUserRWLock() {
        Person person = new Person(5, "Misha", "Volgograd", "8259");
        try (ExecutorService service = Executors.newFixedThreadPool(4)) {

            service.submit(addPersonToDB()).get();

            service.submit(() -> db.delete(5));

            Future<List<Person>> findByName = service.submit(() -> db.findByName(person.name()));
            Future<List<Person>> findByAddress = service.submit(() -> db.findByAddress(person.address()));
            Future<List<Person>> findByPhoneNumber = service.submit(() -> db.findByPhone(person.phoneNumber()));

            assertThat(findByName.get()).isNull();
            assertThat(findByAddress.get()).isNull();
            assertThat(findByPhoneNumber.get()).isNull();

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addWithSameIdUserRWLock() {
        Person person = new Person(5, "Lesha", "Irkutsk", "8219");
        try (ExecutorService service = Executors.newFixedThreadPool(4)) {

            service.submit(addPersonToDB()).get();

            service.submit(() -> db.add(person));

            Future<List<Person>> findByName = service.submit(() -> db.findByName(person.name()));
            Future<List<Person>> findByAddress = service.submit(() -> db.findByAddress(person.address()));
            Future<List<Person>> findByPhoneNumber = service.submit(() -> db.findByPhone(person.phoneNumber()));

            assertThat(findByName.get().get(0)).isEqualTo(person);
            assertThat(findByAddress.get().get(0)).isEqualTo(person);
            assertThat(findByPhoneNumber.get().get(0)).isEqualTo(person);

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Runnable addPersonToDB() {
        return () -> {
            db.add(new Person(1, "Andrey", "SPB", "8953"));
            db.add(new Person(2, "Igor", "Moscow", "8973"));
            db.add(new Person(3, "Ilya", "Kursk", "8567"));
            db.add(new Person(4, "Egor", "Sochi", "8385"));
            db.add(new Person(5, "Misha", "Volgograd", "8259"));
        };
    }
}
