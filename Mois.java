public class Mois
{
	
	private int num;
	private int nbJours;
	private Jour[] listJour;
	private int annee;
	private String[] listNomJour = new String[]{"Lun","Mar","Mer","Jeu","Ven","Sam","Dim"};
	private String[] nomMoisStr = new String[]{"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};

	/*
	 * Constructeur de la classe Mois
	 * @param int num : le numero du mois (0 pour janvier, 1 pour fevrier, etc...)
	 * @param int nbJours : le nombre de jours du mois
	 * @param int numNomJourDebut : le numero du jour de la semaine du premier jour du mois (0 pour lundi, 1 pour mardi, etc...)
	 * @param int annee : l'annee du mois
	 * 
	 * @return Mois : le mois cree
	 */
	Mois(int num, int nbJours , int numNomJourDebut, int annee)
	{
		this.annee =annee;
		this.num = num;
		this.nbJours = nbJours;
		this.listJour = new Jour[this.nbJours];
		
		for (int i = 0;  i < this.nbJours ; i++)
		{

			this.listJour[i] = new Jour(i+1,(numNomJourDebut + i)%7,this);

			//1/1 1/5 8/5 14/7 15/8 1/11 11/11 25/12

			if((i+1 == 1 && this.num+1 == 1)  ||
			    (i+1 == 1 && this.num+1 == 5) ||
			    (i+1 == 8 && this.num+1 == 5) ||
			    (i+1 == 14 && this.num+1 == 7) ||
			    (i+1 == 15 && this.num+1 == 8) ||
			    (i+1 == 1 && this.num+1 == 11) ||
			    (i+1 == 11 && this.num+1 == 11) ||
			    (i+1 == 25 && this.num+1 == 12))
			{
				this.listJour[i].setFerie(true);
			}

		}
		
	}

	/*
	 * Methode qui Renvoie le nom du mois
	 * @return String Jour : le nom du mois
	 */
	public String getNom()
	{
		return this.nomMoisStr[this.num];
	}
	
	/*
	 * Methode qui Renvoie le jour demandé
	 * @param int nb : le numero du jour
	 * @return Jour  : le jour demandé
	 */
	public Jour getJour(int nb)
	{
		return this.listJour[nb];
	}

	/*
	 * Methode renvoie le code html du mois 
	 * @return String : le code html du mois
	 */
	public String html()
	{
		String sRet = "";

		sRet+= "<table>\n";

		sRet+= "<tr>\n";
		for(int i = 0 ; i < 7 ; i++)
		{
			sRet+="<td><div class=\"nom\">" +this.listNomJour[i] +"</div></td>\n";
		}

		sRet+= "</tr>\n";
		sRet+= " <tr>\n";

		for(int i =6-this.listJour[0].getNumNom();i <6 ; i++)
 		{
 			
 			sRet+="<td><div class=\"vide\"> </div></td>\n";
 		}

		for(int i = 0 ; i < this.nbJours ; i++)
		{
			if(this.listJour[i].getNumNom()%7 == 0) 
			{
				sRet+= "</tr>\n";
				sRet+= " <tr>\n";
			}
			//this.listJour[i].getNum()
			if(this.listJour[i].isFerie())
			{
				sRet+= "<td><div class=\"ferie\">" + this.listJour[i].getNum() + "</div></td>\n";
			}
			else
			{
				sRet+= "<td><div class=\"content\">" + this.listJour[i].getNum() + "</div></td>\n";
			}
			//sRet += "<td><div class=\"content\">"+this.listJour[i].getNum()+"</div></td>\n";
			
		}
		


		sRet+= "</table>\n";
		return sRet;
	}
	
	/*
	 * Methode qui renvoie le mois sous forme de chaine de caractere
	 * @return String : le mois sous forme de chaine de caractere
	 */
	public String toString()
	{
 		
 		String sRet = "";
 		sRet += this.nomMoisStr[this.num] + " " + this.annee;
 		sRet +="\n";
 		System.out.println(this.listJour[0].getNumNom());
 		for(int i =6-this.listJour[0].getNumNom();i <6 ; i++)
 		{
 			
 			sRet+=String.format("%4s","");
 		}
		for(int i = 0 ; i < this.nbJours ; i++)
		{
			if(this.listJour[i].getNumNom()%7 == 0) 
				sRet+="\n";
			sRet += String.format("%4d",this.listJour[i].getNum());
			
		}
		sRet+="\n";
		return sRet;
	}
}
