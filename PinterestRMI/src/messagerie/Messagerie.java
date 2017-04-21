package messagerie;

import messagerie.Message;

/**
 * Cette classe gere l'echange de messages entre utilisateurs standards.
 * 
 * C'est elle qui transmet les messages de l'un a l'autre, ainsi que les notifications "lu".
 */
public class Messagerie {
	private final static Messagerie instance = new Messagerie();
	
	private Messagerie() {
	}
	
	public static Messagerie getInstance() {
		return Messagerie.instance;
	}

	/**
	 * Tant que le destinataire n'est pas capable de recevoir le message, la messagerie le garde
	 * en memoire.
	 */
	public void recupererMessage(Message message) {
		try {
			Thread.sleep(1000);
			while (message.getDestinataire().actif && !message.getDestinataire().connecte) {
				Thread.sleep(1000);
			}
			if (message.getDestinataire().actif) {
				transmettreMessage(message);
			}
		} catch (InterruptedException e) {
			System.out.println(this+": Exception dans recupererMessage");
		}
	}

	/**
	 * Tant que l'emetteur n'est pas capable de recevoir la notification, la messagerie la garde
	 * en memoire.
	 */
	private void transmettreMessage(Message message) {
		try {
			//message.getDestinataire().recevoirMessage(message.getEmetteur(), message.getMessage());
			Thread.sleep(1000);
			while (message.getEmetteur().actif && !message.getEmetteur().connecte) {
				Thread.sleep(1000);
			}
			if (message.getEmetteur().actif) {
				transmettreNotifLu(message);
			}
		} catch (InterruptedException e) {
			System.out.println(this+": Exception dans transmettreMessage");
		}
	}
	
	private void transmettreNotifLu(Message message) {
		//message.getEmetteur().recevoirNotifLu(message.getDestinataire());
	}
}
