package com.mnt.scrapper.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Ebean;
import com.mnt.scrapper.ebean.Model;
@Entity
public class ScrappedData extends Model  {
	

	@Id
	public Long id;

	public String name;

	public String image;

	public String link;

	public String regularPrice;

	public String specialPrice;

	public String offers;

	public Date date;

	public String site;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


	public String getOffers() {
		return offers;
	}

	public void setOffers(String offers) {
		this.offers = offers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(String regularPrice) {
		this.regularPrice = regularPrice;
	}

	public String getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(String specialPrice) {
		this.specialPrice = specialPrice;
	}

	public static List<ScrappedData> bySites(String site) {
		return Ebean.find(ScrappedData.class).where().eq("site", site).findList();
	}
	
	/*@Override
	public int hashCode() {
		return (site + name).hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		return name.equals(((ScrappedData)object).name) && site.equals(((ScrappedData)object).site); 
	}*/

}
