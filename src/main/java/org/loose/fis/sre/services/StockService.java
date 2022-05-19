package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.StockItemAlreadyExists;
import org.loose.fis.sre.exceptions.StockItemDoesNotAlreadyExist;
import org.loose.fis.sre.exceptions.WashTypeAlreadyExists;
import org.loose.fis.sre.exceptions.WashTypeDoesNotAlreadyExists;
import org.loose.fis.sre.model.Stock;
import org.loose.fis.sre.model.WashType;

import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class StockService {

    private static ObjectRepository<Stock> stockRepository;

    private static String[] selectedStock;

    private static int selectedStockNum=0;

    public static int getSelectedStockNum() {return selectedStockNum;}
    public static String[] getSelectedStock() {return selectedStock;}

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Stock.db").toFile())
                .openOrCreate();

        stockRepository = database.getRepository(Stock.class);
    }

    public static ObjectRepository<Stock> getStockRepository() {
        return stockRepository;
    }

    public static void setSelectedStock(String[] arr, int len)
    {
        selectedStock=new String[len];
        int cnt=0;
        for(String stockItem:arr)
        {
            selectedStock[cnt++]=stockItem;
        }
        selectedStockNum=len;
    }

    public static void addSelectedStock(String stock) throws StockItemAlreadyExists {
        checkStockItemAlreadyExists(stock);
        String[] newSelectedStock = new String[getSelectedStockNum() + 1];
        int cnt=0;

        if(getSelectedStockNum()>0)
        {
            for(String selectedItem : selectedStock )
                newSelectedStock[cnt++] = selectedItem;
        }

        newSelectedStock[cnt]=stock;

        setSelectedStock(newSelectedStock, cnt+1);
    }

    private static void checkStockItemAlreadyExists(String stockItem) throws StockItemAlreadyExists {
        if(getSelectedStockNum()>0) {
            for (String itemStock : selectedStock) {
                if (Objects.equals(itemStock, stockItem))
                    throw new StockItemAlreadyExists(stockItem);
            }
        }
    }

    public static void deleteStock(String item) throws StockItemDoesNotAlreadyExist {
        checkStockItemDoesNotExist(item);
        String[] newSelectedStock = new String[getSelectedStockNum() - 1];
        int cnt=0;

        if(getSelectedStockNum()>0) {
            for (String type : selectedStock) {
                if (!Objects.equals(type, item))
                    newSelectedStock[cnt++] = type;
            }
        }

        setSelectedStock(newSelectedStock, cnt);
    }

    private static void checkStockItemDoesNotExist(String item) throws StockItemDoesNotAlreadyExist {
        for (String stk : selectedStock) {
            if (Objects.equals(stk, item))
                return;
        }
        throw new StockItemDoesNotAlreadyExist(item);
    }
}
