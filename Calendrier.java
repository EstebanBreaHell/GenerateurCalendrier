import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;

public class Calendrier
{
	private int annee;
	
	private Mois[] listMois;
	
	private String[] nomMois = new String[]{"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
	
	private int[] numJour;
	
	private int numJourDebutAnnee;
	private Calendar gregorianCalendar;

	/*
	 * Constructeur de la classe Calendrier qui prend en parametre l'annee a afficher
	 * @param int annee : l'annee a afficher
	 * @return Calendrier 
	 */
	Calendrier(int annee)
	{
		this.annee = annee;
		
		this.listMois = new Mois[12];
		
		this.gregorianCalendar = new GregorianCalendar(this.annee,0,0);
		if (this.gregorianCalendar.get(Calendar.DAY_OF_YEAR) == 365)
		{
			this.numJour = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		}
		else
		{
			this.numJour = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
		}

		//trouvee sur wikipedia fonction pour calculer les jours de paques
		int n,c,u,s,t,p,q,e,b,d,L,h,m,j;
		n = this.annee % 19;
		c = this.annee / 100;
		u = this.annee %  100;
		s = c / 4;
		t = c % 4;
		p = (c+8)/25;
		q = (c-p+1)/3;
		e = (19*n+c-s-q+15)%30;
		b = u/4;
		d = u%4;
		L = (2*t+2*b-e-d+32)%7;
		h = (n+11*e+22*L)/451;
		m = (e+L-7*h+114)/31;
		j = (e+L-7*h+114)%31;


		this.numJourDebutAnnee = this.gregorianCalendar.get(Calendar.DAY_OF_WEEK);
		int tmpNbJours =this.numJourDebutAnnee -1; 
		if(tmpNbJours == -1)
			tmpNbJours = 6;
		for (int i = 0 ; i < 12 ; i++)
		{
			this.listMois[i] = new Mois(i,this.numJour[i],tmpNbJours,this.annee);
			tmpNbJours = tmpNbJours + this.numJour[i];

		}

		this.listMois[m-1].getJour(j).setFerie(true);
		int ascension = 40;
		ascension = ascension -(this.numJour[m-1] - j+1);
		if(ascension > this.numJour[m])
		{
			ascension = ascension - this.numJour[m];
			this.listMois[m+1].getJour(ascension).setFerie(true);
		}
		else
		{
			this.listMois[m].getJour(ascension).setFerie(true);
		}

		int paques = 50;
		paques = paques -(this.numJour[m-1] - j+1);
		if(paques > this.numJour[m])
		{
			paques = paques - this.numJour[m];
			this.listMois[m+1].getJour(paques).setFerie(true);
		}
		else
		{
			this.listMois[m].getJour(paques).setFerie(true);
		}

		

		
		
	}
	
	/*
	 * Methode qui Renvoie le mois de l'annee demandé
	 * @param int mois : le mois demandé (0 pour janvier, 1 pour fevrier, etc...)
	 * @return Mois : le mois demandé
	 */
	public Mois getMois(int nb)
	{
		return this.listMois[nb];
	}
	
	/*
	 * Methode qui Renvoie le mois de l'annee demandé
	 * @return String affiche les mois de l'annee dans le terminal 
	 */
	public String toString()
	{
		String sRet = "";
		for (int i = 0 ; i < 12 ; i++)
		{
			sRet+=this.listMois[i].toString();
			sRet+="\n";
			sRet+="\n";
			
			
		}
		
		return sRet;
	}
	
	/*
	 * Crée les fichier Html dans le dossier page
	 * Fichier crée : Annee.html, Janvier.html, Fevrier.html, Mars.html, Avril.html, Mai.html, Juin.html, Juillet.html, Aout.html, Septembre.html, Octobre.html, Novembre.html, Decembre.html
	 * @return void
	 */
	public void html()
	{
		
		
		try
		{
			//On va faire la page qui va contenir chaque annee
			File fAnnee = new File("page/Annee.html");
			fAnnee.createNewFile();
			

			PrintWriter pwAnnee = new PrintWriter(new FileOutputStream("page/Annee.html"));
			pwAnnee.println ( "<!DOCTYPE html>\n" );
			pwAnnee.println ( "<html>\n" );
			pwAnnee.println ( "<head>\n" );
			pwAnnee.println ( "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
			pwAnnee.println ( "<meta charset=\"utf-8\">" );
			pwAnnee.println ( "<title>Calendrier</title>\n" );
			pwAnnee.println ( "</head>\n" );
			pwAnnee.println ( "<body>\n" );

			for(int i = 0 ; i < 12 ; i++)
			{

				pwAnnee.println("<h1><a href=\""+this.listMois[i].getNom()+".html\">"+this.listMois[i].getNom()+"</a></h1>");
				pwAnnee.println(this.listMois[i].html());
			}
			
			
			pwAnnee.println ( "</body>\n" );
			pwAnnee.println ( "</html>\n" );
			pwAnnee.close();




			//On cree les pages de chaque mois
			for (int i = 0 ; i < 12 ; i++)
			{
				File f = new File("page/"+this.nomMois[i] + ".html");
				f.createNewFile();
			}


			//On rempli chaque mois par le code html
			for (int i = 0 ; i < 12 ; i++)
			{
				
				PrintWriter pw = new PrintWriter( new FileOutputStream("page/"+this.nomMois[i] + ".html") );

				pw.println ( "<!DOCTYPE html>\n" );
				pw.println ( "<html>\n" );
				pw.println ( "<head>\n" );
				pw.println ( "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
				pw.println ( "<title>"+ this.nomMois[i] +"</title>\n" );
				pw.println ( "</head>\n" );
				pw.println ( "<body>\n" );

				if(i == 0)
				{
					pw.println("<a href=\"Decembre.html\" class=\"button_gauche\">Decembre</a>");
					pw.println("<a href=\"Fevrier.html\" class=\"button_droit\">Fevrier</a>");
			
				}
				if(i == 11)
				{
					pw.println("<a href=\"Novembre.html\" class=\"button_gauche\">Novembre</a>");
					pw.println("<a href=\"Janvier.html\" class=\"button_droit\">Janvier</a>");
				}

				if(i >0 && i <11)
				{
					pw.println("<a href=\"" + this.nomMois[i-1] + ".html\" class=\"button_gauche\">" + this.nomMois[i-1] + "</a>");
					pw.println("<a href=\"" + this.nomMois[i+1] + ".html\" class=\"button_droit\">" + this.nomMois[i+1] + "</a>");
				}
				pw.println("<h1>" + this.listMois[i].getNom() + "   " +  this.annee+ "</h1>\n");
				pw.println(this.listMois[i].html());
			
			
			
			

				pw.println ( "</body>\n" );
				pw.println ( "</html>\n" );
				pw.close();
			}
		}
		catch (Exception e){ e.printStackTrace(); }
	}
	
	public static void main(String args[])
	{
		if (args.length != 2)
		{
			System.out.println("Erreur : Il faut 2 arguments");
			System.out.println("1er argument : html ou console");
			System.out.println("2eme argument : L'annee");
			System.out.println("Exemple : java Calendrier html 2015");
			return;
		}

		try{
			Calendrier t;
			t = new Calendrier(Integer.parseInt(args[1]));
			if(args[0].toLowerCase().equals("html"))
			{
				t.html();
				System.out.println("Calendrier cree avec succes");
				System.out.println("Pour le voir, ouvrez le fichier page/Annee.html");
			}
			else if(args[0].toLowerCase().equals("console"))
			{
				System.out.println(t.toString());
			}
			else
			{
				System.out.println("Erreur : 1er argument doit etre html ou console");
			}
		}
		catch(Exception e)
		{
			System.out.println("Erreur : 2eme argument doit etre un nombre");
		}
	}
}
