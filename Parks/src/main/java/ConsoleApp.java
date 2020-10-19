import com.epam.training.model.Gardener;
import com.epam.training.model.Park;
import com.epam.training.repository.GardenerRepository;
import com.epam.training.repository.ParkRepository;
import com.epam.training.utils.DbConnectionManager;

public class ConsoleApp {


    public static void main(String[] args) throws IllegalAccessException {

        args = new String[]{
        };

        DbConnectionManager dbConnectionManager = new DbConnectionManager(
                "jdbc:mysql://localhost:3306/Parks?autoReconnect=true&useSSL=false&useUnicode=true&serverTimezone=UTC",
                "root",
                "pachina",
                "com.mysql.cj.jdbc.Driver"
        );
        ParkRepository parkRepository = new ParkRepository(dbConnectionManager);
        GardenerRepository gardenerRepository = new GardenerRepository(dbConnectionManager);


        if (args.length < 2) {
            throw new IllegalArgumentException("add here proper message");
        }

        String entityType = args[0];

        if ("park".equals(entityType)) {
            String operation = args[1];
            if ("create".equals(operation)) {
                parkRepository.insert(new Park(
                        args[2],
                        Double.parseDouble(args[3]),
                        args[4]));

            } else if ("update".equals(operation)) {
                parkRepository.update(args[2], Long.parseLong(args[3]));

            } else if ("read".equals(operation)) {
                System.out.println(parkRepository.read(Long.parseLong(args[2])));

            } else if ("readAll".equals(operation)) {
                System.out.println(parkRepository.readAll());

            } else if ("delete".equals(operation)) {
                parkRepository.delete((Long.parseLong(args[2])));

            } else {
                throw new IllegalAccessException(
                        "Supported only following operations: " + "create / read / readAll / update / delete");
            }

        } else if ("gardener".equals(entityType)) {
            String operation = args[1];

            if ("create".equals(operation)) {
                gardenerRepository.insert(new Gardener (
                        args[2],
                        Long.parseLong(args[3])));

            } else if ("update".equals(operation)) {
                gardenerRepository.update(args[2], Long.parseLong(args[3]));

            } else if ("read".equals(operation)) {
                System.out.println(gardenerRepository.read(Long.parseLong(args[2])));

            } else if ("readAll".equals(operation)) {
                System.out.println(gardenerRepository.readAll());

            } else if ("delete".equals(operation)) {
                gardenerRepository.delete((Long.parseLong(args[2])));

            } else {
                throw new IllegalAccessException(
                        "Supported only following operations: " + "create / read / readAll / update / delete");
            }

        } else {
            throw new IllegalAccessException(
                    "Supported only following entityType: " +  "park / gardener");
        }
    }
    }