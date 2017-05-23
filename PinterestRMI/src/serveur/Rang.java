package serveur;

public enum Rang {
	NOUVEAU, INTERMITTENT, FIDELE, HABITUE, INITIE, EXPERT;
	
	public String nom() {
		switch (this) {
		case NOUVEAU:
			return "nouveau";
		case INTERMITTENT:
			return "intermittent";
		case FIDELE:
			return "fidele";
		case HABITUE:
			return "habitue";
		case INITIE:
			return "initie";
		case EXPERT:
			return "expert";
		default:
			return "inconnu";
		}
	}
	
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
