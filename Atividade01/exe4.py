
days = int(input("Digite a quantidade de dias: "))
totalDrived = int(input("Digite a quantidade de km rodados: "))
totalPaid = 0

if totalDrived > 100:
    rate = (totalDrived - 100) * 12
    totalPaid += rate + days * 90
else:
    totalPaid = days * 90

print(f"O valor total a ser pago é de R${totalPaid}")
