# 4004-SimplisticPoker

Michael Hum - 100883995 - https://github.com/Sacredify

Tests regarding the ranking of hands are located in PokerRankServiceTest.

Tests regarding the validation of input and sorting of final hands are in SimplisticPokerServiceTest.

Tests regarding the gathering of input from the user are in InputServiceTest.

Tests that try and cover the minimum requirements as listed in JP's email are in MinimalCoverTest.

To build this project using maven (note: test failures will still result in a build attempt):

    mvn clean install

To run the tests only:

    mvn clean test
    
To run the file:

    cd target
    java -jar SimplisticPoker-1.0-SNAPSHOT.jar

If you don't have maven, it should still be possible to import the project in your IDE of choice and build from in there, or
manually if you prefer (although who does that?).

    One can also do the following:
    
    apt-get install git
    apt-get install maven
    apt-get install openjdk-8-jdk
    
    git clone https://github.com/Sacredify/4004-SimplisticPoker.git
    cd 4004-SimplisticPoker
    mvn clean test

Written with intellij idea for Jean-Pierre's Fall 2015 run of COMP4004.


A basic example of TDD.

Basically, one tries to follow the pattern of:

1. Write (initially failing) unit test around a piece of functionality.
2. Write the code for that functionality.
3. Test should now be passing. Rinse and repeat for new code, bug fixes, etc.

Example output with two players:

    Enter number of players for this round (Q to quit) >>> 1 AceSpades QueenSpades TenSpades JackSpades KingSpades
    ERR: For input string: "1 AceSpades QueenSpades TenSpades JackSpades KingSpades"

    Enter number of players for this round (Q to quit) >>> 2

    Begin entering hand data (format: playerId RankSuit RankSuit RankSuit RankSuit RankSuit)
    Rank and Suit must follow names outlined in the Rank/Suit enumerations!
    Enter data for player 1 >>>1 AceSpades QueenSpades TenSpades JackSpades KingSpades
    Enter data for player 2 >>>2 AceHearts KingHearts QueenHearts TenHearts JackHearts
    ==== BEGIN RESULTS ====
    Hand for player 1:
        Ace of Spades
        Queen of Spades
        Ten of Spades
        Jack of Spades
        King of Spades
    Final player rank: 1. Hand: Royal flush (high card(s): [Ace, King, Queen, Jack, Ten])
    Hand for player 2:
        Ace of Hearts
        King of Hearts
        Queen of Hearts
        Ten of Hearts
        Jack of Hearts
    Final player rank: 1. Hand: Royal flush (high card(s): [Ace, King, Queen, Jack, Ten])
    ==== END RESULTS ====

    Enter number of players for this round (Q to quit) >>> q

    Process finished with exit code 0
    
Example output with three players:

    Enter number of players for this round (Q to quit) >>> 3
    
    Begin entering hand data (format: playerId RankSuit RankSuit RankSuit RankSuit RankSuit)
    Rank and Suit must follow names outlined in the Rank/Suit enumerations!
    Enter data for player 1 >>>1 AceHearts TwoHearts ThreeDiamonds KingHearts KingClubs
    Enter data for player 2 >>>2 NineDiamonds QueenHearts FiveSpades SevenSpades ThreeSpades
    Enter data for player 3 >>>3 EightDiamonds SixClubs EightClubs JackHearts TenSpades
    ==== BEGIN RESULTS ====
    Hand for player 1:
    	Ace of Hearts
    	Two of Hearts
    	Three of Diamonds
    	King of Hearts
    	King of Clubs
    Final player rank: 1. Hand: One pair (high card(s): [Ace, Three, Two])
    Hand for player 3:
    	Eight of Diamonds
    	Six of Clubs
    	Eight of Clubs
    	Jack of Hearts
    	Ten of Spades
    Final player rank: 2. Hand: One pair (high card(s): [Jack, Ten, Six])
    Hand for player 2:
    	Nine of Diamonds
    	Queen of Hearts
    	Five of Spades
    	Seven of Spades
    	Three of Spades
    Final player rank: 3. Hand: High card (high card(s): [Queen, Nine, Seven, Five, Three])
    ==== END RESULTS ====
