
package com.epam.training.repository;

import com.epam.training.model.Gardener;
import com.epam.training.utils.DbConnectionManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.util.List;


public class GardenerRepositoryTest {

    DbConnectionManager dbConnectionManager = new DbConnectionManager(
            "jdbc:mysql://localhost:3306/Parks?autoReconnect=true&useSSL=false&useUnicode=true&serverTimezone=UTC",
            "root",
            "pachina",
            "com.mysql.cj.jdbc.Driver"
    );

    GardenerRepository gardRep = new GardenerRepository(dbConnectionManager);
    Gardener testGardener1 = new Gardener("yyy", 3);
    Gardener testGardener2 = new Gardener("VVV", 2);
    String newName = "XXX";

    @BeforeTest
    private boolean removeAll() {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement("TRUNCATE TABLE Gardener");
            return prepState.executeUpdate() > 0;
        }
        );
    }

    @Test
    public void testGardenerRepository() {
        //TODO: Refactor to separated tests for each method

        // Добавление в таблицу новой записи
        boolean isCreated = gardRep.insert(testGardener1);

        List<Gardener> all = gardRep.readAll();

        Assert.assertTrue(isCreated, "Новая запись добалена");
        Assert.assertEquals(all.size(), 1, "Проверка добавления");
        Assert.assertEquals(1, all.get(0).getId(), "Проверка id");
        Assert.assertEquals(testGardener1.getName(), all.get(0).getName(), "Проверка name");
        Assert.assertEquals(testGardener1.getParkId(), all.get(0).getParkId(), "Проверка park_id");

        // Добавление еще одной записи в таблицу
        gardRep.insert(testGardener2);

        // Чтение записей по id
        Gardener gardenerById = gardRep.read(2);

        Assert.assertEquals(gardenerById.getId(), 2, "Проверка id");
        Assert.assertEquals(gardenerById.getName(), testGardener2.getName(), "Проверка name");
        Assert.assertEquals(gardenerById.getParkId(), testGardener2.getParkId(), "Проверка park_id");

        // Чтение всех записей из таблицы
        List<Gardener> allGardeners = gardRep.readAll();

        Assert.assertEquals(testGardener1.getParkId(), allGardeners.get(0).getParkId(), "Проверка первой записи");
        Assert.assertEquals(testGardener1.getName(), allGardeners.get(0).getName(), "Проверка первой записи");
        Assert.assertEquals(testGardener2.getParkId(), allGardeners.get(1).getParkId(), "Проверка второй записи");
        Assert.assertEquals(testGardener2.getName(), allGardeners.get(1).getName(), "Проверка второй записи");

        // Обновление строки в таблице
        boolean isUpdated = gardRep.update(newName, 1);

        List<Gardener> afterUpdate = gardRep.readAll();

        Assert.assertTrue(isUpdated, "Запись обновлена");
        Assert.assertEquals(1, afterUpdate.get(0).getId(), "Проверка id");
        Assert.assertEquals(newName, afterUpdate.get(0).getName(), "Проверка name");
        Assert.assertEquals(testGardener1.getParkId(), afterUpdate.get(0).getParkId(), "Проверка park_id");

        // Удаление записи из таблицы
        boolean isDeleted = gardRep.delete(1);

        List<Gardener> afterDelete = gardRep.readAll();

        Assert.assertTrue(isDeleted, "Запись удалена");
        Assert.assertEquals(afterDelete.size(), 1, "Проверка удаления");
    }
}

