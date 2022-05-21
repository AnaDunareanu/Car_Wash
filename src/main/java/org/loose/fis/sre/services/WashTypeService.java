package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.Appointment;
import org.loose.fis.sre.model.CarWash;
import org.loose.fis.sre.model.WashType;

import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;


public class WashTypeService {

    private static ObjectRepository<WashType> typeRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("wst.db").toFile())
                .openOrCreate();

       typeRepository = database.getRepository(WashType.class);
    }


    public static ObjectRepository<WashType> getTypeRepository() {
        return typeRepository;
    }


    public static void addSelectedWashType(String washType,int price,String carwashName) throws WashTypeAlreadyExists {
        checkWashTypeAlreadyExists(washType,carwashName);
        int nr=1;
        for (WashType washtype : typeRepository.find())
        {
            nr=washtype.getNr();
            nr++;
        }
        typeRepository.insert(new WashType(nr, price,carwashName, washType));
    }

    private static void checkWashTypeAlreadyExists(String selectedWashType,String carwashName) throws WashTypeAlreadyExists {
        for (WashType wash : typeRepository.find()) {
            if (Objects.equals(selectedWashType,wash.getWashTypeName()) && Objects.equals(carwashName,wash.getCarWashName()))
                throw new WashTypeAlreadyExists(selectedWashType);
        }
    }

    public static void deleteWashType(String washType) throws WashTypeDoesNotAlreadyExists {
        checkWashTypeDoesNotExists(washType);
        for (WashType wash : typeRepository.find())
        {
            if (Objects.equals(washType,wash.getWashTypeName()))
            {
                typeRepository.remove(wash);
            }
        }

    }

    private static void checkWashTypeDoesNotExists(String washType) throws WashTypeDoesNotAlreadyExists {
        for (WashType wash : typeRepository.find()) {
            if (Objects.equals(wash.getWashTypeName(), washType))
                return;
        }
        throw new WashTypeDoesNotAlreadyExists(washType);
    }
}
