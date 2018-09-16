package app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Board {

	private Map<Character, Point> MapPoints = new HashMap<Character, Point>();
	private String[] boardGame;
	private String[] Mills;
	private int BoardError;

	public Board() {
		for (char Char = 'A'; Char <= 88; Char++) {
			MapPoints.put(Char, Point.EMPTY);
		}

		boardGame = BoardGeometric.LAYOUT;

		Mills = BoardGeometric.MILLS;
	}

	public void resetBoard() {
		for (char Char = 'A'; Char <= 88; Char++) {
			MapPoints.put(Char, Point.EMPTY);
		}
	}

	public void printBoard() {
		String Board = "";
		String LBoard = "";
		String RBoard = "";
		String space = "                ";
		char SpecialChar;

		for (int i = 0; i < boardGame.length; i++) {
			RBoard = "";
			LBoard = "";

			for (char ch : boardGame[i].toCharArray()) {
				SpecialChar = ch;
				RBoard += ch;

				for (Map.Entry<Character, Point> entry : MapPoints.entrySet()) {
					if (entry.getKey() == ch) {
						if (entry.getValue() == Point.EMPTY) {
							SpecialChar = '.';
						} else if (entry.getValue() == Point.W || entry.getValue() == Point.Z) {
							if (entry.getValue() == Point.W) {
								SpecialChar = 'W';
							} else {
								SpecialChar = 'Z';
							}
						}
					}
				}

				LBoard += SpecialChar;

			}
			Board += LBoard + space + RBoard + "\n";
		}

		System.out.println(Board);
	}

	public boolean checkIfValidPoint(String Point) {

		for (char ch : Point.toCharArray()) {

			if (ch < 65 || ch > 88) {
				this.BoardError = 1;
				this.BoardPrintErrorMsg();
				return false;
			}
		}

		return true;
	}

	public String giveEmptyPoints() {
		String Pointslist = "";
		String[] AllPoint = BoardGeometric.MILLS;

		for (int i = 0; i < AllPoint.length; i++) {
			for (int x = 0; x < AllPoint[i].length(); x++) {
				for (char ch : AllPoint[i].toCharArray()) {
					if (MapPoints.get(ch) == Point.EMPTY) {
						Pointslist += ch;
					}
				}
			}
		}
		return Pointslist;
	}

	public boolean checkIfPointEmpty(char ch) {
		boolean pointEmpty = false;

		if ((Point) MapPoints.get(ch) == Point.EMPTY) {
			pointEmpty = true;
		}

		return pointEmpty;
	}

	public boolean FillPoint(char selectedPoint, String value) {
		Point valuePoint = Point.EMPTY;

		if (value == "W") {
			valuePoint = Point.W;
		} else if (value == "Z") {
			valuePoint = Point.Z;
		} else {
			valuePoint = Point.EMPTY;
		}

		if (valuePoint != Point.EMPTY) {
			if ((Point) MapPoints.get(selectedPoint) == Point.W || (Point) MapPoints.get(selectedPoint) == Point.Z) {
				this.BoardError = 4;
				this.BoardPrintErrorMsg();
				return false;
			}
		}

		MapPoints.put(selectedPoint, valuePoint);
		return true;
	}

	public boolean hasMill(String playerColor, Character Cpoint) {
		int count;

		for (int i = 0; i < Mills.length; i++) {

			count = 0;
			boolean found = false;
			for (char ch : Mills[i].toCharArray()) {
				if (ch == Cpoint) {
					found = true;
					break;
				}
			}
			if (found) {
				for (char ch : Mills[i].toCharArray()) {
					Point valuePoint = (Point) MapPoints.get(ch);

					if (playerColor == "Z") {
						if (valuePoint == Point.Z) {
							count++;
						}
					} else if (playerColor == "W") {
						if (valuePoint == Point.W) {
							count++;
						}
					}
				}

				if (count == 3) {
					return true;
				}
			}
		}

		return false;
	}

	private Point getPlayerVal(String colorPlayer) {

		Point pointValue = null;

		if (colorPlayer == "W") {
			pointValue = Point.W;
		} else if (colorPlayer == "Z") {
			pointValue = Point.Z;
		}

		return pointValue;
	}

	public String[] getPointsandMillsPlayer(String colorPlayer) {
		String[] PointsandMills = new String[2];

		PointsandMills[0] = getPointPlayer(colorPlayer);
		PointsandMills[1] = getMillsPlayer(colorPlayer);

		return PointsandMills;
	}

	private String getPointPlayer(String colorPlayer) {

		String[] PlayerPoints = { "" };
		Point pointValue = this.getPlayerVal(colorPlayer);
		boolean InString = false;

		for (int i = 0; i < Mills.length; i++) {

			for (char ch : Mills[i].toCharArray()) {

				InString = false;

				if ((Point) MapPoints.get(ch) == pointValue) {
					for (char mp : PlayerPoints[0].toCharArray()) {
						if (mp == ch) {
							InString = true;
							break;
						}
					}

					if (!InString) {
						PlayerPoints[0] += ch;
					}
				}
			}
		}

		return PlayerPoints[0];

	}

	public HashSet<String> getConnects() {

		return BoardGeometric.CONNECTIONS;
	}

	public boolean playerCanMove(String colorPlayer) {

		String PlayerPoints = this.getPointPlayer(colorPlayer);
		String emptyPoints = this.giveEmptyPoints();

		for (char ch : PlayerPoints.toCharArray()) {
			for (int i = 0; i < emptyPoints.length(); i++) {
				if (BoardGeometric.areConnected(ch, emptyPoints.toCharArray()[i])) {
					return true;
				}
			}
		}
		return false;
	}

	private String getMillsPlayer(String colorPlayer) {

		String[] PlayerMills = { "" };
		Point pointValue = this.getPlayerVal(colorPlayer);
		int countPoint;

		for (int i = 0; i < Mills.length; i++) {
			countPoint = 0;

			for (char ch : Mills[i].toCharArray()) {
				if ((Point) MapPoints.get(ch) == pointValue) {
					countPoint++;
				}
			}

			if (countPoint == 3) {
				PlayerMills[0] += Mills[i];
			}
		}

		return PlayerMills[0];
	}

	public boolean useMill(char mp, String opponentColor) {

		boolean cpInMill = false;
		Point oppenentVal = this.getPlayerVal(opponentColor);
		String opponentMills = this.getMillsPlayer(opponentColor);
		String oppenentPoints = this.getPointPlayer(opponentColor);

		if (mp < 65 || mp > 88) {
			this.BoardError = 1;
			this.BoardPrintErrorMsg();
			return false;

		} else if (oppenentVal != (Point) MapPoints.get(mp)) {
			this.BoardError = 2;
			this.BoardPrintErrorMsg();
			return false;
		}

		for (char ch : opponentMills.toCharArray()) {
			if (ch == mp) {
				cpInMill = true;
			}
		}

		if (cpInMill) {
			if (opponentMills.length() < oppenentPoints.length()) {
				this.BoardError = 3;
				this.BoardPrintErrorMsg();
				return false;
			}
		}

		return this.FillPoint(mp, "EMPTY");
	}

	private void BoardPrintErrorMsg() {

		switch (this.BoardError) {
		case 1:
			System.out.println("*** Dat punt bestaat niet ***");
			break;
		case 2:
			System.out.println("*** Dat punt bevat geen pion van je tegenstander ***");
			break;
		case 3:
			System.out.println("*** Die pion van je tegenstander mag je niet pakken ***");
			break;
		case 4:
			System.out.println("*** Dat punt is al bezet ***");
			break;
		case 5:
			System.out.println("*** Punten zijn niet met elkaar verbonden ***");
			break;
		case 6:
			System.out.println("*** Daar staat geen pion van jou ***");
			break;
		}

		this.BoardClearErrors();
	}

	private void BoardClearErrors() {
		this.BoardError = 0;
	}

	public boolean movePoint(char PointFrom, char Pointto, String PlayerColor) {

		Point Playerval = this.getPlayerVal(PlayerColor);

		if (this.checkIfPointEmpty(Pointto)) {

			if ((Point) MapPoints.get(PointFrom) == Playerval) {

				if (BoardGeometric.areConnected(PointFrom, Pointto)) {

					this.FillPoint(PointFrom, "EMPTY");

					this.FillPoint(Pointto, PlayerColor);

					return true;

				} else {
					this.BoardError = 5;
				}

			} else {
				this.BoardError = 6;
			}

		} else {
			this.BoardError = 4;
		}

		this.BoardPrintErrorMsg();
		return false;
	}

}
