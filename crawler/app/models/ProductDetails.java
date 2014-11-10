package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class ProductDetails extends Model {
	@Id
	public Long id;

	public String store = "admin";

	public String websites = "base";

	public String attribute_set = "Default";

	public String type = "simple";

	public String category_ids;

	public String sku;

	public int has_options = 0;

	public String name;

	public String url_key;

	public String country_of_manufacture = "";

	public String msrp_enabled = "Use config";

	public String msrp_display_actual_price_type = "Use config";

	public String meta_title;

	public String meta_description;

	public String image;

	public String small_image;

	public String thumbnail;

	public String rotator_image;

	public String custom_design = "";

	public String page_layout = "No layout updates";

	public String options_container = "Product Info Column";

	public String gift_message_available = "No";

	public String image_label;

	public String small_image_label = "";

	public String thumbnail_label = "";

	public String url_path;

	public Double weight;

	public Double price;

	public Double special_price;

	public String msrp = "";

	public String status = "Enabled";

	public String visibility = "Catalog, Search";

	public String featured = "Yes";

	public String tax_class_id = "Shipping";

	public String is_recurring = "No";

	public String description;

	public String short_description;

	public String meta_keyword;

	public String custom_layout_update = "";

	public String news_from_date = "";

	public String news_to_date = "";

	public String special_from_date;

	public String special_to_date = "";

	public String custom_design_from = "";

	public String custom_design_to = "";

	public String qty = "999999";

	public String min_qty = "0";

	public String use_config_min_qty = "1";

	public String is_qty_decimal = "0";

	public String backorders = "0";

	public String use_config_backorders = "1";

	public String min_sale_qty = "1";

	public String use_config_min_sale_qty = "1";

	public String max_sale_qty = "0";

	public String use_config_max_sale_qty = "1";

	public String is_in_stock = "1";

	public String low_stock_date = "";

	public String notify_stock_qty = "";

	public String use_config_notify_stock_qty = "1";

	public String manage_stock = "0";

	public String use_config_manage_stock = "1";

	public String stock_status_changed_auto = "0";

	public String use_config_qty_increments = "1";

	public String qty_increments = "0";

	public String use_config_enable_qty_inc = "1";

	public String enable_qty_increments = "0";

	public String is_decimal_divided = "0";

	public String stock_status_changed_automatically = "0";

	public String use_config_enable_qty_increments = "1";

	public String product_name;

	public String store_id = "0";

	public String product_type_id = "simple";

	public String product_status_changed = "";

	public String product_changed_websites = "";

	public static Finder<Long, ProductDetails> find = new Finder<Long, ProductDetails>(
			Long.class, ProductDetails.class);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getWebsites() {
		return websites;
	}

	public void setWebsites(String websites) {
		this.websites = websites;
	}

	public String getAttribute_set() {
		return attribute_set;
	}

	public void setAttribute_set(String attribute_set) {
		this.attribute_set = attribute_set;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory_ids() {
		return category_ids;
	}

	public void setCategory_ids(String category_ids) {
		this.category_ids = category_ids;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getHas_options() {
		return has_options;
	}

	public void setHas_options(int has_options) {
		this.has_options = has_options;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl_key() {
		return url_key;
	}

	public void setUrl_key(String url_key) {
		this.url_key = url_key;
	}

	public String getCountry_of_manufacture() {
		return country_of_manufacture;
	}

	public void setCountry_of_manufacture(String country_of_manufacture) {
		this.country_of_manufacture = country_of_manufacture;
	}

	public String getMsrp_enabled() {
		return msrp_enabled;
	}

	public void setMsrp_enabled(String msrp_enabled) {
		this.msrp_enabled = msrp_enabled;
	}

	public String getMsrp_display_actual_price_type() {
		return msrp_display_actual_price_type;
	}

	public void setMsrp_display_actual_price_type(
			String msrp_display_actual_price_type) {
		this.msrp_display_actual_price_type = msrp_display_actual_price_type;
	}

	public String getMeta_title() {
		return meta_title;
	}

	public void setMeta_title(String meta_title) {
		this.meta_title = meta_title;
	}

	public String getMeta_description() {
		return meta_description;
	}

	public void setMeta_description(String meta_description) {
		this.meta_description = meta_description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSmall_image() {
		return small_image;
	}

	public void setSmall_image(String small_image) {
		this.small_image = small_image;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getRotator_image() {
		return rotator_image;
	}

	public void setRotator_image(String rotator_image) {
		this.rotator_image = rotator_image;
	}

	public String getCustom_design() {
		return custom_design;
	}

	public void setCustom_design(String custom_design) {
		this.custom_design = custom_design;
	}

	public String getPage_layout() {
		return page_layout;
	}

	public void setPage_layout(String page_layout) {
		this.page_layout = page_layout;
	}

	public String getOptions_container() {
		return options_container;
	}

	public void setOptions_container(String options_container) {
		this.options_container = options_container;
	}

	public String getGift_message_available() {
		return gift_message_available;
	}

	public void setGift_message_available(String gift_message_available) {
		this.gift_message_available = gift_message_available;
	}

	public String getImage_label() {
		return image_label;
	}

	public void setImage_label(String image_label) {
		this.image_label = image_label;
	}

	public String getSmall_image_label() {
		return small_image_label;
	}

	public void setSmall_image_label(String small_image_label) {
		this.small_image_label = small_image_label;
	}

	public String getThumbnail_label() {
		return thumbnail_label;
	}

	public void setThumbnail_label(String thumbnail_label) {
		this.thumbnail_label = thumbnail_label;
	}

	public String getUrl_path() {
		return url_path;
	}

	public void setUrl_path(String url_path) {
		this.url_path = url_path;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSpecial_price() {
		return special_price;
	}

	public void setSpecial_price(Double special_price) {
		this.special_price = special_price;
	}

	public String getMsrp() {
		return msrp;
	}

	public void setMsrp(String msrp) {
		this.msrp = msrp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getFeatured() {
		return featured;
	}

	public void setFeatured(String featured) {
		this.featured = featured;
	}

	public String getTax_class_id() {
		return tax_class_id;
	}

	public void setTax_class_id(String tax_class_id) {
		this.tax_class_id = tax_class_id;
	}

	public String getIs_recurring() {
		return is_recurring;
	}

	public void setIs_recurring(String is_recurring) {
		this.is_recurring = is_recurring;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getMeta_keyword() {
		return meta_keyword;
	}

	public void setMeta_keyword(String meta_keyword) {
		this.meta_keyword = meta_keyword;
	}

	public String getCustom_layout_update() {
		return custom_layout_update;
	}

	public void setCustom_layout_update(String custom_layout_update) {
		this.custom_layout_update = custom_layout_update;
	}

	public String getNews_from_date() {
		return news_from_date;
	}

	public void setNews_from_date(String news_from_date) {
		this.news_from_date = news_from_date;
	}

	public String getNews_to_date() {
		return news_to_date;
	}

	public void setNews_to_date(String news_to_date) {
		this.news_to_date = news_to_date;
	}

	public String getSpecial_from_date() {
		return special_from_date;
	}

	public void setSpecial_from_date(String special_from_date) {
		this.special_from_date = special_from_date;
	}

	public String getSpecial_to_date() {
		return special_to_date;
	}

	public void setSpecial_to_date(String special_to_date) {
		this.special_to_date = special_to_date;
	}

	public String getCustom_design_from() {
		return custom_design_from;
	}

	public void setCustom_design_from(String custom_design_from) {
		this.custom_design_from = custom_design_from;
	}

	public String getCustom_design_to() {
		return custom_design_to;
	}

	public void setCustom_design_to(String custom_design_to) {
		this.custom_design_to = custom_design_to;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getMin_qty() {
		return min_qty;
	}

	public void setMin_qty(String min_qty) {
		this.min_qty = min_qty;
	}

	public String getUse_config_min_qty() {
		return use_config_min_qty;
	}

	public void setUse_config_min_qty(String use_config_min_qty) {
		this.use_config_min_qty = use_config_min_qty;
	}

	public String getIs_qty_decimal() {
		return is_qty_decimal;
	}

	public void setIs_qty_decimal(String is_qty_decimal) {
		this.is_qty_decimal = is_qty_decimal;
	}

	public String getBackorders() {
		return backorders;
	}

	public void setBackorders(String backorders) {
		this.backorders = backorders;
	}

	public String getUse_config_backorders() {
		return use_config_backorders;
	}

	public void setUse_config_backorders(String use_config_backorders) {
		this.use_config_backorders = use_config_backorders;
	}

	public String getMin_sale_qty() {
		return min_sale_qty;
	}

	public void setMin_sale_qty(String min_sale_qty) {
		this.min_sale_qty = min_sale_qty;
	}

	public String getUse_config_min_sale_qty() {
		return use_config_min_sale_qty;
	}

	public void setUse_config_min_sale_qty(String use_config_min_sale_qty) {
		this.use_config_min_sale_qty = use_config_min_sale_qty;
	}

	public String getMax_sale_qty() {
		return max_sale_qty;
	}

	public void setMax_sale_qty(String max_sale_qty) {
		this.max_sale_qty = max_sale_qty;
	}

	public String getUse_config_max_sale_qty() {
		return use_config_max_sale_qty;
	}

	public void setUse_config_max_sale_qty(String use_config_max_sale_qty) {
		this.use_config_max_sale_qty = use_config_max_sale_qty;
	}

	public String getIs_in_stock() {
		return is_in_stock;
	}

	public void setIs_in_stock(String is_in_stock) {
		this.is_in_stock = is_in_stock;
	}

	public String getLow_stock_date() {
		return low_stock_date;
	}

	public void setLow_stock_date(String low_stock_date) {
		this.low_stock_date = low_stock_date;
	}

	public String getNotify_stock_qty() {
		return notify_stock_qty;
	}

	public void setNotify_stock_qty(String notify_stock_qty) {
		this.notify_stock_qty = notify_stock_qty;
	}

	public String getUse_config_notify_stock_qty() {
		return use_config_notify_stock_qty;
	}

	public void setUse_config_notify_stock_qty(String use_config_notify_stock_qty) {
		this.use_config_notify_stock_qty = use_config_notify_stock_qty;
	}

	public String getManage_stock() {
		return manage_stock;
	}

	public void setManage_stock(String manage_stock) {
		this.manage_stock = manage_stock;
	}

	public String getUse_config_manage_stock() {
		return use_config_manage_stock;
	}

	public void setUse_config_manage_stock(String use_config_manage_stock) {
		this.use_config_manage_stock = use_config_manage_stock;
	}

	public String getStock_status_changed_auto() {
		return stock_status_changed_auto;
	}

	public void setStock_status_changed_auto(String stock_status_changed_auto) {
		this.stock_status_changed_auto = stock_status_changed_auto;
	}

	public String getUse_config_qty_increments() {
		return use_config_qty_increments;
	}

	public void setUse_config_qty_increments(String use_config_qty_increments) {
		this.use_config_qty_increments = use_config_qty_increments;
	}

	public String getQty_increments() {
		return qty_increments;
	}

	public void setQty_increments(String qty_increments) {
		this.qty_increments = qty_increments;
	}

	public String getUse_config_enable_qty_inc() {
		return use_config_enable_qty_inc;
	}

	public void setUse_config_enable_qty_inc(String use_config_enable_qty_inc) {
		this.use_config_enable_qty_inc = use_config_enable_qty_inc;
	}

	public String getEnable_qty_increments() {
		return enable_qty_increments;
	}

	public void setEnable_qty_increments(String enable_qty_increments) {
		this.enable_qty_increments = enable_qty_increments;
	}

	public String getIs_decimal_divided() {
		return is_decimal_divided;
	}

	public void setIs_decimal_divided(String is_decimal_divided) {
		this.is_decimal_divided = is_decimal_divided;
	}

	public String getStock_status_changed_automatically() {
		return stock_status_changed_automatically;
	}

	public void setStock_status_changed_automatically(
			String stock_status_changed_automatically) {
		this.stock_status_changed_automatically = stock_status_changed_automatically;
	}

	public String getUse_config_enable_qty_increments() {
		return use_config_enable_qty_increments;
	}

	public void setUse_config_enable_qty_increments(
			String use_config_enable_qty_increments) {
		this.use_config_enable_qty_increments = use_config_enable_qty_increments;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(String product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getProduct_status_changed() {
		return product_status_changed;
	}

	public void setProduct_status_changed(String product_status_changed) {
		this.product_status_changed = product_status_changed;
	}

	public String getProduct_changed_websites() {
		return product_changed_websites;
	}

	public void setProduct_changed_websites(String product_changed_websites) {
		this.product_changed_websites = product_changed_websites;
	}

	public static ProductDetails findById(Long id) {
		return find.byId(id);
	}

}
