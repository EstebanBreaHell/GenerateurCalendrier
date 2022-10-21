public class Jour 
{

	private int num;
	private int numNom;
	private Mois mois;
	private boolean ferie;
	private String[] listNom = new String[]{"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
	
	/*
	 * Constructeur de la classe Jour
	 * @param int num : le numero du jour
	 * @param int numNom : le numero du jour dans la semaine (0 pour lundi, 1 pour mardi, etc...)
	 * @param Mois mois : le mois auquel appartient le jour
	 */
	Jour(int num , int numNom, Mois mois)
	{
		
		this.num = num;
		this.numNom = numNom;
		this.mois = mois;
		this.ferie = false;
	}
	
	/*
	 * Methode qui Renvoie le numero du jour
	 * @return int : le numero du jour
	 */
	public int getNum(){ return this.num;}
	
	/*
	 * Methode qui Renvoie le nom du jour
	 * @return String : le nom du jour
	 */
	public String getNom(){ return this.listNom[this.numNom];}
	
	/*
	 * Methode qui Renvoie le numero mois auquel appartient le jour
	 * @return int : le numero du mois auquel appartient le jour (0 pour janvier, 1 pour fevrier, etc...)
	 */
	public int getNumNom(){ return this.numNom;}
	
	/*
	 * Methode qui Renvoie le mois auquel appartient le jour
	 * @return Mois : le mois auquel appartient le jour
	 */
	public Mois getMois(){ return this.mois;}

	/*
	 * Methode qui Renvoie si le jour est un jour ferie
	 * @return boolean : true si le jour est un jour ferie, false sinon
	 */
	public boolean isFerie(){ return this.ferie;}

	/*
	 * Methode qui Renvoie si le jour est un jour ferie
	 * @param boolean ferie : true si le jour est un jour ferie, false sinon
	 * @return void
	 */
	public void setFerie(boolean ferie){ this.ferie = ferie;}
	
	/*
	 * Methode qui Renvoie le jour sous forme de chaine de caractere
	 * @return String : le jour sous forme de chaine de caractere
	 */
	public String toString()
	{
		String sRet="";
		sRet+=this.listNom[this.numNom] + " " + this.num + " " + this.mois.getNom();
		
		return sRet;
	}
}
