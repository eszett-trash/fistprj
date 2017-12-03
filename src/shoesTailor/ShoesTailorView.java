package shoesTailor;

public class ShoesTailorView {

	ShoesTailorDB sdb = ShoesTailorDB.getInstance();

	public void start()
	{

		for (int i = 0; i < sdb.size_stockList(); i++) {
			System.out.println(sdb.view_stockList(i));
		}
	}
}
