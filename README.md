# aconex-phone-challenge

A program that shows a user possible matches for a list of provided phone numbers

# Functional Requirements

- Reads from files specified as command-line arguments or STDIN when no files are given.
- Each line of these files contains a single phone number.
- For each phone number read, output all possible word replacements from a dictionary.
- Replace every digit of the provided phone number with a letter from a dictionary word; however, if no match can be made, a single digit can be left as is
- No two consecutive digits can remain unchanged
- Skips over a phone number (producing no output) if a match cannot be made
- Allow the user to set a dictionary with the -d command-line option
- The dictionary is expected to have one word per line
- All punctuation and whitespace should be ignored in both phone numbers and the dictionary file
- Should not be case sensitive, letting "a" == "A"
- Output should be capital letters and digits separated at word boundaries with a single dash (-), one possible word encoding per line
- The number encoding on the phone the program will use is:

DIGIT | CHARACTERS |
----- | ----- |
2 | A B C |
3 | D E F |
4 | G H I |
5 | J K L |
6 | M N O |
7 | P Q R S |
8 | T U V |
9 | W X Y Z |

# Design

Basic command pattern for input, parser for text file processing, factory for object creation.

# Runtime Requirements

- JRE 1.8+

# Usage

java -jar:<jar name> com.anaish.aconnex.CreatePhoneNumbers [-p <phone number file>] <-d dictionary file>

# Inputs

Sample Phone input file:

/src/test/resources/mock-phone-number-file-big.txt

Sample dictionary file:

/src/test/resources/mock-dictionary-file.txt




