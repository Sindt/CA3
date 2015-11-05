/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Currency;
import entity.User;
import java.util.List;

/**
 *
 * @author Sindt
 */
public class JSONConvert {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    public static User getUserFromJson(String js) {
        return gson.fromJson(js, User.class);
    }

    public static String getJSONFromUser(User u) {
        return gson.toJson(u);
    }

    public static String getJSONFromCurrency(Currency c) {
        return gson.toJson(c);
    }

    public static String getJSONFromUsers(List<User> users) {
        return gson.toJson(users);
    }
    public static String getJSONFromCurrencys(List<Currency> curs) {
        return gson.toJson(curs);
    }

}
