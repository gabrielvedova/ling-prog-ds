qntd_turmas = int(input("Informe a quantidade de turmas na escola: "))
total_alunos = int(input("Informe a quantidade total de alunos na escola: "))

media = total_alunos/ qntd_turmas

print(f"Média de alunos por turma: {media}")

if media > 40:
    print("Algumas turmas tem mais de 40 alunos")