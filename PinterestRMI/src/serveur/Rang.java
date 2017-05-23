package serveur;

public enum Rang {
	NOUVEAU, INTERMITTENT, FIDELE, HABITUE, INITIE, EXPERT;
	
	public Rang changer() {
		int r = (int)(Math.random()*Rang.values().length);
		Rang resultat = Rang.values()[r];
		while (resultat.equals(this)) {
			r = (int)(Math.random()*Rang.values().length);
			resultat = Rang.values()[r];
		}
		return resultat;
	}
}
