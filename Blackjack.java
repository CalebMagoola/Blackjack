import java.util.Scanner;
public class Blackjack {
	
	public static void main(String[] args) {
	Card [] userHand = new Card [11];
	Card [] compHand = new Card [11];
	CardDeck deck = new CardDeck();
	Scanner scan = new Scanner(System.in);
	boolean hit = true;	
	
	deck.shuffle();
	Card c1 = deck.nextCard();
	Card c2 = deck.nextCard();
	Card comp1 = deck.nextCard();
	Card hole = deck.nextCard();
	userHand[0] = c1;
	userHand[1] = c2;
	compHand[0] = comp1;
	compHand[1] = hole;	
	
	System.out.println("Welcome to Blackjack!");
	System.out.println("Your first 2 cards are: \n" + c1 + "\n" +  c2 + "\nGiving you a total of: " + countHand(userHand));
	System.out.println("\nThe Computer's first card is: \n" + comp1 + "\nThey also have one in the hole ");
	while(hit){
		System.out.println("\nWould you like to hit? ");
		String ans = scan.nextLine();
		if(ans.equalsIgnoreCase("no")) {
			hit = false;
		}
		else {
			Card hitCard = deck.nextCard();
			System.out.println("Your next card is:\n" + hitCard);
			addCard(userHand, hitCard);
			System.out.println("Your updated total is: " + countHand(userHand));
			if(countHand(userHand) > 21) {
				break;
			}
		}
	}
	while (countHand(compHand) < 17) {
			addCard(compHand, deck.nextCard());
		}
	if(countHand(userHand) > 21) {
		System.out.println("You went over 21. You lose :(");
	}
	else if (countHand(compHand) > 21) {
		System.out.println("You win! The computer went over 21");
		System.out.println("The computer's cards were: \n" + printHand(compHand,0));
		//printHand(compHand,0);
	}
	else if (countHand(userHand) > countHand(compHand)){
		System.out.println("You win! You had a higher total than the computer.");
		System.out.println("The computer's cards were: \n" + printHand(compHand,0));
		//printHand(compHand,0);
	}
	else if (countHand(userHand) < countHand(compHand)) {
		System.out.println("You lose. The computer's total was higher than yours.");
		System.out.println("The computer's cards were: \n" + printHand(compHand,0));
		//printHand(compHand,0);
	}
	else {
		System.out.println("You tied.");
		System.out.println("The computer's cards were: \n" + printHand(compHand,0));
		//printHand(compHand,0);
	}
	scan.close();
	}
	
	//methods
	public static void addCard(Card [] hand, Card card) {
		for(int i = 0; i < hand.length; i++) {
			if(hand[i] == null) {
				hand[i] = card;
				break;
			}
		}
	}
	
	public static int countHand(Card [] hand) {
		int total = 0;
		int otherTotal = 0;
		for(int i = 0; i < hand.length; i++) {
			if(hand[i] != null) {
				if(hand[i].getRank().getValue() == 11) {
					total += hand[i].getRank().getValue();
					otherTotal += hand[i].getRank().getValue() - 10;
				}
				else {
					total += hand[i].getRank().getValue();
					otherTotal += hand[i].getRank().getValue();
				}
			}
		}
		if((total > 21 && otherTotal <=21) || (total > 21 && otherTotal > 21))
			return otherTotal;
		else
			return total;
	}
	
	public static String printHand(Card [] hand, int index) {
		if(index > hand.length) {
			return "";
		}
		else {
			if(hand[index] != null) {
				return hand[index] + "\n" + printHand(hand, index+1);
			}
			else
				return "";
			}
		}
	}