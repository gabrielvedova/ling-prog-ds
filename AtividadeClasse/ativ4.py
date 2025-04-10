#!/usr/bin/python3

notes = input("Forneça uma lista de 3 notas separadas por espaço: ").split(" ")

def check_notes(n1, n2, n3):
    media = (float(n1) + float(n2) + float(n3)) / 3
    if media >= 7:
        print("Aprovado")
    else:
        print("Reprovado")