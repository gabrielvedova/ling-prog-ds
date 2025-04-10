numbers = input("Me informe 3 números: ").split(" ")

for i in range(len(numbers)):
    numbers[i] = int(numbers[i])

numbers.sort()

print(numbers)