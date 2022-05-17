package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.CarWash;
import org.loose.fis.sre.model.WashType;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;


public class WashTypeService {

    private static ObjectRepository<WashType> typeRepository;

    private static String[] selectedWashTypes;

    private static int selectedWashTypesNum=0;

    public static int getSelectedWashTypesNum() {return selectedWashTypesNum;}
    public static String[] getSelectedWashTypes() {return selectedWashTypes;}

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("WashType.db").toFile())
                .openOrCreate();

       typeRepository = database.getRepository(WashType.class);
    }

    public static ObjectRepository<WashType> getTypeRepository() {
        return typeRepository;
    }

    public static void setSelectedWashTypes(String[] arr, int len)
    {
        selectedWashTypes=new String[len];
        int cnt=0;
        for(String washType:arr)
        {
            selectedWashTypes[cnt++]=washType;
        }
        selectedWashTypesNum=len;
    }

    public static void addSelectedWashType(String washType) throws WashTypeAlreadyExists {
        checkWashTypeAlreadyExists(washType);
        String[] newSelectedWashTypes = new String[getSelectedWashTypesNum() + 1];
        int cnt=0;

        if(getSelectedWashTypesNum()>0)
        {
            for(String selectedItem : selectedWashTypes )
                newSelectedWashTypes[cnt++] = selectedItem;
        }

        newSelectedWashTypes[cnt]=washType;

        setSelectedWashTypes(newSelectedWashTypes, cnt+1);
    }

    private static void checkWashTypeAlreadyExists(String selectedWashType) throws WashTypeAlreadyExists {
        if(getSelectedWashTypesNum()>0) {
            for (String washType : selectedWashTypes) {
                if (Objects.equals(washType, selectedWashType))
                    throw new WashTypeAlreadyExists(selectedWashType);
            }
        }
    }

    public static void deleteWashType(String washType) throws WashTypeDoesNotAlreadyExists {
        checkWashTypeDoesNotExists(washType);
        String[] newSelectedWashTypes = new String[getSelectedWashTypesNum() - 1];
        int cnt=0;

        if(getSelectedWashTypesNum()>0) {
            for (String type : selectedWashTypes) {
                if (!Objects.equals(type, washType))
                    newSelectedWashTypes[cnt++] = type;
            }
        }

        setSelectedWashTypes(newSelectedWashTypes, cnt);
    }

    private static void checkWashTypeDoesNotExists(String washType) throws WashTypeDoesNotAlreadyExists {
        for (String wash : selectedWashTypes) {
            if (Objects.equals(wash, washType))
                return;
        }
        throw new WashTypeDoesNotAlreadyExists(washType);
    }
}
