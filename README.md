# BigramApplication
Create an application that can take as input text file and output a histogram of the bigrams in the text.

Description:
Bigram Application is a maven project which requires Java8 for execution. In this application histogram of the bigrams of given text is calculated. A bigram is any two adjacent words in the text disregarding case. A histogram is the count of how many times that particular bigram occurred in the text.

Example:
Input: “The quick brown fox and the quick blue hare.” 
Output:
the quick 2
quick brown 1
brown fox 1
fox and 1
and the 1
quick blue 1
blue hare 1

Requirement:
-	Java 8
-	Maven

Main file: 
-	Bigram.java

Test File: 
-	BigramTest.java

Steps to execute program:
1.	Download zip from https://github.com/Valentina9091/BigramApplication
2.	Extract zip 
3.	cd BigramApplication
4.	mvn clean package
5.	java -cp target\BigramApplication-0.0.1-SNAPSHOT.jar com.bigram.Bigram  <filename>

Limitation:
1.	This program can only handle files with extension .csv and .txt 
