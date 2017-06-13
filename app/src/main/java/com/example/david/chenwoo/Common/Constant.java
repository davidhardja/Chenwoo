package com.example.david.chenwoo.Common;

import com.example.david.chenwoo.Database.Data.ProductWrapper;
import com.example.david.chenwoo.Database.Data.Sale;

import java.util.List;

/**
 * Created by David on 31/03/2017.
 */

public class Constant {

    // ============== App Name & Version =========================
    public static String APP_NAME = "Chenwoo";
    public static String APP_VERSION = "2.0.0";

    // ============== APIs =========================
    public static String API_URL = "https://cms.chenwoofishery.com/chenwoo/";
    //public static String API_URL = "https://project.tommyhost.net/chenwoo/";

    // ============== Response Code =========================
    public static final int API_SUCCESS = 200;
    public static final int DATA_NOT_FOUND = 401;
    public static final int UNAUTORIZED = 403;
    public static final int STATUS_BERUBAH = 207;
    public static final int FAIL = 405;
    public static final int PARAMETER_MISSING = 406;
    public static final int LOGIN_SUCCESS = 201;


    //=============== Database =================================
    public static String DATABASE_NAME = "Template_DB";
    public static int DATABASE_VERSION = 1;

    //=============== Font =================================
    //Font
    public static String DEFAULT_FONT = "fonts/SourceSansPro.ttf";
    public static String OSWALD_FONT = "fonts/Oswald.otf";

    public static List<ProductWrapper> listProductWrapper;
    public static List<Sale> sales;

    public static int positionListProductWrapper = -1;
    public static int positionListSales = -1;


}
