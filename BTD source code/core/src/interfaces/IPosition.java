package interfaces;
/**
 * Brukere av dette interfacet skal kunne lage et posisjons-objekt. 
 * 
 */

public interface IPosition {

	/**
	 * @param x, x-posisjonen som skal settes.
	 */
	public void setX(int x);
	
	/**
	 * @param y, y-posisjonen som skal settes
	 */
	public void setY(int y);
	
	/**
	 * @return x - posisjon
	 */
	public int getX();
	
	/**
	 * @return y - posisjon
	 */
	public int getY();
	
	/**
	 * @param x endring i x-posisjon
	 * @param y endring i y-posisjon
	 * 
	 * @return ny posisjon
	 */
	public IPosition changePosition (int x, int y);
	
}
