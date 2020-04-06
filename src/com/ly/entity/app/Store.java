package com.ly.entity.app;


/**
 * 门店信息
 * @author Administrator
 *
 */
public class Store {

	private Integer id;
	private String store_name;//门店名称
	private String store_address;//门店地址（收货地址）
	private String store_address_code;//门店地址马卡鲁内部编码（冗余）
	private Double store_longitude;//门店经度
	private Double store_latitude;//门店纬度
	private String store_img;//门店照片（门头）
	private String store_shopowner_phone;//门店店长电话（收货人电话）
	private String store_shopowner_name;//门店店长姓名（收货人姓名）
	private String store_number;//门店编码（马卡鲁内部编码冗余）
	private String channel_code;//门店渠道编码
	private Integer vendor_id;//门店信息
	private Integer type;//门店类型(直营/现金)
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_address() {
		return store_address;
	}
	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}
	public String getStore_address_code() {
		return store_address_code;
	}
	public void setStore_address_code(String store_address_code) {
		this.store_address_code = store_address_code;
	}
	public Double getStore_longitude() {
		return store_longitude;
	}
	public void setStore_longitude(Double store_longitude) {
		this.store_longitude = store_longitude;
	}
	public Double getStore_latitude() {
		return store_latitude;
	}
	public void setStore_latitude(Double store_latitude) {
		this.store_latitude = store_latitude;
	}
	public String getStore_img() {
		return store_img;
	}
	public void setStore_img(String store_img) {
		this.store_img = store_img;
	}
	public String getStore_shopowner_phone() {
		return store_shopowner_phone;
	}
	public void setStore_shopowner_phone(String store_shopowner_phone) {
		this.store_shopowner_phone = store_shopowner_phone;
	}
	public String getStore_shopowner_name() {
		return store_shopowner_name;
	}
	public void setStore_shopowner_name(String store_shopowner_name) {
		this.store_shopowner_name = store_shopowner_name;
	}
	public String getStore_number() {
		return store_number;
	}
	public void setStore_number(String store_number) {
		this.store_number = store_number;
	}
	public String getChannel_code() {
		return channel_code;
	}
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
	public Integer getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(Integer vendor_id) {
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
