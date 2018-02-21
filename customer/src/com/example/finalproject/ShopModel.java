package com.example.finalproject;

public class ShopModel { //customer
		private Long id;
		private Integer numberGive; //店家發號碼發腳號
		private Integer numberCome; //店家出餐到幾號		
		
		public ShopModel(){
			
		}

		public ShopModel(Long id, Integer numberGive,Integer numberCome) {
			this.id = id;
			this.numberGive = numberGive;
			this.numberCome = numberCome;
		}

		public Integer getNumberGive() {
			return numberGive;
		}

		public void setNumberGive(Integer numberGive) {
			this.numberGive = numberGive;
		}
		
		public Integer getNumberCome() {
			return numberCome;
		}

		public void setNumberCome(Integer numberCome) {
			this.numberCome = numberCome;
		}
		
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
}
