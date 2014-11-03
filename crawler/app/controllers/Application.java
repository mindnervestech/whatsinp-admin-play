package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

public class Application extends Controller {

    public static Result index() {
    	List<ScrappedData> datas = Ebean.find(ScrappedData.class).findList();
    	List<SqlRow> siteNames = Ebean.createSqlQuery("Select distinct site, date from scrapped_data").findList();
    	List<String> sname = new ArrayList<String>();
    	List<SqlRow> _date = Ebean.createSqlQuery("Select distinct date from scrapped_data").findList();
    	List<String> dateFilter = new ArrayList<String>();
    	for(SqlRow site :siteNames){
    		sname.add(site.getString("site"));
    	}
    	for(SqlRow site :_date){
    		//dateFilter.add(site.getDate("date").toString());
    	}
    	System.out.println(sname);
        return ok(index.render(datas, sname, dateFilter) );
    }
    
    public static Result saveKeyValue() {
    	
		DynamicForm form = DynamicForm.form().bindFromRequest();
		ScrappedData scrappedData = ScrappedData.findById(Long.parseLong(form.get("Id")));
		System.out.println("Before if");
		try
		{
			if(!scrappedData.getProductDetails().toString().trim().equals("")) 
			{
				System.out.println("In if"+scrappedData.getProductDetails().id);
				ProductDetails productDetails = ProductDetails.findById(scrappedData.getProductDetails().id);
				productDetails.setProductName(form.get("pdname"));
				productDetails.setSku(form.get("pdsku"));
				productDetails.setDescription(form.get("pddesc"));
				productDetails.setWeight(Double.parseDouble(form.get("pdweight")));
				productDetails.setStatus(form.get("pdselect"));
				productDetails.setVisibility(form.get("pdvisible"));
				productDetails.setShortDescription(form.get("pdshortdesc"));
				productDetails.setSearch(form.get("pdsearch"));
				productDetails.setQuantity(Double.parseDouble(form.get("pdquantity")));
				productDetails.setPrice(Double.parseDouble(form.get("pdprice")));
				productDetails.setMetaDescription(form.get("pdmetadesc"));
				productDetails.setTaxClass(form.get("pdtaxclass"));
				productDetails.setCategories(form.get("pdcategories"));
				productDetails.setMetaTitle(form.get("pdmetatitle"));
				productDetails.setMetaKeywords(form.get("pdmetakeywords"));
				productDetails.setImages(form.get("pdimages"));
				productDetails.update();
			} 
		} catch(NullPointerException e) {
			System.out.println("In Else");
			ProductDetails productDetails = new ProductDetails();
			productDetails.productName = form.get("pdname");
			productDetails.sku = form.get("pdsku");
			productDetails.description = form.get("pddesc");
			productDetails.weight = Double.parseDouble(form.get("pdweight"));
			productDetails.status = form.get("pdselect");
			productDetails.visibility = form.get("pdvisible");
			productDetails.shortDescription = form.get("pdshortdesc");
			productDetails.search = form.get("pdsearch");
			productDetails.quantity = Double.parseDouble(form.get("pdquantity"));
			productDetails.price = Double.parseDouble(form.get("pdprice"));
			productDetails.metaDescription = form.get("pdmetadesc");
			productDetails.taxClass = form.get("pdtaxclass");
			productDetails.categories = form.get("pdcategories");
			productDetails.metaTitle = form.get("pdmetatitle");
			productDetails.metaKeywords = form.get("pdmetakeywords");
			productDetails.images = form.get("pdimages");
			productDetails.save();
	        
	        scrappedData.setProductDetails(productDetails);
	        //System.out.println(form.get("pdname"));
	        //scrappedData.setKeyValue(form.get("values"));
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
    public static Result changeStatus() {
    	
		DynamicForm form = DynamicForm.form().bindFromRequest();
		
        ScrappedData scrappedData = ScrappedData.findById(Long.parseLong(form.get("Id")));
        if(form.get("status").equals("true")) {
        	scrappedData.setStatus(true);
        }
        if(form.get("status").equals("false")) {
        	scrappedData.setStatus(false);
        }
        scrappedData.update();
    	return ok();
    }
    
    public static Result LoadDataBySiteName() {
    	DynamicForm requestData = Form.form().bindFromRequest();
    	String site_name = requestData.get("site");
    	String date = requestData.get("dateFilter");
    	String status = requestData.get("approvedstatus");
    	boolean flag = false;
    	if(status.equals("1")) {
        	flag = true;
        }
        if(status.equals("0")) {
        	flag = false;
        }
        
    	System.out.println("sitename = "+date);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date selectedDate = null;
    	try {
    		selectedDate = sdf.parse(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	List<ScrappedData> datas = Ebean.find(ScrappedData.class).where().eq("site", site_name).eq("status", flag).findList();
    	
    	List<SqlRow> siteNames = Ebean.createSqlQuery("Select distinct site, date from scrapped_data").findList();
    	List<String> sname = new ArrayList<String>();
    	List<SqlRow> _date = Ebean.createSqlQuery("Select distinct date from scrapped_data").findList();
    	List<String> dateFilter = new ArrayList<String>();
    	for(SqlRow site :siteNames){
    		sname.add(site.getString("site"));
    	}
    	for(SqlRow site :_date){
    		//dateFilter.add(site.getDate("date").toString());
    	}
    	
    	return ok(index.render(datas, sname, dateFilter));
    	
    }
    

}
