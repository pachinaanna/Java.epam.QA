package com.epam.training.repository;

import com.epam.training.model.Park;
import com.epam.training.utils.DbConnectionManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class ParkRepositoryTest {

    DbConnectionManager dbConnectionManager = new DbConnectionManager(
            "jdbc:mysql://localhost:3306/Parks?autoReconnect=true&useSSL=false&useUnicode=true&serverTimezone=UTC",
            "root",
            "pachina",
            "com.mysql.cj.jdbc.Driver"
    );

    ParkRepository parkRep = new ParkRepository(dbConnectionManager);

    Park testPark1 = new Park("ЛЛЛ", 1.5, "Санкт-Петербург");
    Park testPark2 = new Park("ППП", 2.5, "Санкт-Петербург, Невский пр.");
    String newName = "YYY";

    String dropFKG = "ALTER TABLE gardener DROP FOREIGN KEY gardener_ibfk_1;";
    String dropFKZ = "ALTER TABLE zone DROP FOREIGN KEY zone_ibfk_1;";
    String dropFKZP = "ALTER TABLE zone_plant DROP FOREIGN KEY zone_plant_ibfk_1;";
    String truncateP = "TRUNCATE TABLE park;";
    String truncateG = "TRUNCATE TABLE gardener;";
    String truncateZ = "TRUNCATE TABLE zone;";
    String truncateZP = "TRUNCATE TABLE zone_plant;";
    String addFKG = "ALTER TABLE gardener ADD FOREIGN KEY (park_id) REFERENCES Park (id)";
    String addFKZ = "ALTER TABLE zone ADD FOREIGN KEY (park_id) REFERENCES Park (id)";
    String addFKZP = "ALTER TABLE zone_plant ADD FOREIGN KEY (zone_id) REFERENCES zone (id)";


    @BeforeMethod
    private void truncateTable () {
        parkRep.clearTable(dropFKG);
        parkRep.clearTable(dropFKZ);
        parkRep.clearTable(dropFKZP);
        parkRep.clearTable(truncateP);
        parkRep.clearTable(truncateG);
        parkRep.clearTable(truncateZ);
        parkRep.clearTable(truncateZP);
        parkRep.clearTable(addFKG);
        parkRep.clearTable(addFKZ);
        parkRep.clearTable(addFKZP);
        set();
    }

    private void set() {
        parkRep.insert(testPark1);
    }

    @Test
    public void testInsert() {
        // Добавление в таблицу новой записи
        boolean isCreated = parkRep.insert(testPark2);

        // Создание списка, в котором хранится результят выполнения метода readAll
        List<Park> all = parkRep.readAll();

        Assert.assertTrue(isCreated, "Новая запись добалена");
        Assert.assertEquals(all.size(), 2, "Проверка добавления");
        Assert.assertEquals(all.get(1).getId(), 2,"Проверка того, что сгенерирован id");
        Assert.assertEquals(testPark2.getName(), all.get(1).getName(), "Проверка поля name");
        Assert.assertEquals(testPark2.getSquare(), all.get(1).getSquare(), "Проверка поля square");
        Assert.assertEquals(testPark2.getLocation(), all.get(1).getLocation(), "Проверка поля location");
    }

    @Test
    public void testUpdate() {
        // Обновление строки в таблице
        boolean isUpdated = parkRep.update(newName, 1);

        // Создание списка, в котором хранится результат выполнения метода update
        List<Park> afterUpdate = parkRep.readAll();

        Assert.assertTrue(isUpdated, "Запись обновлена");

        Assert.assertEquals(newName, afterUpdate.get(0).getName(), "Проверка поля name");
        Assert.assertEquals(testPark1.getSquare(), afterUpdate.get(0).getSquare(), "Проверка поля square");
        Assert.assertEquals(testPark1.getLocation(), afterUpdate.get(0).getLocation(), "Проверка поля location");
    }

    @Test
    public void testRead() {
        // Чтение записей по id
        Park parkById = parkRep.read(1);

        Assert.assertEquals(parkById.getId(), 1, "Проверка по id");
        Assert.assertEquals(parkById.getName(), testPark1.getName(), "Проверка по имени");
        Assert.assertEquals(parkById.getSquare(), testPark1.getSquare(), "Проверка по square");
        Assert.assertEquals(parkById.getLocation(), testPark1.getLocation(), "Проверка поля location");
    }

    @Test
    public void testReadAll() {
        parkRep.insert(testPark2);
        // Чтение всех записей из таблицы
        List<Park> allParks = parkRep.readAll();

        Assert.assertEquals(testPark1.getName(), allParks.get(0).getName(), "Проверка 2 записи по name");
        Assert.assertEquals(testPark1.getSquare(), allParks.get(0).getSquare(), "Проверка 2 записи по square");
        Assert.assertEquals(testPark1.getLocation(), allParks.get(0).getLocation(), "Проверка 2 записи по location");
        Assert.assertEquals(testPark2.getName(), allParks.get(1).getName(), "Проверка 1 записи по name");
        Assert.assertEquals(testPark2.getSquare(), allParks.get(1).getSquare(), "Проверка 1 записи по square");
        Assert.assertEquals(testPark2.getLocation(), allParks.get(1).getLocation(), "Проверка 1 записи по location");
    }

    @Test
    public void testDelete() {
        // Удаление записи из таблицы
        boolean isDeleted = parkRep.delete(1);
        // Создание списка, в котором хранится результат выполнения метода delete
        List<Park> afterDelete = parkRep.readAll();

        Assert.assertTrue(isDeleted, "Запись удалена");
        Assert.assertEquals(afterDelete.size(), 0, "Проверка удаления");
    }
}
