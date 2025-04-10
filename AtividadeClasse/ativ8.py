#!/usr/bin/python3

word = input("Forneça uma palavra: ").strip()

def is_palindrome(word):
    if word == word[::-1]:
        print(f"A palavra {word} é um palíndromo")
    else:
        print(f"A palavra {word} não é um palíndromo")

is_palindrome(word)