#!/usr/bin/python3

phrase = input("Forneça uma frase: ").strip()

def count_vogals(phrase):
    count = 0
    for i in phrase:
        if i.lower() in "aeiou":
            count += 1
    print(f"A frase possui {count} vogais")

count_vogals(phrase)