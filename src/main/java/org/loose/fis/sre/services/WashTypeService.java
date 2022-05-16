package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.CarWashAlreadyExistsException;
import org.loose.fis.sre.exceptions.InvalidCredentialsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.WashTypeAlreadyExists;
import org.loose.fis.sre.model.CarWash;
import org.loose.fis.sre.model.WashType;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;


public class WashTypeService {

    private static ObjectRepository<WashType> typeRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("WashType.db").toFile())
                .openOrCreate();

       typeRepository = database.getRepository(WashType.class);
    }

    public static ObjectRepository<WashType> getTypeRepository() {
        return typeRepository;
    }

    public static void addWashType(String carwashname, String nume) throws WashTypeAlreadyExists {
        checkWashTypeAlreadyExists(carwashname, nume);
        typeRepository.insert(new WashType(carwashname, nume));
    }

    private static void checkWashTypeAlreadyExists(String carwashname, String nume) throws WashTypeAlreadyExists {
        for (WashType wash : typeRepository.find()) {
            if (Objects.equals(carwashname, wash.getCarWashName()) && Objects.equals(nume, wash.getWashTypeName()))
                throw new WashTypeAlreadyExists(carwashname);
        }
    }
}
