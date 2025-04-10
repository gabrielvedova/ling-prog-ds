notes = input("Me informe 3 notas separadas por espaço: ").split(' ')

for i in range(len(notes)):
    notes[i] = float(notes[i])

print(f"""
Media Aritmética: {(notes[0] + notes[1] + notes[2]) / 3};
Média Ponderada 1: {(notes[0] * 2 + notes[1] * 2 + notes[2] * 3) / 7};
Média Ponderada 2: {(notes[0] * 1 + notes[1] * 2 + notes[2] * 2) / 5};
""")
