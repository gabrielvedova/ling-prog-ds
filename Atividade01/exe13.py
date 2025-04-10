salario_atual = float(input("Informe o seu salário atual: "))
aumento_percentual = float(input("informe seu aumento percentual em decimal: "))
anos = int(input("Informe a quantidade de anos que queira saber o seu salário: "))

for i in range(anos):
    salario_atual += salario_atual*aumento_percentual
    aumento_percentual = aumento_percentual*2

print(f"R${salario_atual}")