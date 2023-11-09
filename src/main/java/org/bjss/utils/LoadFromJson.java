package org.bjss.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bjss.product.model.Products;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * For adding mock json from a file and then populate the Products
 */
public class LoadFromJson {
    Logger logger = Logger.getLogger(LoadFromJson.class.getName());
    /**
     * Mocking data from Json file
     *
     * @return Products
     */
    public Products loadProductsFromJsonFile(String filePathJson) {
        JSONParser parser = new JSONParser();
        Object obj;
        JSONObject jsonObject;
        ObjectMapper objectMapper;
        try {
            obj = parser.parse(new FileReader(filePathJson));
            jsonObject = (JSONObject) obj;
            jsonObject.toJSONString();
            objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonObject.toJSONString(), Products.class);
        } catch (IOException e) {
            logger.warning("Problems reading Json file, we will load from internal memory");

        } catch (ParseException e) {
            logger.warning("Problems when parsing file");
        }
        return null;
    }
}
