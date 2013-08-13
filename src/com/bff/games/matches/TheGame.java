package com.bff.games.matches;

import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bff.games.matches.Widgets.MatchFlipper;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 6/18/13
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class TheGame {
    private static TheGame ourInstance = new TheGame();
    private int nRows = 0;
    private int nCols = 0;
    private int nMaxRows;
    private int nMaxCols;
    final static String TAG = "TheGame";
    private int nCardsLeftInGame;
    private boolean beginner;
    private boolean advanced;
    private Drawable backOfCardDrawable;
    private Set<Integer> matchIDs = new HashSet<Integer>();
    private int goToPairs;

    private Drawable imageToPass;

    private boolean flipPending=false;

    private Collection<Match> currentMatches;

    MatchFlipper firstFlipped = null;
    MatchFlipper secondFlipped = null;

    private long totalTime = 0;

    private Collection<Match> matches = new ArrayList<Match>();

    public static TheGame getInstance() {
        return ourInstance;
    }

    private TheGame() {
    }

    public int getNCols() {
        return nCols > 0 ? nCols : nMaxCols;
    }

    public void setNCols(int nCols) {
        this.nCols = nCols;
    }

    public int getNRows() {
        return nRows > 0 ? nRows : nMaxRows;
    }

    public void setNRows(int nRows) {
        this.nRows = nRows;
    }

    public void addMatch(Match newMatch) {
        matches.add(newMatch);
    }

    public Match[] getMatches() {
        return (Match[]) matches.toArray(new Match[0]);
    }

    public Card[] getRandomizedCards(int nCards) {
        // return a randomized array of cards

        Collections.shuffle((List) matches);

        // create array of cards
        Collection<Card> cards = new ArrayList<Card>();
        currentMatches = new ArrayList<Match>();

        TheGame theGame = TheGame.getInstance();

        int nMatches = nCards / 2;
        int id = 0;
        for (int nMatch = 0; nMatch < nMatches; nMatch++) {
            // adding match
            Match theMatch = (Match) ((List) matches).get(nMatch);
            currentMatches.add(theMatch);
            theMatch.setId(id);
            Card card1 = new Card();
            Card card2 = new Card();

            if (theGame.isBeginner()) {
                card1.setDrawable(theMatch.getImage1());
                card1.setFlippedDrawable(theMatch.getImage1());

                card2.setDrawable(theMatch.getImage2());
                card2.setFlippedDrawable(theMatch.getImage2());
            } else {
                card1.setDrawable(backOfCardDrawable);
                card1.setFlippedDrawable(theMatch.getImage1());

                card2.setDrawable(backOfCardDrawable);
                card2.setFlippedDrawable(theMatch.getImage2());
            }

            card1.setId(theMatch.getId());
            // adding match's id: theMatch.getId());
            card2.setId(theMatch.getId());
            cards.add(card1);
            cards.add(card2);
            id++;
        }

        Collections.shuffle((List)cards);
        return (Card[]) cards.toArray(new Card[0]);

    }

    public MatchFlipper getSecondFlipped() {
        return secondFlipped;
    }

    public void setSecondFlipped(MatchFlipper secondFlipped) {
        this.secondFlipped = secondFlipped;
    }

    public MatchFlipper getFirstFlipped() {
        return firstFlipped;
    }

    public void setFirstFlipped(MatchFlipper firstFlipped) {
        this.firstFlipped = firstFlipped;
    }

    public int getnCardsLeftInGame() {
        return nCardsLeftInGame;
    }

    public void setnCardsLeftInGame(int nCardsLeftInGame) {
        this.nCardsLeftInGame = nCardsLeftInGame;
    }

    public void flipTwo(int id) {
        if (matchIDs.contains(id)){

        }
        else {
            matchIDs.add(id);
            this.nCardsLeftInGame -= 2;
        }
    }

    public boolean areWeDone() {
        Log.d(TAG, "We have nCardsLeftInGame ->"+nCardsLeftInGame);
        return (this.nCardsLeftInGame > 0) ? false : true;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public int getnMaxRows() {
        return nMaxRows;
    }

    public void setnMaxRows(int nMaxRows) {
        this.nMaxRows = nMaxRows;
    }

    public int getnMaxCols() {
        return nMaxCols;
    }

    public void setnMaxCols(int nMaxCols) {
        this.nMaxCols = nMaxCols;
    }

    public boolean isAdvanced() {
        return advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }

    public boolean isBeginner() {
        return beginner;
    }

    public void setBeginner(boolean beginner) {
        this.beginner = beginner;
    }

    public Drawable getBackOfCardDrawable() {
        return backOfCardDrawable;
    }

    public void setBackOfCardDrawable(Drawable backOfCardDrawable) {
        this.backOfCardDrawable = backOfCardDrawable;
    }

    public Match[] getCurrentMatches() {
        if (currentMatches == null) {
            return new Match[0];
        } else {
            return currentMatches.toArray(new Match[0]);
        }

    }

    public Match[] getFullMatches() {
        if (matches == null) {
            return new Match[0];
        } else {
            return matches.toArray(new Match[0]);
        }

    }

    public boolean isFlipPending() {
        return flipPending;
    }

    public void setFlipPending(boolean flipPending) {
        this.flipPending = flipPending;
    }

    public void clear() {
        matchIDs.clear();
    }

    public Drawable getImageToPass() {
        return imageToPass;
    }

    public void setImageToPass(Drawable imageToPass) {
        this.imageToPass = imageToPass;
    }

    public int getGoToPairs() {
        return goToPairs;
    }

    public void setGoToPairs(int goToPairs) {
        this.goToPairs = goToPairs;
    }
}
