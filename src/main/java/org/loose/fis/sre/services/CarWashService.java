package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.CarWashAlreadyExistsException;
import org.loose.fis.sre.model.CarWash;

import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class CarWashService {

    private static ObjectRepository<CarWash> carwashRepository;
    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("car-wash.db").toFile())
                .openOrCreate();

        carwashRepository = database.getRepository(CarWash.class);
    }

    public static ObjectRepository<CarWash> getCarWashRepository() {
        return carwashRepository;
    }

    public static void addCarWash(String carwashname, String address, String administrator) throws CarWashAlreadyExistsException {
        checkCarWashAlreadyExists(carwashname);
        carwashRepository.insert(new CarWash(carwashname, address, administrator));
    }

    private static void checkCarWashAlreadyExists(String carwashname) throws CarWashAlreadyExistsException {
        for (CarWash carwash : carwashRepository.find()) {
            if (Objects.equals(carwashname, carwash.getCarWashName()))
                throw new CarWashAlreadyExistsException(carwashname);
        }
    }

}
