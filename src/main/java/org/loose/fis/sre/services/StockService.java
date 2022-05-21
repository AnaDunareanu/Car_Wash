package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.StockItemAlreadyExists;
import org.loose.fis.sre.exceptions.StockItemDoesNotAlreadyExist;
import org.loose.fis.sre.model.Stock;

import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class StockService {

    private static ObjectRepository<Stock> stockRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("stock.db").toFile())
                .openOrCreate();

        stockRepository = database.getRepository(Stock.class);
    }

    public static ObjectRepository<Stock> getStockRepository() {
        return stockRepository;
    }


    public static void addSelectedStock(String stock,String carwashName) throws StockItemAlreadyExists {
        checkStockItemAlreadyExists(stock,carwashName);
        int nr=1;
        for (Stock stk : stockRepository.find())
        {
            nr=stk.getNr();
            nr++;
        }
        stockRepository.insert(new Stock(carwashName,stock,nr));
    }

    private static void checkStockItemAlreadyExists(String stockItem,String carwashName) throws StockItemAlreadyExists {
        for (Stock stk : stockRepository.find()) {
            if (Objects.equals(stockItem,stk.getStockName()) && Objects.equals(carwashName,stk.getCarWashName()))
                throw new StockItemAlreadyExists(stockItem);
        }

    }

    public static void deleteStock(String item) throws StockItemDoesNotAlreadyExist {
        checkStockItemDoesNotExist(item);
        for (Stock stk : stockRepository.find()) {
            if (Objects.equals(item, stk.getStockName())) {
                stockRepository.remove(stk);
            }
        }
    }

    private static void checkStockItemDoesNotExist (String item) throws StockItemDoesNotAlreadyExist {
            for (Stock stk : stockRepository.find()) {
                if (Objects.equals(stk.getStockName(), item))
                    return;
            }
        throw new StockItemDoesNotAlreadyExist(item);
    }
}
