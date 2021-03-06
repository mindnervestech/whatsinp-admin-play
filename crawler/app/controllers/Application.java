package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import models.ProductDetails;
import models.ScrappedData;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import views.html.index;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {
public static String root; 
	static {
		
		String os = System.getProperty("os.name").toLowerCase();
		if(os.indexOf("win") >=0 ) {
			root =  "C:\\home\\scrapper";
		} else if (os.indexOf("mac") >=0 ) {
			root = "/home/scrapper";
		} else {
			root = "/home/scrapper";
		}
}

    public static Result index() {
    	List<ScrappedData> datas = Ebean.find(ScrappedData.class).where().eq("status",0).findList();
    	List<SqlRow> siteNames = Ebean.createSqlQuery("Select distinct site, date from scrapped_data").findList();
    	List<String> sname = new ArrayList<String>();
    	List<String> dateFilter = new ArrayList<String>();
    	for(SqlRow site :siteNames){
    		sname.add(site.getString("site"));
    	}
        return ok(index.render(datas, sname, dateFilter) );
    }
    
    public static Result saveKeyValue() {
		DynamicForm form = DynamicForm.form().bindFromRequest();
		ScrappedData scrappedData = ScrappedData.findById(Long.parseLong(form.get("Id")));
		
			if(scrappedData.getProductDetails()!=null) 
			{
				ProductDetails productDetails = ProductDetails.findById(scrappedData.getProductDetails().id);
				productDetails.setProduct_name(form.get("pdproname"));
				productDetails.setSku(form.get("pdsku"));
				String pdcategories = form.get("pdcategories");
				pdcategories = pdcategories.replace("[", "");
				pdcategories = pdcategories.replace("]", "");
				pdcategories = pdcategories.replace("\"", "");
				productDetails.setCategory_ids(pdcategories);
				
				productDetails.setName(form.get("pdname"));
				productDetails.setUrl_key(form.get("pdurlkey"));
				productDetails.setMeta_title(form.get("pdmetatitle"));
				productDetails.setMeta_description(form.get("pdmetadesc"));
				productDetails.setImage(form.get("pdimages"));
				productDetails.setSmall_image(form.get("pdsmallimages"));
				productDetails.setThumbnail(form.get("pdthumbnail"));
				productDetails.setRotator_image(form.get("pdrotatorimage"));
				productDetails.setImage_label(form.get("pdimagelabel"));
				productDetails.setUrl_path(form.get("pdurlpath"));
				productDetails.setWeight(Double.parseDouble(form.get("pdweight")));
				productDetails.setPrice(Double.parseDouble(form.get("pdprice")));
				productDetails.setSpecial_price(Double.parseDouble(form.get("pdspclprice")));
				productDetails.setDescription(form.get("pddesc"));
				productDetails.setShort_description(form.get("pdshortdesc"));
				productDetails.setMeta_keyword(form.get("pdmetakeywords"));
				productDetails.setSpecial_from_date(form.get("pdspclfromdate"));
				
				productDetails.update();
			}  else {
				ProductDetails productDetails = new ProductDetails();
				productDetails.product_name = form.get("pdproname");
				productDetails.sku = form.get("pdsku");
				productDetails.category_ids = form.get("pdcategories");
				productDetails.category_ids = productDetails.category_ids.replace("[", "");
				productDetails.category_ids = productDetails.category_ids.replace("]", "");
				productDetails.category_ids = productDetails.category_ids.replace("\"", "");
				if(productDetails.category_ids.length() == 0) {
					throw new RuntimeException();
				}
				productDetails.name = form.get("pdproname");//form.get("pdname");
				
				productDetails.meta_title = form.get("pdmetatitle");
				productDetails.meta_description = form.get("pdmetadesc");
				productDetails.image = form.get("pdimages");
				productDetails.small_image = form.get("pdimages");
				productDetails.thumbnail = form.get("pdthumbnail");
				productDetails.rotator_image = form.get("pdrotatorimage");
				productDetails.image_label = form.get("pdproname");//form.get("pdimagelabel");
				productDetails.url_path = form.get("pdproname");//form.get("pdurlpath");
				productDetails.url_path = "/"+productDetails.url_path.trim().replace(" ", "-").toLowerCase();
				productDetails.url_key = productDetails.url_path;//form.get("pdurlkey");
				
				try {
					productDetails.weight = Double.parseDouble(form.get("pdweight"));
				}catch (Exception e) {
					productDetails.weight = 0.0;
				}
				
				productDetails.price = Double.parseDouble(form.get("pdprice"));
				try {
					productDetails.special_price = Double.parseDouble(form.get("pdspclprice"));
				}catch (Exception e) {
					productDetails.special_price = Double.parseDouble(form.get("pdprice"));
				}
				productDetails.description = form.get("pddesc");
				productDetails.short_description = form.get("pdshortdesc");
				productDetails.meta_keyword = form.get("pdmetakeywords");
				productDetails.special_from_date = form.get("pdspclfromdate");
				productDetails.save();
		        scrappedData.setProductDetails(productDetails);
		        scrappedData.setStatus(1);
		        scrappedData.update();
			}
		
    	return ok();
    }
    public static Result getValues() {
    	
		DynamicForm form = DynamicForm.form().bindFromRequest();
        ScrappedData scrappedData = ScrappedData.findById(Long.parseLong(form.get("Id")));
		ProductDetails productDetails = ProductDetails.findById(scrappedData.getProductDetails().id);
		System.out.println("In if"+scrappedData.getProductDetails().id);
    	
    	return ok(Json.toJson(productDetails));
    }
    
    public static Result delete() {
    	
		DynamicForm form = DynamicForm.form().bindFromRequest();
		
        ScrappedData scrappedData = ScrappedData.findById(Long.parseLong(form.get("Id")));
        scrappedData.setStatus(2);
        scrappedData.update();
    	return ok();
    }
    
    public static Result LoadDataBySiteName() {
    	DynamicForm requestData = Form.form().bindFromRequest();
    	String site_name = requestData.get("site");
    	String status = requestData.get("approvedstatus");
    	int flag = 0;
    	if(status.equals("1")) {
        	flag = 1;
        }
        if(status.equals("0")) {
        	flag = 0;
        }
        
        if(status.equals("2")) {
        	flag = 2;
        }
        
    	
    	List<ScrappedData> datas = Ebean.find(ScrappedData.class).where().eq("site", site_name).eq("status", flag).findList();
    	
    	List<SqlRow> siteNames = Ebean.createSqlQuery("Select distinct site, date from scrapped_data").findList();
    	List<String> sname = new ArrayList<String>();
    	List<String> dateFilter = new ArrayList<String>();
    	for(SqlRow site :siteNames){
    		sname.add(site.getString("site"));
    	}
    	
    	return ok(index.render(datas, sname, dateFilter));
    	
    }
    public static Result generateCSVFile() {
    	List<ProductDetails> postLists = ProductDetails.find.where().eq("generatedcsv", null).findList();
    	CellProcessor[] processors = new CellProcessor[] {
    	new NotNull(), // name
    	new NotNull(), // message
    	new NotNull(), // picture
    	new NotNull(), // link
    	new NotNull(), // icon

    	new NotNull(), // created_Date
    	new NotNull(), // created_time
    	new NotNull(), // updated_Date
    	new NotNull(), // updated_time

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(),

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 

    	new NotNull(),
    	new NotNull(),
    	new NotNull(),
    	new NotNull(), 
    	new NotNull()


    	};

    	ICsvBeanWriter beanWriter = null;
    	File file = new File(root);
    	if(!file.exists()){
    	file.mkdir();
    	}
    	;
    	long currentTime = System.currentTimeMillis();
    	Date date = new Date(currentTime);
    	DateFormat df1 = new SimpleDateFormat("yyyyMMdd-HHmm");
    	String dateName = df1.format(date);

    	try {
    	beanWriter = new CsvBeanWriter(new FileWriter(root + File.separator + "scraper_" + dateName + ".csv"),
    	CsvPreference.STANDARD_PREFERENCE);
    	String[] header = {"store","websites","attribute_set","type","category_ids",
    	"sku", "has_options", "name", "url_key",
    	"country_of_manufacture", "msrp_enabled", "msrp_display_actual_price_type", "meta_title",
    	"meta_description", "image", "small_image", "thumbnail",
    	"rotator_image", "custom_design", "page_layout", "options_container",
    	"gift_message_available", "image_label", "small_image_label", "thumbnail_label",
    	"url_path", "weight", "price", "special_price",
    	"msrp", "status", "visibility", "featured",
    	"tax_class_id", "is_recurring", "description", "short_description",
    	"meta_keyword", "custom_layout_update", "news_from_date", "news_to_date",
    	"special_from_date", "special_to_date", "custom_design_from", "custom_design_to",
    	"qty", "min_qty", "use_config_min_qty", "is_qty_decimal",
    	"backorders", "use_config_backorders", "min_sale_qty", "use_config_min_sale_qty",
    	"max_sale_qty", "use_config_max_sale_qty", "is_in_stock", "low_stock_date",
    	"notify_stock_qty", "use_config_notify_stock_qty", "manage_stock", "use_config_manage_stock",
    	"stock_status_changed_auto", "use_config_qty_increments", "qty_increments", "use_config_enable_qty_inc", "enable_qty_increments",
    	"is_decimal_divided", "stock_status_changed_automatically", "use_config_enable_qty_increments", "product_name",
    	"store_id", "product_type_id", "product_status_changed", "product_changed_websites"};
    	beanWriter.writeHeader(header);
    	for (ProductDetails result : postLists) {
    	beanWriter.write(result, header, processors);
    	}
    	if(beanWriter != null){
    	beanWriter.flush();
    	beanWriter.close();
    	}
    	} catch (IOException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	}
    	
    	for (ProductDetails result : postLists) {
    		result.setGeneratedcsv(dateName);
        }
    	Ebean.save(postLists);


    	response().setContentType("application/octet-stream");
    	response().setHeader("Content-Disposition",
    	"attachment;filename=scraper_" + dateName +".csv");
    	response().setCookie("fileDownload", "true");
    	return ok(new File(root + File.separator + "scraper_" + dateName + ".csv"));
    }

}
