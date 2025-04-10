
totalSpent = float(input("Informe o valor total gasto: "))

if totalSpent > 500:
    rate = (totalSpent - 500) * 0.5
    totalSpent += rate

print(f"Valor total a ser pago: {totalSpent}")