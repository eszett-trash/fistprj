package fistprj;

public class ShoesTailorView {

	ShoesTailorDB sdb = ShoesTailorDB.getInstance();

	public void start()
	{
		sdb.view_stock();
	}
}
