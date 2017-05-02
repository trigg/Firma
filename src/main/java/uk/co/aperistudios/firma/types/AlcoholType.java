package uk.co.aperistudios.firma.types;

public enum AlcoholType {
	Whiskey(0xffD29062,"Whiskey"), Rye(0xffCC8E69,"Rye"), Vodka(0xddffffff,"Vodka"), Beer(0xffd29062,"Beer"), Ale(0xff774000,"Ale"), Bourbon("Bourbon"), Cider(0xff776a00,"Cider"), Brandy("Brandy"), Gin(0xddffffff,"Gin"), Rum(0xddffffff,"Rum"), Tequila(0xddffffff,"Tequila"), Liquour(0xffff3000,"Liquour"), Sake("Sake"), Mead("Mead"), PanGalacticGargleblaster(0xff22ff00,"PanGalacticGargleblaster"), Absinthe(0xff00ff00,"Absinthe");
	
	private int col;
	private String name;

	AlcoholType(int col, String name){
		this.col = col;
		this.name = name;
	}
	
	AlcoholType(String name){
		this(0xffd29062, name);
	}
	
	public int getCol() {
		return col;
	}

	public String getName() {
		return name;
	}

}
