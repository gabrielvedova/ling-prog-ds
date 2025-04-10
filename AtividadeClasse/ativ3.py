#!/usr/bin/python3

phrase = input("Forneça uma frase: ").strip()

def count_caracthers_without_space(phrase):
    count = 0
    for i in phrase:
        if i != " ":
            count += 1
    print(f"A frase possui {count} caracteres")


count_caracthers_without_space(phrase)

